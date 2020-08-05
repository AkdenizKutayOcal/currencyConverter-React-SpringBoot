package currency_converter.backend.services;


import currency_converter.backend.model.Rate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RateService {

    List<Rate> findAllRates();

    ResponseEntity<Rate> saveOrUpdateRate(Rate rate);

    void deleteRate(String baseCode, String targetCode);
}
