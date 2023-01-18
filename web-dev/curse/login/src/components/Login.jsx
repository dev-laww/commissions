import { useEffect, useState } from "react";
import { Link, useNavigate, Navigate } from "react-router-dom";

function Login() {
  localStorage.setItem("authenticated", false);
  
  const initialFormValues = {
    email: "",
    password: "",
  };
  const [formValues, setFormValues] = useState(initialFormValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const navigate = useNavigate();

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

    return errors;
  };

  const submit = () => {
    console.log(formValues);
  };

  const users = [
    { email: "johndoe@mail.com", password: "123456" },
    { email: "maryjane@mail.com", password: "123456" },
  ];
  
  const handleSubmit = (e) => {
    e.preventDefault();
    setFormErrors(validate(formValues));
    setIsSubmitting(true);

    const account = users.find((user) => user.email === formValues.email);
    if (account && account.password === formValues.password) {
      localStorage.setItem("authenticated", true);
      navigate("/dashboard");
    } else {
      alert("Invalid email or password");
      setFormValues(initialFormValues);
      setFormErrors({});
    }

    setIsSubmitting(false);
  };

  useEffect(() => {
    if (Object.keys(formErrors).length === 0 && isSubmitting) {
      submit();
    }
  }, [formErrors]);

  return (
    <div className="auth-form" id="login">
      <h2 className="title">Welcome to E-Pay</h2>
      <form onSubmit={handleSubmit}>
        <input
          className="input"
          value={formValues.email}
          type="email"
          name="email"
          placeholder="Email"
          onChange={handleChange}
        />
        {formErrors.email && <span className="error">{formErrors.email}</span>}
        <input
          className="input"
          value={formValues.password}
          type="password"
          name="password"
          placeholder="Password"
          onChange={handleChange}
        />
        {formErrors.password && (
          <span className="error">{formErrors.password}</span>
        )}
        {!isSubmitting ? (
          <button>Login</button>
        ) : (
          <button disabled>Logging in...</button>
        )}
      </form>
      <Link to="/forgot-pass">Forgot Password?</Link>
      <Link to="/register">Don't have an account? Sign up</Link>
    </div>
  );
}

export default Login;
