import React, { useContext, useState } from "react";
import TextField from '@mui/material/TextField';
import { AppContext } from "../../context/AppContext";

const EditInput = (props) => {

    const { patient, editNotes, deleteNote } = useContext(AppContext);

    const [editOn, setEditOn] = useState(false);


    const changeEdit = async (e) => {
        e.preventDefault();
        setEditOn(!editOn)

    }

    const handleDeleteNote = async (e) => {
        e.preventDefault();


        deleteNote(props.notes, props.patientId, props.date);

    }

    const handleEditClick = async (e) => {
        e.preventDefault();
        console.log("clic");
        console.log(props)

        console.log(e.currentTarget.elements[0].value);

        const newNote = e.currentTarget.elements[0].value;
        console.log(patient.id);
        editNotes(newNote, props.date, patient.id)

    }

    const NewlineText = ({ text }) => {
        return text.split('\n').map(str => <p>{str}</p>);
    }

    return (

        <div>
            <div>{props.date}</div>

            {
                (!editOn) ? <div>
                    <div> <NewlineText text={props.notes} /></div>
                    <button onClick={handleDeleteNote}>Delete Note</button>
                </div>
                    :
                    <form onSubmit={handleEditClick} className="form-horizontal">
                        <TextField
                            id="standard-textarea"
                            label="Notes"
                            dafaultValue={props.notes}
                            multiline
                            rows={5}
                            variant="standard"
                        />
                        <button type="submit" className="btn btn-primary">Edit notes</button>
                    </form>
            }

            <button onClick={changeEdit}> {(!editOn) ? "Edit" : "Cancel"}</button>

        </div>


    );

}

export default EditInput;