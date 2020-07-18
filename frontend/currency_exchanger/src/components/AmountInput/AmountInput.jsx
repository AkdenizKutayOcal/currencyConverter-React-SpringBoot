import React from "react";
import TextField from '@material-ui/core/TextField';


const AmountInput = () => {
    return (
        
        <form className="amount" noValidate autoComplete="off">
            
            <TextField id="filled-basic" label="Amount" variant="filled" />
        
        </form>
    );
};

export default AmountInput;
