package springframework.webapp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.webapp.model.Currency;
import springframework.webapp.model.Rate;
import springframework.webapp.repositories.CurrencyRepository;
import springframework.webapp.repositories.RateRepository;

import java.util.Date;

@Component
public class data implements CommandLineRunner {

    @Autowired
    private final CurrencyRepository currencyRepository;
    @Autowired
    private final RateRepository rateRepository;

    public data(CurrencyRepository currencyRepository, RateRepository rateRepository) {
        this.currencyRepository = currencyRepository;
        this.rateRepository = rateRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Currency eur = new Currency("EUR","Euro");
        Currency tl = new Currency("TRY","Turkish Lira");
        Rate euro_tl = new Rate("TRY",eur,"Turkish Lira",7.80556,new Date());
        Rate euro_usd = new Rate("USD",eur,"US Dollar",1.13797,new Date());
        Rate euro_gbp = new Rate("GBP",eur,"British Pound",0.906362,new Date());

        currencyRepository.save(eur);
        currencyRepository.save(tl);
        rateRepository.save(euro_tl);
        rateRepository.save(euro_usd);
        rateRepository.save(euro_gbp);

        eur.addRate(euro_gbp);
        eur.addRate(euro_tl);
        eur.addRate(euro_usd);

    }
}




