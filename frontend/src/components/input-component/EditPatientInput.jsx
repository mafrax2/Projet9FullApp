import React, { useContext, useEffect, useState } from "react";
import TextField from '@mui/material/TextField';
import { AppContext } from "../../context/AppContext";
import { useStepperContext } from "@mui/material";

const EditPatientInput = (props) => {

    const [text, setText] = useState(props.field);

    const onTextChange = (e) => {
        setText(e.target.value);
    }

    useEffect(() => {
        setText(props.field)
    }, [props])

    return (<span >
        <input
            type="text" className="form-control"
            value={text} onInput={onTextChange} />
    </span>);

}

export default EditPatientInput;