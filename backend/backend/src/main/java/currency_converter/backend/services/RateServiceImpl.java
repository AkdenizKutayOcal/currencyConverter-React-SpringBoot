package currency_converter.backend.services;

import currency_converter.backend.exceptions.ApiRequestException;
import currency_converter.backend.model.Currency;
import currency_converter.backend.model.Rate;
import currency_converter.backend.repositories.RateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;

    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public List<Rate> findAllRates() {
        /*
         * Returns List of all Rates
         *
         * @Param: None
         * @return: List<Rate>
         * */

        var rates = new ArrayList<Rate>();
        rateRepository.findAll().forEach(e -> rates.add(e));
        if (rates.isEmpty()) {
            throw new ApiRequestException(ApiRequestException.NO_RECORDS_FOUND);
        } else {
            return rates;
        }
    }

    @Override
    public ResponseEntity<Rate> saveOrUpdateRate(Rate rate) {
        /*
         * Creates a new Rate or updates the existing one
         *
         * @Param: rate Rate
         * @return: ResponseEntity<Rate>
         * */

        var targetCode = rate.getTargetCode();

        if (targetCode.isEmpty()||targetCode.length()<3) {
            throw new ApiRequestException(ApiRequestException.VALID + ". for targetCode");
        } else if (rate.getTargetName().isEmpty()) {
            throw new ApiRequestException(ApiRequestException.VALID + ". targetName cannot be null.");
        } else {
            try {
                Rate response = rateRepository.save(rate);
                return ResponseEntity.ok().body(response);
            } catch (Exception e) {
                throw new ApiRequestException(ApiRequestException.WRONG + " while updating rate.");
            }
        }
    }

    @Override
    public void deleteRate(String baseCode, String targetCode) {
        /*
         * Delete Rate with given baseCode and targetCode
         *
         * @Param: baseCode String
         * @return: None
         * */

        var rates = findAllRates();
        for (var rate : rates) {
            if (rate.getTargetCode().equals(targetCode) && rate.getBase().getBaseCode().equals(baseCode)) {
                try {
                    rateRepository.delete(rate);
                } catch (Exception e) {
                    throw new ApiRequestException(ApiRequestException.WRONG + " while deleting rate.");
                }
            }
        }

    }
}
