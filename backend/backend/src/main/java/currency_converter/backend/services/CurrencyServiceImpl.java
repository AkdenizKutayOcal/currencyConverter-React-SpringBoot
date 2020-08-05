package currency_converter.backend.services;

import currency_converter.backend.exceptions.ApiRequestException;
import currency_converter.backend.model.Currency;
import currency_converter.backend.model.Rate;
import currency_converter.backend.repositories.CurrencyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;


    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> findAllCurrencies() {
        /*
         * Returns List of all Currencies
         *
         * @Param: None
         * @return: List<Currency>
         * */

        var currencies = new ArrayList<Currency>();
        currencyRepository.findAll().forEach(e -> currencies.add(e));
        if (currencies.isEmpty()) {
            throw new ApiRequestException(ApiRequestException.NO_RECORDS_FOUND);
        } else {
            return currencies;
        }
    }

    @Override
    public ResponseEntity<Currency> findCurrency(String baseCode) {
        /*
         * Returns Currency with given baseCode
         *
         * @Param: baseCode String
         * @return: ResponseEntity<Currency>
         * */

        Optional<Currency> currency = currencyRepository.findById(baseCode);
        if (currency.isPresent()) {
            return ResponseEntity.ok().body(currency.get());
        } else {
            throw new ApiRequestException(ApiRequestException.NO_RECORDS_FOUND + " with currency code " + baseCode);
        }
    }

    @Override
    public List<Rate> listRates(String baseCode) {
        /*
         * Returns Rate List of currency with given baseCode
         *
         * @Param: baseCode String
         * @return: List<Rate>
         * */

        Optional<Currency> currency = currencyRepository.findById(baseCode);
        if (currency.isPresent()) {

            var valid_currency = currency.get();
            var rates = valid_currency.getRates();

            if (rates.isEmpty()) {
                //this might not be true and should be handled in client side
                throw new ApiRequestException(ApiRequestException.NO_RECORDS_FOUND + ". No rate data has inserted.");
            } else {
                return rates;
            }

        } else {
            throw new ApiRequestException(ApiRequestException.NO_RECORDS_FOUND + " with currency code " + baseCode);
        }

    }

    @Override
    public double findValueOf(String baseCode, String targetCode) {
        /*
         * Returns value of given currency's given rate
         *
         * @Param: baseCode String
         * @Param: targetCode String
         * @return: the value double
         * */


        var it = listRates(baseCode);
        for (var rate : it) {
            if (rate.getTargetCode().equals(targetCode)) {
                return rate.getValue();
            }
        }
        throw new ApiRequestException(ApiRequestException.NO_RECORDS_FOUND + " with target currency code " + targetCode);

    }

    @Override
    public ResponseEntity<Currency> saveOrUpdateCurrency(Currency currency) {
        /*
         * Creates a new Currency or updates the existing one
         *
         * @Param: currency Currency
         * @return: Currency
         * */

        if (currency.getBaseCode().length() < 3) {
            throw new ApiRequestException(ApiRequestException.VALID + ". baseCode should be minimum 3 char long.");
        } else if (currency.getBaseName().isEmpty()) {
            throw new ApiRequestException(ApiRequestException.VALID + ". baseName cannot be null.");
        } else {
            try {
                Currency response = currencyRepository.save(currency);
                return ResponseEntity.ok().body(response);
            } catch (Exception e) {
                throw new ApiRequestException(ApiRequestException.WRONG + " while updating currency.");
            }
        }
    }

    @Override
    public void deleteCurrency(String baseCode) {
        /*
         * Delete Currency with given baseCode
         *
         * @Param: baseCode String
         * @return: None
         * */

        Optional<Currency> currency = currencyRepository.findById(baseCode);
        if (currency.isPresent()) {
            try {
                currencyRepository.delete(currency.get());
            } catch (Exception e) {
                throw new ApiRequestException(ApiRequestException.WRONG + " while deleting currency.");
            }

        } else {
            throw new ApiRequestException(ApiRequestException.NO_RECORDS_FOUND + " with currency code " + baseCode);
        }
    }
}