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

    //Override findAll function
    public ArrayList<Currency> findAll() {

        try {
            var it = currencyRepository.findAll();

            var currencies = new ArrayList<Currency>();
            it.forEach(e -> currencies.add(e));

            return currencies;

        }catch (Exception e){
            System.err.println("Error occurred while finding currencies");
        }
        return null;
    }


    public List<Rate> findRates(String baseCode) {
        /*
        * Returns rates List of currency with give baseCode
        * @Param: String
        * @return: List<Rate>
        * */

        try {
            var currency = currencyRepository.findById(baseCode).get();

            var it = currency.getRates();
            return it;
        } catch (Exception e) {
            System.err.println("Error occurred while finding Rates of the Currency with baseCode " + baseCode);
        }
        return null;
    }

    public double getValue(String baseCode, String targetCode) {
        /*
         * Returns value of given currency's given rate
         * @Param: String, String
         * @return: double
         * */

        try {
            var it = findRates(baseCode);
            for (var rate : it) {
                if (rate.getTargetCode().equals(targetCode)) {
                    return rate.getValue();
                }
            }
        } catch (
                Exception e) {
            System.err.println("Error occurred while finding value of the" + targetCode + " with the Currency with baseCode " + baseCode);
        }

        return 0.0;
    }
}
