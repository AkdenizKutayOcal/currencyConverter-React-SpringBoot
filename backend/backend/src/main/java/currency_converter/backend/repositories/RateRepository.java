package currency_converter.backend.repositories;

import currency_converter.backend.model.Rate;
import org.springframework.data.repository.CrudRepository;

public interface RateRepository extends CrudRepository<Rate, String> {
}
