import React, { useContext } from "react";
import { AppContext } from "../context/AppContext";
import TextField from '@mui/material/TextField';
import EditInput from "../components/input-component/EditInput"
import EditPatientInput from "./input-component/EditPatientInput";

const PatientInfo = () => {

    const { editPatient, patient, notes, addNotes, findNotes, diabetes, errors } = useContext(AppContext);

    const handleClicNotes = async (e) => {
        e.preventDefault();
        console.log("clic");

        console.log(e.currentTarget.elements[0].value);

        const newNote = e.currentTarget.elements[0].value;

        addNotes(newNote, patient.id)
    }

    const handleClic = async (e) => {

        e.preventDefault();

        console.log("clic");

        console.log(e.currentTarget.elements)

        const form = e.currentTarget;
        const id = form.elements[0].value;
        const family = form.elements[1].value;
        const given = form.elements[2].value;
        const dob = form.elements[3].value;
        const sex = form.elements[4].value;
        const address = form.elements[5].value;
        const phone = form.elements[6].value;

        const newPatient = {
            id,
            family,
            given,
            dob,
            sex,
            address,
            phone
        }
        console.log(JSON.stringify(newPatient))
        editPatient(newPatient);

    }




    if (patient) {
        console.log(JSON.stringify(patient))
        console.log(JSON.stringify(diabetes))

        return (

            <div className="row">

                <form onSubmit={handleClic} className="form-horizontal"
                >
                    <table className="table table-bordered">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>family</th>
                                <th>given</th>
                                <th>dob</th>
                                <th>sex</th>
                                <th>address</th>
                                <th>phone</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <td><span >
                                    <input
                                        type="text" className="form-control"
                                        value={patient.id} />
                                </span></td>
                                <td>
                                    <EditPatientInput field={patient.family}></EditPatientInput>
                                    {errors.family ? <span style={{ color: 'red', fontSize: '12px' }}>{errors.family}</span> : ''}
                                </td>

                                <td><EditPatientInput field={patient.given}></EditPatientInput>
                                    {errors.given ? <span style={{ color: 'red', fontSize: '12px' }}>{errors.given}</span> : ''}
                                </td>

                                <td><EditPatientInput field={patient.dob}></EditPatientInput></td>

                                <td><EditPatientInput field={patient.sex}></EditPatientInput></td>

                                <td><EditPatientInput field={patient.address}></EditPatientInput></td>
                                <td><EditPatientInput field={patient.phone}></EditPatientInput></td>
                            </tr>

                        </tbody>
                    </table>
                    <button type="submit" className="btn btn-primary">Edit patient</button>
                </form>

                {(typeof notes != "undefined") ?
                    <div>
                        <div>
                            {diabetes}
                        </div>
                        <div>
                            {
                                notes.map(n =>
                                    <EditInput key={n.date} date={n.date} notes={n.notes} patientId={patient.id} onclick={handleClicNotes}></EditInput>
                                )
                            }

                        </div>


                    </div>
                    :
                    <div>

                    </div>
                }

                <form onSubmit={handleClicNotes} className="form-horizontal">
                    <TextField
                        id="standard-textarea"
                        label="Notes"
                        placeholder="Please enter your notes here"
                        multiline
                        rows={5}
                        variant="standard"
                    />

                    <button type="submit" className="btn btn-primary">Add notes</button>
                </form>



            </div>

        );
    } else {
        return (
            <div></div>
        );
    }


}


export default PatientInfo;