package springframework.webapp.controllers;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springframework.webapp.model.Rate;
import springframework.webapp.repositories.RateRepository;
import springframework.webapp.services.RateService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RateController {

    private final RateService rateService;
    private final RateRepository rateRepository;

    public RateController(RateService rateService, RateRepository rateRepository) {
        this.rateService = rateService;
        this.rateRepository = rateRepository;
    }

    //@Query("SELECT u FROM User u WHERE u.status = :status and u.name = :name")
    //User findUserByStatusAndNameNamedParams(
    //  @Param("status") Integer status,
    //  @Param("name") String name);

    /*@Query("SELECT Rate FROM Rate r WHERE r.base.baseCode = :baseCode and r.targetCode = :targetCode")
    Rate findRateByBaseCodeAndTargetCode(@Param("baseCode") String baseCode, @Param("targetCode") String targetCode) {
        return null;
    }

    @GetMapping("/currencies/{baseCode}/rates/{targetCode}")
    public Rate getRate(@PathVariable String baseCode, @PathVariable String targetCode){
        return findRateByBaseCodeAndTargetCode(baseCode,targetCode);
    }*/
}
