import React, { useEffect, useState } from 'react';


export const AppContext = React.createContext();


export const TransactionProvider = ({ children }) => {

    const [patient, setPatient] = useState();
    const [notes, setNotes] = useState();
    const [diabetes, setDiabetes] = useState();
    const [errors, setErrors] = useState({});


    const assessDiabetes = async (id) => {
        const response = await fetch(`/assess/${id}`);
        const text = await response.text()
        setDiabetes(text);
        console.log(text)
        return diabetes;
    }

    const deleteNote = async (notes2, patientId, date) => {
        console.log(notes2)
        console.log(date)
        console.log(patientId)
        console.log(Date.parse(date))
        const notes1 = {
            notes: notes2,
            patientId,
            date: Date.parse(date)
        }

        console.log(JSON.stringify(notes1))


        const response = await fetch(`/patHistory/delete`
            , {

                // Adding method type
                method: "DELETE",
                // Adding body or contents to send
                body: JSON.stringify(notes1),

                // Adding headers to the request
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })

        const notes3 = await response.json();

        console.log(notes3);

        setNotes(notes3);

    }

    const editNotes = async (notes2, date, patientId) => {
        console.log(notes2)
        console.log(date)
        console.log(patientId)
        console.log(Date.parse(date))
        const notes1 = {
            notes: notes2,
            patientId,
            date: Date.parse(date)
        }

        console.log(JSON.stringify(notes1))


        const response = await fetch(`/patHistory/edit`
            , {

                // Adding method type
                method: "PUT",
                // Adding body or contents to send
                body: JSON.stringify(notes1),

                // Adding headers to the request
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })

        const notes3 = await response.json();

        const newNotes = [...notes, notes3]

        setNotes(newNotes)

    }

    const addNotes = async (notes2, patientId) => {
        console.log(notes2)

        const notes1 = {
            notes: notes2,
            patientId
        }

        const response = await fetch(`/patHistory/add`
            , {

                // Adding method type
                method: "POST",
                // Adding body or contents to send
                body: JSON.stringify(notes1),

                // Adding headers to the request
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })

        const notes3 = await response.json();

        const newNotes = [...notes, notes3]

        setNotes(newNotes)

    }

    const findNotes = async (patient) => {

        console.log(patient.id)

        const response = await fetch(`/patHistory/find?patientId=${patient.id}`)
        const notes = await response.json();
        console.log(notes);
        setNotes(notes);

        return notes;
    }

    const findPatient = async (family, given) => {

        const response = await fetch(`/find/patient?family=${family}&given=${given}`)

        const patient2 = await response.json();

        setPatient(patient2);

        return patient2;
    }

    const editPatient = async (patient) => {
        setErrors({});
        const response = await fetch(`/edit/patient/${patient.id}`
            , {

                // Adding method type
                method: "PUT",
                // Adding body or contents to send
                body: JSON.stringify(patient),

                // Adding headers to the request
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })

        const patient2 = await response.json().then(data => {
            console.log(JSON.stringify(data))
            if (data.fieldErrors) {
                data.fieldErrors.forEach(fieldError => {
                    console.log(fieldError);
                    if (fieldError.field === 'family') {
                        const errors1 = {
                            family: fieldError.message
                        }
                        setErrors(errors1)
                    }
                    if (fieldError.field === 'given') {
                        const errors1 = {
                            given: fieldError.message
                        }
                        setErrors(errors1)
                    }

                });
            } else {
                alert("Success !!");
            }

        });

        setPatient(patient2);
        console.log(response)

    }

    const addPatient = async (patient) => {
        setErrors({});
        const response = await fetch(`/patient/add`
            , {

                // Adding method type
                method: "POST",
                // Adding body or contents to send
                body: JSON.stringify(patient),

                // Adding headers to the request
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })


        const patient2 = await response.json().then(data => {
            if (data.fieldErrors) {
                data.fieldErrors.forEach(fieldError => {
                    console.log("has error " + data.fieldErrors)
                    if (fieldError.field === 'family') {
                        const errors1 = {
                            family: fieldError.message
                        }
                        setErrors(errors1)
                    }
                    if (fieldError.field === 'given') {
                        console.log("has error on give name " + data.fieldErrors)
                        const errors1 = {
                            given: fieldError.message
                        }
                        setErrors(errors1)
                    }

                });
            } else {
                alert("Success !!");
            }

        });



        setPatient(patient2);

    }


    return (
        <AppContext.Provider value={{ errors, addPatient, editPatient, findPatient, patient, findNotes, addNotes, editNotes, deleteNote, notes, diabetes, assessDiabetes }}>
            {children}
        </AppContext.Provider>
    )

}
