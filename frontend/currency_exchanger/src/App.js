import React from "react";
import styles from "./App.module.css";
import CurrencyPicker from "./components/CurrencyPicker/CurrencyPicker";
import AmountInput from "./components/AmountInput/AmountInput";
import Button from "@material-ui/core/Button";
import titleImage from "./images/titleImage.png";
import convertImage from "./images/convert.png";
import { getValueOf } from "./api";
import axios from "axios";

class App extends React.Component {
  state = {
    amount: 0,
    result: 0,
    currencyFrom: "",
    currentyTo: "",
    isButtonClicked: false,
  };

  componentDidMount() {}

  //Getting selected currency from picker
  handleCurrencyChangeFrom = async (currencyFrom) => {
    this.setState({ currencyFrom: currencyFrom });
  };

  //Getting selected currency from picker
  handleCurrencyChangeTo = async (currencyTo) => {
    this.setState({ currencyTo: currencyTo });
  };

  handleTextFieldChange = async (amount) => {
    this.setState({ amount: amount });
  };

  handleButtonClick = () => {
    this.setState({ isButtonClicked: true });
    if (this.state.currencyFrom !== this.state.currentyTo) {
      axios
        .get(
          `http://localhost:8080/currencies/${this.state.currencyFrom}/rates/${this.state.currencyTo}`
        )
        .then((response) => {
          const result = this.state.amount * response.data;
          this.setState({ result: result.toFixed(5) });
        })
        .catch((err) => {
          console.log(
            "Could not fetch the value of the rate of the currency",
            err.message
          );
        });
    }
  };

  calculateCurrency(amount, value) {
    return amount * value;
  }

  render() {
    return (
      <div>
        <div className={styles.titleDiv}>
          <img
            className={styles.image}
            src={titleImage}
            alt="Currency Converter"
          />
        </div>
        <div className={styles.container}>
          <AmountInput handleTextFieldChange={this.handleTextFieldChange} />
          <CurrencyPicker
            handleCurrencyChange={this.handleCurrencyChangeFrom}
          />
          <img src={convertImage} alt="Converter" />
          <CurrencyPicker handleCurrencyChange={this.handleCurrencyChangeTo} />
          <Button className="btn-convert" onClick={this.handleButtonClick}>
            Convert
          </Button>
        </div>
        <div className={styles.container}>
          <p className={styles.resultText}>
            {(() => {
              if (this.state.isButtonClicked) {

                if(this.state.result==0){
                  return "You have to select different currency values";
                }
                return (
                  this.state.amount +
                  "   " +
                  this.state.currencyFrom +
                  "    to    " +
                  this.state.currencyTo +
                  " = " +
                  this.state.result
                );
              } else {
                return "Select distinct currencies from the lists and enter an amount";
              }
            })()}
          </p>
        </div>
      </div>
    );
  }
}

export default App;
