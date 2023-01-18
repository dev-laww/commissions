import { BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Login from "./components/Login";
import Register from "./components/Register";
import ForgotPass from "./components/ForgotPass";
import Dashboard from "./components/Dashboard";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import "./styles/styles.css";

function App() {
  return (
    <Router>
      <div className="container">
        <Routes>
          <Route path="/" element={<Home/>} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/forgot-pass" element={<ForgotPass />} />
          <Route path="/dashboard" element={<Dashboard/>} />
          <Route path="*" element={<NotFound/>}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
