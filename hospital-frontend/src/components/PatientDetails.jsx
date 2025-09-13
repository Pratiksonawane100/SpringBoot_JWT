import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";

function PatientDetails() {
  const { id } = useParams();
  const [patient, setPatient] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:9090/patients/${id}`)
      .then(res => {
        if (!res.ok) throw new Error("Network response was not ok");
        return res.json();
      })
      .then(data => setPatient(data))
      .catch(err => console.error("Error fetching patient:", err));
  }, [id]);

  if (!patient) return <p>Loading...</p>;

  return (
    <div>
      <h2 className="text-xl font-bold mb-3">{patient.name}</h2>
      <p><strong>Email:</strong> {patient.email}</p>
      <p><strong>Gender:</strong> {patient.gender}</p>
      <p><strong>Birth Date:</strong> {patient.birthDate}</p>
      <p><strong>Created At:</strong> {patient.createdAt}</p>

      {/* Insurance Section */}
      <h3 className="mt-4 text-lg font-semibold">Insurances</h3>
      <ul className="list-disc ml-6">
        {patient.insurances?.map(ins => (
          <li key={ins.id}>
            {ins.providerName} - {ins.policyNumber} ({ins.coverageDetails})
          </li>
        ))}
      </ul>

      {/* Appointments Section */}
      <h3 className="mt-4 text-lg font-semibold">Appointments</h3>
      <ul className="list-disc ml-6">
        {patient.appointments?.map(app => (
          <li key={app.id}>
            {app.doctorName} ({app.department}) on {app.appointmentDate} - <b>{app.status}</b>
          </li>
        ))}
      </ul>

      <Link to="/" className="mt-4 inline-block text-blue-600 hover:underline">← Back to Patients</Link>
    </div>
  );
}

export default PatientDetails;
