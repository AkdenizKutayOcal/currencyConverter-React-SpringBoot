package springframework.webapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springframework.webapp.model.Currency;
import springframework.webapp.model.Rate;
import springframework.webapp.repositories.CurrencyRepository;
import springframework.webapp.services.CurrencyService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CurrencyController {


    private final CurrencyService currencyService;
    private final CurrencyRepository currencyRepository;

    public CurrencyController(CurrencyService currencyService, CurrencyRepository currencyRepository) {

        this.currencyService = currencyService;
        this.currencyRepository = currencyRepository;
    }

    @GetMapping("/currencies")
    public ArrayList<Currency> allCurrencies(){
        return currencyService.findAll();
    }

    @GetMapping("/currencies/{baseCode}")
    public Currency getCurrency(@PathVariable String baseCode){
        return currencyRepository.findById(baseCode).get();
    }

    @GetMapping("/currencies/{baseCode}/rates")
    public List<Rate> getRates(@PathVariable String baseCode){
        return currencyService.findRates(baseCode);
    }

    @GetMapping("/currencies/{baseCode}/rates/{targetCode}")
    public double getValue(@PathVariable String baseCode,@PathVariable String targetCode){
        return currencyService.getValue(baseCode,targetCode);
    }
}
