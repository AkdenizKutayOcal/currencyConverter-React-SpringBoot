package currency_converter.backend.services;

import currency_converter.backend.model.Currency;
import currency_converter.backend.model.Rate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CurrencyService {

    List<Currency> findAllCurrencies();

    ResponseEntity<Currency> findCurrency(String baseCode);

    List<Rate> listRates(String baseCode);

    double findValueOf(String baseCode, String targetCode);

    ResponseEntity<Currency> saveOrUpdateCurrency(Currency currency);

    void deleteCurrency(String baseCode);

}