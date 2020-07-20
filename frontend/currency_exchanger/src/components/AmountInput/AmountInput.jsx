import React from "react";
import TextField from '@material-ui/core/TextField';


const AmountInput = ({handleTextFieldChange}) => {
    return (
        
        <form className="amount" noValidate autoComplete="off">
            
            <TextField id="filled-basic" label="Amount" variant="filled" defaultValue="" onChange={(e)=> handleTextFieldChange(e.target.value)} />
        
        </form>
    );
};

export default AmountInput;
