import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

const Register = () => {
  const intialValues = {
    fname: '',
    lname: '',
    birthday: '',
    email: '',
    password: '',
    confirmPass: ''
  };
  const navigate = useNavigate();
  const [formValues, setFormValues] = useState(intialValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  const submit = () => {
    console.log(formValues);
  };


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };


  const handleSubmit = (e) => {
    e.preventDefault();
    setFormErrors(validate(formValues));
    setIsSubmitting(true);
    console.log(formValues);
    navigate("/login");
    setIsSubmitting(false);
  };


  const validate = (values) => {
    let errors = {};
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;

    if (!values.fname) {
      errors.fname = "First name is required";
    } else if (values.fname.length < 3) {
      errors.fname = "First name must be at least 3 characters";
    }

    if (!values.lname) {
      errors.lname = "Last name is required";
    } else if (values.lname.length < 3) {
      errors.lname = "Last name must be at least 3 characters";
    }

    if (!values.birthday) {
      errors.birth = "Birthday is required";
    }

    if (!values.email) {
      errors.email = "Cannot be blank";
    } else if (!regex.test(values.email)) {
      errors.email = "Invalid email format";
    }

    if (!values.password) {
      errors.password = "Cannot be blank";
    } else if (values.password.length < 4) {
      errors.password = "Password must be more than 4 characters";
    }

    if (!values.confirmPass) {
      errors.confirmPass = "Cannot be blank";
    } else if (values.confirmPass !== values.password) {
      errors.confirmPass = "Passwords do not match";
    }

    return errors;
  };

  useEffect(() => {
    if (Object.keys(formErrors).length === 0 && isSubmitting) {
      submit();
    }
  }, [formErrors]);

  return (
    <div className="auth-form" id="register">
      <h2>Sign Up</h2>
      <p>Please fill this form to create an account</p>
      <form id="register-form" onSubmit={handleSubmit}>
        <label htmlFor="fname">First Name</label>
        <input
          value={formValues.fname}
          onChange={handleChange}
          className="input"
          type="text"
          name="fname"
          id="first-name"
          placeholder="First Name"
        />
        {formErrors.fname && <span>{formErrors.fname}</span>}
        <label htmlFor="lname">Last Name</label>
        <input
          value={formValues.lname}
          onChange={handleChange}
          className="input"
          type="text"
          name="lname"
          id="last-name"
          placeholder="Last Name"
        />
        {formErrors.lname && <span>{formErrors.lname}</span>}
        <label htmlFor="birthday">Birthday</label>
        <input
          value={formValues.birth}
          onChange={handleChange}
          type="date"
          name="birthday"
          className="input"
        />
        {formErrors.birth && <span>{formErrors.birth}</span>}
        <label htmlFor="email">Email</label>
        <input
          value={formValues.email}
          onChange={handleChange}
          className="input"
          type="email"
          name="email"
          placeholder="Email"
        />
        {formErrors.email && <span>{formErrors.email}</span>}
        <label htmlFor="password">Password</label>
        <input
          value={formValues.password}
          onChange={handleChange}
          className="input"
          type="password"
          name="password"
          placeholder="Password"
        />
        {formErrors.password && <span>{formErrors.password}</span>}
        <label htmlFor="confirmPass">Confirm Password</label>
        <input
          value={formValues.confirmPass}
          onChange={handleChange}
          className="input"
          type="password"
          name="confirmPass"
          placeholder="Confirm Password"
        />
        {formErrors.confirmPass && <span>{formErrors.confirmPass}</span>}
        {!isSubmitting ? <button>Sign Up</button> : <button disabled>Signing up...</button>}
        <Link to="/login">Already have an account? Login</Link>
      </form>
    </div>
  );
};

export default Register;
