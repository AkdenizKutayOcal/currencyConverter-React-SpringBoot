package currency_converter.backend.controllers;

import currency_converter.backend.model.Currency;
import currency_converter.backend.model.Rate;
import currency_converter.backend.services.CurrencyService;
import currency_converter.backend.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class RateController {

    private final RateService rateService;
    private final CurrencyService currencyService;

    @Autowired
    public RateController(RateService rateService, CurrencyService currencyService) {
        this.rateService = rateService;
        this.currencyService = currencyService;
    }

    @GetMapping("/rates")
    public List<Rate> allRates(){
        return rateService.findAllRates();
    }

    @PostMapping("/currencies/{baseCode}/rates")
    public ResponseEntity<Rate> createRate(@RequestBody Rate rate,@PathVariable String baseCode){
        Currency currency = currencyService.findCurrency(baseCode).getBody();
        rate.setBase(currency);
        return rateService.saveOrUpdateRate(rate);
    }
}
