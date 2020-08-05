import React ,{ useState, useEffect }from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import "./CurrencyForm.css";
import {createCurrency} from "../../api";

const CurrencyForm = () => {

    const [currencyCode, setCurrencyCode] = useState([]);
    const [currencyName, setCurrencyName] = useState([]);
    
    const handleSubmit = ()=>{
        const item = {

            baseCode: currencyCode,
            baseName: currencyName,
            rates: []
        }


        fetch(`http://localhost:8080/currencies`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: item
        });
    };

  return (
    <div className="currency-form">
      <form noValidate autoComplete="off">
        <TextField
          id="filled-basic"
          label="Currency Code"
          variant="filled"
          defaultValue=""
          onChange={(e)=> setCurrencyCode(e.target.value)}
        />
        <TextField
          id="filled-basic"
          label="Currency Name"
          variant="filled"
          defaultValue=""
          onChange={(e)=> setCurrencyName(e.target.value)}
        />
        <Button variant="outlined" color="primary" onClick={()=>{handleSubmit()}}>
          Create
        </Button>
      </form>
    </div>
  );
};

export default CurrencyForm;
