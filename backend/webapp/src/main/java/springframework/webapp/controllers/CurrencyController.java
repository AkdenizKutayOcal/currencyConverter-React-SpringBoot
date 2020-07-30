package springframework.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springframework.webapp.model.Currency;
import springframework.webapp.model.Rate;
import springframework.webapp.repositories.CurrencyRepository;
import springframework.webapp.services.CurrencyService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
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
    public Currency getCurrency(@PathVariable String baseCode){
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
    public Currency createCurrency(@RequestBody Currency currency){
        return currencyService.saveOrUpdateCurrency(currency);
    }
    @PutMapping("currencies/{baseCode}")
    public Currency replaceCurrency(@RequestBody Currency newCurrency, @PathVariable String baseCode){
        Currency currency = currencyService.findCurrency(baseCode);
        if(currency==null){
            return currencyService.saveOrUpdateCurrency(newCurrency);
        }
        else{
            currency.setBaseCode(baseCode);
            currency.setBaseName(newCurrency.getBaseName());
            currency.setRates(newCurrency.getRates());
            return currencyService.saveOrUpdateCurrency(currency);
        }
    }
    @DeleteMapping("/currency/{baseCode}")
    void deleteEmployee(@PathVariable String baseCode) {
        currencyService.deleteCurrency(baseCode);
    }
}
