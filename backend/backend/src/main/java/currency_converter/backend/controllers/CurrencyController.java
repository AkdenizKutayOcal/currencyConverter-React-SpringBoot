package currency_converter.backend.controllers;

import currency_converter.backend.exceptions.ApiRequestException;
import currency_converter.backend.model.Currency;
import currency_converter.backend.model.Rate;
import currency_converter.backend.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies")
    public List<Currency> allCurrencies(){
        return currencyService.findAllCurrencies();
    }

    @GetMapping("/currencies/{baseCode}")
    public ResponseEntity<Currency> getCurrency(@PathVariable String baseCode){
        return currencyService.findCurrency(baseCode);
    }

    @GetMapping("/currencies/{baseCode}/rates")
    public List<Rate> getRates(@PathVariable String baseCode){
        return currencyService.listRates(baseCode);
    }

    @GetMapping("/currencies/{baseCode}/rates/{targetCode}")
    public double getValue(@PathVariable String baseCode,@PathVariable String targetCode){
        return currencyService.findValueOf(baseCode,targetCode);
    }
    @PostMapping("/currencies")
    public ResponseEntity<Currency> createCurrency(@RequestBody Currency currency){
        return currencyService.saveOrUpdateCurrency(currency);
    }
    @PutMapping("currencies/{baseCode}")
    public ResponseEntity<Currency> replaceCurrency(@RequestBody Currency newCurrency, @PathVariable String baseCode){

        Currency currency = currencyService.findCurrency(baseCode).getBody();
        if(currency==null){
            throw new ApiRequestException(ApiRequestException.NO_RECORDS_FOUND + " with currency code " + baseCode);
        }
        else{
            currency.setBaseCode(baseCode);
            currency.setBaseName(newCurrency.getBaseName());
            currency.setRates(newCurrency.getRates());
            return currencyService.saveOrUpdateCurrency(currency);
        }
    }

    @DeleteMapping("/currencies/{baseCode}")
    void deleteCurrency(@PathVariable String baseCode) {
        currencyService.deleteCurrency(baseCode);
    }
}
