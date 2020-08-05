import axios from 'axios';

const url = "http://localhost:8080/";

export const fetchCurrencies = async () => {

    let finalUrl = url+"currencies";

    try{
        const response = await axios.get(finalUrl)
        return response;
    
    }catch(error){
        console.error("Could not fetch the currencies from "+url);
    }
}

export const fetchCurrencyCodes = async () => {

    let finalUrl = url+"currencies";

    try{
        const response = await axios.get(finalUrl) 
        const currencyCodes = [];
        response.data.forEach(currency => {
            currencyCodes.push(currency.baseCode);
        });
        //console.log(currencyCodes);
        return currencyCodes;
    
    }catch(error){
        console.error("Could not fetch the currency codes from "+url);
    }
}

export const getValueOf = async (from,to) => {

    
    let finalUrl = url+"currencies/"+from+"/rates/"+to;

    try{
        const response = await axios.get(finalUrl); 
        
        return response.data;
    
    }catch(error){
        console.error("Could not fetch the value of the rate of the currency from "+url);
    }
}

export const createCurrency = async (data) =>{

    let finalUrl = url+"currencies";

    /*try{
        console.log(item);
        axios({
            method: 'post',
            url: finalUrl,
            data: item
        });

    
    }catch(error){
        console.error("Error occured creating currency");
    }*/

    const response = await fetch(finalUrl, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
          'Content-Type': 'application/json'
          // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(data) // body data type must match "Content-Type" header
      });
      return response.json();

}