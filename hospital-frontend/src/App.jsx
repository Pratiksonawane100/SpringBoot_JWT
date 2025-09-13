import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import PatientList from "./components/PatientList";
import PatientDetails from "./components/PatientDetails";

function App() {
  return (
    <Router>
      <div className="p-4 font-sans">
        <h1 className="text-2xl font-bold mb-4">🏥 Hospital Management</h1>
        <Routes>
          <Route path="/" element={<PatientList />} />
          <Route path="/patients/:id" element={<PatientDetails />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
