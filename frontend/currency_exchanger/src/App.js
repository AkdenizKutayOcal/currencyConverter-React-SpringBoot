import React from "react";
//import { CurrencyPicker, AmountInput } from './components';
import styles from "./App.module.css";
import CurrencyPicker from "./components/CurrencyPicker/CurrencyPicker";
import AmountInput from "./components/AmountInput/AmountInput";
import { fetchCurrencies } from "./api";
import Button from "@material-ui/core/Button";
import titleImage from "./images/titleImage.png";
import convertImage from './images/convert.png';

class App extends React.Component {
  async componentDidMount() {
    /*const data = await fetchCurrencies();
    console.log(data);*/
  }
  render() {
    return (
      <div>
        <div className={styles.titleDiv}>
          <img className={styles.image} src={titleImage} alt="Currency Converter" />
        </div>
        <div className={styles.container}>
          <AmountInput />
          <CurrencyPicker />
          <img src={convertImage} alt="Converter" />
          <CurrencyPicker />
          <Button className="btn-convert">Convert</Button>
        </div>
      </div>
    );
  }
}

export default App;
