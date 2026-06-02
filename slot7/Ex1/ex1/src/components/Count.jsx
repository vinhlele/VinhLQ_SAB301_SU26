import { useState } from 'react'

function Count() {
  const [count, setCount] = useState(0)

  const handleIncrease = () => setCount((current) => current + 1)
  const handleDecrease = () => setCount((current) => Math.max(current - 1, 0))
  const handleReset = () => setCount(0)

  return (
    <div className="card p-4 shadow-sm" style={{ maxWidth: 380 }}>
      <div className="mb-3">
        <h2>Số hiện tại</h2>
        <p className="display-6">{count}</p>
      </div>
      <div className="d-flex gap-2">
        <button type="button" className="btn btn-success" onClick={handleIncrease}>
          Tăng
        </button>
        <button type="button" className="btn btn-warning" onClick={handleDecrease}>
          Giảm
        </button>
        <button type="button" className="btn btn-secondary" onClick={handleReset}>
          Reset
        </button>
      </div>
    </div>
  )
}

export default Count
