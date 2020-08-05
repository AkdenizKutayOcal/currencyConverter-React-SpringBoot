import React, { useState, useEffect } from "react";
import Navi from "../Navi/Navi";
import { fetchCurrencies, fetchCurrencyCodes,getValueOf } from "../../api";
import styles from "./Home.module.css";
import titleImage from "../../images/titleImage.png";
import convertImage from "../../images/convert.png";
import CurrencyPicker from "../CurrencyPicker/CurrencyPicker";
import Button from "@material-ui/core/Button";
import Card from "../Card/Card";
import ErrorCard from "../Card/ErrorCard";
import AmountInput from "../AmountInput/AmountInput";

function Home() {
  const [currencyCodes, setCurrencyCodes] = useState([]);
  const [currencyFrom, setCurrencyFrom] = useState([]);
  const [currencyTo, setCurrencyTo] = useState([]);
  const [amount, setAmount] = useState([0]);
  const [value, setValue] = useState([0]);
  const [isButtonClicked, setButtonClick] = useState([false]);


  useEffect(() => {
    fetchCurrencyCodes().then((response) => setCurrencyCodes(response));
  }, []);

  const handleCurrencyChangeFrom = (currency) => {
    setCurrencyFrom(currency);
  };

  const handleCurrencyChangeTo = (currency) => {
    setCurrencyTo(currency);
  };

  const handleTextFieldChange = (amount) => {
    setAmount(amount);
  }

  const handleButtonClick = () => {
      setButtonClick([true]);
      getValueOf(currencyFrom,currencyTo).then(response=> setValue(response));
      
  }

  const handleCardDisplay = () => {
      
      if(isButtonClicked[0]){
       
        if(currencyFrom==currencyTo){
            return(
                <ErrorCard message={"Please select distinct currencies"}/>
            )
        }
        else if(currencyFrom[0]===undefined || currencyTo[0]===undefined){
            return(
                <ErrorCard message={"Please select Currency"}/>
            )
        }
        else{
          return(
              <Card from={currencyFrom} to={currencyTo} amount={amount} value={value} />
          )
        }
      }
    
  };
  return (
    <div>
      <Navi />
      {console.log(currencyFrom[0])}
      {console.log("To " + currencyTo)}
      {console.log("value " + value)}
      
      <div>
        <div className={styles.titleDiv}>
          <img
            className={styles.image}
            src={titleImage}
            alt="Currency Converter"
          />
        </div>
        <div className={styles.container}>
        <AmountInput handleTextFieldChange={handleTextFieldChange} />
          <CurrencyPicker
            handleCurrencyChange={handleCurrencyChangeFrom}
            currencyCodes={currencyCodes}
          />
          <img src={convertImage} alt="Converter" />
          <CurrencyPicker
            handleCurrencyChange={handleCurrencyChangeTo}
            currencyCodes={currencyCodes}
          />
          <Button className="btn-convert" onClick={handleButtonClick}>Convert</Button>
          
        </div>
        {
            handleCardDisplay()
        }
      </div>
    </div>
  );
}

export default Home;
