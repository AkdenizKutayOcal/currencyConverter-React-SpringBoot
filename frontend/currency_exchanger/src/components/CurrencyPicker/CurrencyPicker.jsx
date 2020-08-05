import React from "react";
import { NativeSelect, FormControl } from "@material-ui/core";
import styles from "./CurrencyPicker.module.css";


const CurrencyPicker = ( {handleCurrencyChange,currencyCodes}) => {
    
  return (
    <FormControl className={styles.FormControl}>
      <NativeSelect defaultValue="" onChange={(e)=> handleCurrencyChange(e.target.value)}>
        {currencyCodes.map((currency, i) => (
          <option key={i} value={currency}>
            {currency}
          </option>
        ))}
      </NativeSelect>
    </FormControl>
  );
};

export default CurrencyPicker;
