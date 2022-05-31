import React, { useContext } from "react";
import { AppContext } from "../context/AppContext";


const FindInputs = () => {

    const { findPatient, findNotes, assessDiabetes, notes, patient, } = useContext(AppContext);

    const handleSubmit = async (e) => {


        e.preventDefault();

        console.log("clic");

        const form = e.currentTarget;

        console.log(form)

        const formElements = form.elements;

        console.log(formElements)
        const family = form.elements[0].value
        const given = form.elements[1].value
        console.log(family)
        console.log(given)


        const patient = await findPatient(family, given);
        console.log(patient)
        const notes = await findNotes(patient);
        console.log(notes)
        console.log(patient.id);
        const diabetes = await assessDiabetes(patient.id);
    }

    return (
        <div className="container">

            <div className="row">
                <h2>Find Patient</h2>
            </div>

            <div className="row">
                <form onSubmit={handleSubmit} className="form-horizontal"
                >

                    <div className="form-group">
                        <label >family name</label>
                        <br />
                        <input
                            type="text" className="form-control"
                            aria-describedby="familyHelp" placeholder="Account"></input>
                        <p className="text-danger"></p>
                    </div>

                    <div className="form-group">
                        <label>given name</label>
                        <br />
                        <input
                            type="text" className="form-control"
                            aria-describedby="givenHelp" placeholder="given"></input>
                        <p className="text-danger" ></p>
                    </div>


                    <button type="submit" className="btn btn-primary">Find</button>

                </form>

            </div>

        </div>
    );
}





export default FindInputs;