package springframework.webapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springframework.webapp.model.Currency;
import springframework.webapp.model.Rate;
import springframework.webapp.repositories.CurrencyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {

        this.currencyRepository = currencyRepository;
    }

    public ArrayList<Currency> findAll() {

        var it = currencyRepository.findAll();

        var currencies = new ArrayList<Currency>();
        it.forEach(e -> currencies.add(e));

        return currencies;
    }

    public List<Rate> findRates(String baseCode){

        var currency = currencyRepository.findById(baseCode).get();

        var it = currency.getRates();
        return it;
    }

    /*public Currency findByBaseCode(String baseCode){

        Currency temp = (Currency) currencyRepository.findById(baseCode);
        return temp;
    }*/
}
