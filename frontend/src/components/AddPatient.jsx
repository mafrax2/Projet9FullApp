import React, { useContext } from "react";
import { AppContext } from "../context/AppContext";


const AddPatient = () => {

    const { addPatient, patient, errors } = useContext(AppContext);


    const handleSubmit = async (e) => {


        e.preventDefault();

        const form = e.currentTarget;
        console.log(form);

        const family = form.elements[0].value;
        const given = form.elements[1].value;
        const dob = form.elements[2].value;
        const sex = form.elements[3].value;
        const address = form.elements[4].value;
        const phone = form.elements[5].value;

        const newPatient = {
            family,
            given,
            dob,
            sex,
            address,
            phone
        }

        console.log(newPatient)

        addPatient(newPatient);

    }

    return (
        <div className="container">

            <div className="row">
                <h2>Add Patient</h2>
            </div>

            <div className="row">
                <form onSubmit={handleSubmit} className="form-horizontal"
                >

                    <div className="form-group">
                        <label >family name</label>
                        <br />
                        <input
                            type="text" className="form-control"
                            aria-describedby="familyHelp" placeholder="Family name"></input>
                        {errors.family ? <span style={{ color: 'red', fontSize: '12px' }}>{errors.family}</span> : ''}
                    </div>

                    <div className="form-group">
                        <label>given name</label>
                        <br />
                        <input
                            type="text" className="form-control"
                            aria-describedby="givenHelp" placeholder="given name"></input>
                        {errors.given ? <span style={{ color: 'red', fontSize: '12px' }}>{errors.given}</span> : ''}

                    </div>

                    <div className="form-group">
                        <label>dob</label>
                        <br />
                        <input
                            type="date" className="form-control"
                            aria-describedby="dobHelp" placeholder="dob"></input>
                        <p className="text-danger" ></p>
                    </div>

                    <div className="form-group">
                        <label>sex</label>
                        <br />
                        <input
                            type="text" className="form-control"
                            aria-describedby="sexHelp" placeholder="sex"></input>
                        <p className="text-danger" ></p>
                    </div>

                    <div className="form-group">
                        <label>address</label>
                        <br />
                        <input
                            type="text" className="form-control"
                            aria-describedby="addressHelp" placeholder="address"></input>
                        <p className="text-danger" ></p>
                    </div>

                    <div className="form-group">
                        <label>phone</label>
                        <br />
                        <input
                            type="text" className="form-control"
                            aria-describedby="phoneHelp" placeholder="phone"></input>
                        <p className="text-danger" ></p>
                    </div>


                    <button type="submit" className="btn btn-primary">Add Patient</button>

                </form>

            </div>

        </div>
    );


}

export default AddPatient;