package springframework.webapp.services;

import springframework.webapp.model.Currency;
import springframework.webapp.model.Rate;

import java.util.List;

public interface CurrencyService {

    List<Currency> findAllCurrencies();

    Currency findCurrency(String baseCode);

    List<Rate> listRates(String baseCode);

    double findValueOf(String baseCode, String targetCode);

    Currency saveOrUpdateCurrency(Currency currency);

    void deleteCurrency(String baseCode);

}
