package currency_converter.backend.repositories;

import currency_converter.backend.model.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, String> {
}