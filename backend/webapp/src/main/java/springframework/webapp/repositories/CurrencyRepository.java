package springframework.webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.webapp.model.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, String> {
}
