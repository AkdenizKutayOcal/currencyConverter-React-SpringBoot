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

export const fetchCurrencyNames = async () => {

    let finalUrl = url+"currencies";

    try{
        const response = await axios.get(finalUrl) 
        return response;
    
    }catch(error){
        console.error("Could not fetch the currencies from "+url);
    }
}
