import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function PatientList() {
  const [patients, setPatients] = useState([]);

  useEffect(() => {
    fetch("http://localhost:9090/patients/all")   // ✅ matches backend
      .then(res => {
        if (!res.ok) throw new Error("Network response was not ok");
        return res.json();
      })
      .then(data => setPatients(data))
      .catch(err => console.error("Error fetching patients:", err));
  }, []);

  return (
    <div>
      <h2 className="text-xl font-semibold mb-2">Patients</h2>
      <ul className="space-y-2">
        {/* {patients.map(patient => (
          <li key={patient.id} className="p-3 border rounded-md hover:bg-gray-100">
            <Link to={`/patients/${patient.id}`} className="text-blue-600 hover:underline">
              {patient.name} ({patient.email})
            </Link>
          </li>
        ))} */}
        {patients.map(patient => (
            <div key={patient.id} className="border p-3 mb-3 rounded">
    <p><b>ID:</b> {patient.id}</p>
    <p><b>Name:</b> {patient.name}</p>

    {/* Insurance section */}
    <h4 className="mt-2 font-semibold">Insurances:</h4>
    {patient.insurances && patient.insurances.length > 0 ? (
      patient.insurances.map(ins => (
        <div key={ins.id} className="ml-4">
          <p>Provider: {ins.providerName}</p>
          <p>Policy: {ins.policyNumber}</p>
          <p>Coverage: {ins.coverageDetails}</p>
        </div>
      ))
    ) : (
      <p>No insurance available</p>
    )}
        {patient.appointments && patient.appointments.length > 0 ? (
      patient.appointments.map(ap => (
        <div key={ap.id} className="ml-4">
          <p>Doctor: {ap.doctorName}</p>
          
        </div>
      ))
    ) : (
      <p>No appoinments available</p>
    )}
  </div>
            
        ))}
      </ul>
    </div>
  );
}

export default PatientList;
