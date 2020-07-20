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

        try{
            Currency eur = new Currency("EUR","Euro");

            Rate euro_tl = new Rate("TRY",eur,"Turkish Lira",7.83355,new Date());
            Rate euro_usd = new Rate("USD",eur,"US Dollar",1.14233,new Date());
            Rate euro_gbp = new Rate("GBP",eur,"British Pound",0.909280,new Date());

            currencyRepository.save(eur);
            rateRepository.save(euro_tl);
            rateRepository.save(euro_usd);
            rateRepository.save(euro_gbp);
            eur.addRate(euro_gbp);
            eur.addRate(euro_tl);
            eur.addRate(euro_usd);

            Currency tl = new Currency("TRY","Turkish Lira");

            Rate tl_eur = new Rate("EUR",tl,"Euro",0.127651,new Date());
            Rate tl_usd = new Rate("USD",tl,"US Dollar",0.145827,new Date());
            Rate tl_gbp = new Rate("GBP",tl,"British Pound",0.116068,new Date());

            currencyRepository.save(tl);
            rateRepository.save(tl_eur);
            rateRepository.save(tl_usd);
            rateRepository.save(tl_gbp);
            tl.addRate(tl_usd);
            tl.addRate(tl_gbp);
            tl.addRate(tl_eur);

            Currency usd = new Currency("USD","US Dollar");

            Rate usd_tl = new Rate("TRY",usd,"Turkish Lira",6.85780,new Date());
            Rate usd_eur = new Rate("EUR",usd,"Euro",0.875243,new Date());
            Rate usd_gbp = new Rate("GBP",usd,"British Pound",0.796029,new Date());

            currencyRepository.save(usd);
            rateRepository.save(usd_tl);
            rateRepository.save(usd_eur);
            rateRepository.save(usd_gbp);
            usd.addRate(usd_gbp);
            usd.addRate(usd_eur);
            usd.addRate(usd_tl);

            Currency gbp = new Currency("GBP","British Pound");

            Rate gbp_tl = new Rate("TRY",gbp,"Turkish Lira",8.61429,new Date());
            Rate gbp_eur = new Rate("EUR",gbp,"Euro",1.09947,new Date());
            Rate gbp_usd = new Rate("USD",gbp,"US Dollar",1.25620,new Date());

            currencyRepository.save(gbp);
            rateRepository.save(gbp_tl);
            rateRepository.save(gbp_eur);
            rateRepository.save(gbp_usd);
            gbp.addRate(gbp_tl);
            gbp.addRate(gbp_eur);
            gbp.addRate(gbp_usd);
        }
        catch (Exception e){
            System.err.println("Error occurred while inserting the data");
        }

    }
}




