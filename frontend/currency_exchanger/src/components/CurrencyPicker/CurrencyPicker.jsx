import React, { useState, useEffect } from "react";
import { NativeSelect, FormControl } from "@material-ui/core";
import styles from "./CurrencyPicker.module.css";
import { fetchCurrencyCodes } from "../../api/index";

const CurrencyPicker = ( {handleCurrencyChange}) => {
  const [fetchedCurrencyCodes, setFetchedCurrencies] = useState([]);

  useEffect(() => {
    const fetchAPI = async () => {
      setFetchedCurrencies(await fetchCurrencyCodes());
    };
    fetchAPI();
  }, [setFetchedCurrencies]);

  
  return (
    <FormControl className={styles.FormControl}>
      <NativeSelect defaultValue="" onChange={(e)=> handleCurrencyChange(e.target.value)}>
        {fetchedCurrencyCodes.map((currency, i) => (
          <option key={i} value={currency}>
            {currency}
          </option>
        ))}
      </NativeSelect>
    </FormControl>
  );
};

export default CurrencyPicker;
