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

    console.log(" "+from+" "+to);
    let finalUrl = url+"currencies/"+from+"/rates/"+to;

    try{
        const response = await axios.get(finalUrl); 
        return response;
    
    }catch(error){
        console.error("Could not fetch the value of the rate of the currency from "+url);
    }
}