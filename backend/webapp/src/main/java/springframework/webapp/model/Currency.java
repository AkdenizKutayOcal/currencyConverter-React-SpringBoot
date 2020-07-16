package springframework.webapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.*;

@Entity
public class Currency {

    @Id
    private String baseCode;

    @NotNull
    private String baseName;

    @OneToMany(mappedBy = "base")
    @JsonManagedReference
    private List<Rate> rates = new ArrayList<Rate>();

    public Currency() {
    }

    public Currency(String baseCode, String baseName) {
        this.baseCode = baseCode;
        this.baseName = baseName;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String id) {
        this.baseCode = id;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    //Maybe Not ?
    public void addRate(Rate rate){
        this.rates.add(rate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        return baseCode != null ? baseCode.equals(currency.baseCode) : currency.baseCode == null;
    }

    @Override
    public int hashCode() {
        return baseCode != null ? baseCode.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + baseCode + '\'' +
                ", baseName='" + baseName + '\'' +
                ", rates=" + rates +
                '}';
    }
}
