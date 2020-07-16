package springframework.webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import springframework.webapp.model.Rate;

public interface RateRepository extends CrudRepository<Rate, String> {
}
