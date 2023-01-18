import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function ForgotPass() {
  const initialFormValues = {
    email: "",
    password: "",
    confirmPass: ""
  };

  const [formValues, setFormValues] = useState(initialFormValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };

  const validate = (values) => {
    let errors = {};
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;

    if (!values.email) {
      errors.email = "Email is required";
    } else if (!regex.test(values.email)) {
      errors.email = "Invalid email format";
    }

    if (!values.password) {
      errors.password = "Password is required";
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    se;
    setFormErrors(validate(formValues));
    setIsSubmitting(true);
  };

  useEffect(() => {
    if (Object.keys(formErrors).length === 0 && isSubmitting) {
      submit();
    }
  }, [formErrors]);

  return (
    <div className="auth-form" id="forgot-pass">
      <h2 className="title">Reset Password</h2>
      <form onSubmit={handleSubmit}>
        <input
          value={formValues.email}
          onChange={handleChange}
          className="input"
          type="email"
          name="email"
          placeholder="Email"
        />
        {formErrors.email && <span className="error">{formErrors.email}</span>}
        <input
          value={formValues.password}
          onChange={handleChange}
          className="input"
          type="password"
          name="password"
          placeholder="Password"
        />
        {formErrors.password && <span className="error">{formErrors.password}</span>}
        <input
          value={formValues.confirmPass}
          onChange={handleChange}
          className="input"
          type="password"
          name="confirmPass"
          placeholder="Confirm Password"
        />
        {!isSubmitting ? <button>Reset Password</button> : <button disabled>Resetting Password...</button>}
      </form>
    </div>
  );
}

export default ForgotPass