package springframework.webapp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String baseName;
    private String baseCode;


    private Set<Rate> rates;

    public Currency() {
    }

    public Currency(String baseName, String baseCode) {
        this.baseName = baseName;
        this.baseCode = baseCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }


    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", baseName='" + baseName + '\'' +
                ", baseCode='" + baseCode + '\'' +
                ", rates=" + rates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        return id != null ? id.equals(currency.id) : currency.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
