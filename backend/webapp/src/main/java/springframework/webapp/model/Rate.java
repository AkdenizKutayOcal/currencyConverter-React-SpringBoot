package springframework.webapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rate {

    @Id
    private String targetCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "baseCode",nullable = false)
    @JsonBackReference
    private Currency base;

    @NotNull
    private String targetName;
    @NotNull
    private double value;
    private Date date;

    public Rate() {
    }

    public Rate(String targetCode, Currency base, String targetName, double value, Date date) {
        this.targetCode = targetCode;
        this.base = base;
        this.targetName = targetName;
        this.value = value;
        this.date = date;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public Currency getBase() {
        return base;
    }

    public void setBase(Currency base) {
        this.base = base;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rate rate = (Rate) o;

        return targetCode != null ? targetCode.equals(rate.targetCode) : rate.targetCode == null;
    }

    @Override
    public int hashCode() {
        return targetCode != null ? targetCode.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "targetCode='" + targetCode + '\'' +
                ", base=" + base +
                ", targetName='" + targetName + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
