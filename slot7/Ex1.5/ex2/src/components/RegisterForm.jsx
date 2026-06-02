import { useState } from 'react'
import AlertMessage from './AlertMessage'

export default function RegisterForm() {
  const [form, setForm] = useState({ fullName: '', email: '', password: '', confirm: '' })
  const [errors, setErrors] = useState({})
  const [showErrorAlert, setShowErrorAlert] = useState(false)
  const [showSuccessAlert, setShowSuccessAlert] = useState(false)

  const validate = (values) => {
    const errs = {}
    if (!values.fullName.trim()) errs.fullName = 'Họ tên không được để trống.'
    if (!values.email) errs.email = 'Email không được để trống.'
    else if (!/^[\w-.]+@[\w-]+\.[a-zA-Z]{2,}$/.test(values.email)) errs.email = 'Email không hợp lệ.'
    if (!values.password) errs.password = 'Mật khẩu không được để trống.'
    else if (values.password.length < 6) errs.password = 'Mật khẩu phải có ít nhất 6 ký tự.'
    if (!values.confirm) errs.confirm = 'Vui lòng xác nhận mật khẩu.'
    else if (values.confirm !== values.password) errs.confirm = 'Mật khẩu xác nhận không khớp.'
    return errs
  }

  const validateField = (name, value, values) => {
    const fieldValues = { ...values, [name]: value }
    switch (name) {
      case 'fullName':
        if (!fieldValues.fullName.trim()) return 'Họ tên không được để trống.'
        return ''
      case 'email':
        if (!fieldValues.email) return 'Email không được để trống.'
        if (!/^[\w-.]+@[\w-]+\.[a-zA-Z]{2,}$/.test(fieldValues.email)) return 'Email không hợp lệ.'
        return ''
      case 'password':
        if (!fieldValues.password) return 'Mật khẩu không được để trống.'
        if (fieldValues.password.length < 6) return 'Mật khẩu phải có ít nhất 6 ký tự.'
        if (fieldValues.confirm && fieldValues.confirm !== fieldValues.password) return 'Mật khẩu xác nhận không khớp.'
        return ''
      case 'confirm':
        if (!fieldValues.confirm) return 'Vui lòng xác nhận mật khẩu.'
        if (fieldValues.confirm !== fieldValues.password) return 'Mật khẩu xác nhận không khớp.'
        return ''
      default:
        return ''
    }
  }

  const updateAlertStates = (nextErrors, nextForm) => {
    const activeErrors = Object.values(nextErrors).filter(Boolean)
    const isComplete = Object.values(nextForm).every((value) => value.trim() !== '')
    const hasErrors = activeErrors.length > 0

    setShowErrorAlert(hasErrors)
    setShowSuccessAlert(!hasErrors && isComplete)
  }

  const handleChange = (e) => {
    const { name, value } = e.target
    setForm((current) => {
      const nextForm = { ...current, [name]: value }
      const fieldError = validateField(name, value, nextForm)
      setErrors((prev) => {
        const nextErrors = { ...prev, [name]: fieldError }
        if (name === 'password' || name === 'confirm') {
          const confirmError = validateField('confirm', nextForm.confirm, nextForm)
          nextErrors.confirm = confirmError
        }
        updateAlertStates(nextErrors, nextForm)
        return nextErrors
      })
      return nextForm
    })
    setShowSuccessAlert(false)
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    const validation = validate(form)
    setErrors(validation)
    const hasError = Object.values(validation).some(Boolean)
    setShowErrorAlert(hasError)
    setShowSuccessAlert(!hasError)

    if (!hasError) {
      setForm({ fullName: '', email: '', password: '', confirm: '' })
    }
  }

  const errorMessages = Object.values(errors).filter(Boolean)

  return (
    <div className="register-page py-5">
      <div className="register-card shadow-sm p-4">
        <h2 className="mb-3">Form Đăng Ký</h2>

        {showErrorAlert && errorMessages.length > 0 && (
          <AlertMessage type="danger" title="Vui lòng sửa các lỗi sau" messages={errorMessages} />
        )}
        {showSuccessAlert && (
          <div className="alert alert-success" role="alert">
            Đăng ký thành công!
          </div>
        )}

        <form onSubmit={handleSubmit} noValidate>
          <div className="mb-3">
            <label className="form-label">Họ tên</label>
            <input
              type="text"
              name="fullName"
              value={form.fullName}
              onChange={handleChange}
              className={`form-control ${errors.fullName ? 'is-invalid' : ''}`}
            />
            {errors.fullName && <div className="invalid-feedback">{errors.fullName}</div>}
          </div>

          <div className="mb-3">
            <label className="form-label">Email</label>
            <input
              type="email"
              name="email"
              value={form.email}
              onChange={handleChange}
              className={`form-control ${errors.email ? 'is-invalid' : ''}`}
            />
            {errors.email && <div className="invalid-feedback">{errors.email}</div>}
          </div>

          <div className="mb-3">
            <label className="form-label">Mật khẩu</label>
            <input
              type="password"
              name="password"
              value={form.password}
              onChange={handleChange}
              className={`form-control ${errors.password ? 'is-invalid' : ''}`}
            />
            {errors.password && <div className="invalid-feedback">{errors.password}</div>}
          </div>

          <div className="mb-3">
            <label className="form-label">Xác nhận mật khẩu</label>
            <input
              type="password"
              name="confirm"
              value={form.confirm}
              onChange={handleChange}
              className={`form-control ${errors.confirm ? 'is-invalid' : ''}`}
            />
            {errors.confirm && <div className="invalid-feedback">{errors.confirm}</div>}
          </div>

          <button type="submit" className="btn btn-primary w-100">Đăng ký</button>
        </form>
      </div>
    </div>
  )
}
