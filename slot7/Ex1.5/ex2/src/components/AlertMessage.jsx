export default function AlertMessage({ type = 'info', title, messages = [] }) {
  return (
    <div className={`alert alert-${type} alert-dismissible fade show`} role="alert">
      {title && <h4 className="alert-heading fs-6 mb-2">{title}</h4>}
      {messages.length === 1 ? (
        <p className="mb-0">{messages[0]}</p>
      ) : (
        <ul className="mb-0">
          {messages.map((message, index) => (
            <li key={index}>{message}</li>
          ))}
        </ul>
      )}
    </div>
  )
}
