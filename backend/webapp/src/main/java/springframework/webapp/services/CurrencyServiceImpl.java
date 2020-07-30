package springframework.webapp.services;

import org.springframework.stereotype.Service;
import springframework.webapp.model.Currency;
import springframework.webapp.model.Rate;
import springframework.webapp.repositories.CurrencyRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService{

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

        try {
            var currencies = new ArrayList<Currency>();
            currencyRepository.findAll().forEach(e -> currencies.add(e));
            return currencies;

        }catch (Exception e){
            System.err.println("Error occurred while finding currencies");
        }
        return null;
    }

    @Override
    public Currency findCurrency(String baseCode) {
        /*
         * Returns Currency with given baseCode
         *
         * @Param: baseCode String
         * @return: Currency
         * */
        try{
            var currency = currencyRepository.findById(baseCode).get();
            if(currency==null){
                //throw exception: bad request no currency with given baseCode
            }
            return currency;
        }
        catch(Exception e){
            System.err.println("Error occurred while finding currencies");
        }
        return null;
    }

    @Override
    public List<Rate> listRates(String baseCode) {
        /*
         * Returns Rate List of currency with given baseCode
         *
         * @Param: baseCode String
         * @return: List<Rate>
         * */

        try {
            var currency = currencyRepository.findById(baseCode).get();
            if(currency==null){
                System.out.println("throw exception: bad request no currency with given baseCode");
            }
            return currency.getRates();

        } catch (Exception e) {
            System.err.println("Error occurred while finding Rates of the Currency with baseCode " + baseCode);
        }
        return null;
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

        try {
            var it = listRates(baseCode);
            for (var rate : it) {
                if (rate.getTargetCode().equals(targetCode)) {
                    return rate.getValue();
                }
            }
            System.out.println("throw exception: bad request no targetCode in RateList");
        } catch (
                Exception e) {
            System.err.println("Error occurred while finding value of the" + targetCode + " with the Currency with baseCode " + baseCode);
        }
        return 0;
    }

    @Override
    public Currency saveOrUpdateCurrency(Currency currency) {
        /*
         * Creates a new Currency or updates the existing one
         *
         * @Param: currency Currency
         * @return: Currency
         * */

        try{
            currencyRepository.save(currency);
            return currency;
        }catch (Exception e){
            System.err.println("Error occurred while saving Currency "+currency);
        }
        return null;
    }

    @Override
    public void deleteCurrency(String baseCode) {
        /*
         * Delete Currency with given baseCode
         *
         * @Param: baseCode String
         * @return: None
         * */

        var currency = findCurrency(baseCode);
        if(currency!=null){
            currencyRepository.delete(currency);
        }
    }
}
