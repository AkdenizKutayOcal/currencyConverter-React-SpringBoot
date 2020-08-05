package currency_converter.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rates")
public class Rate {

    @Id
    private Long id;

    @NotNull
    private String targetCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "baseCode",nullable = false)
    @JsonBackReference
    private Currency base;

    @NotNull
    private String targetName;
    @NotNull
    private double value;
    private String date;

    public Rate() {
    }

    public Rate(String targetCode, Currency base, String targetName, double value, String date) {
        this.targetCode = targetCode;
        this.base = base;
        this.targetName = targetName;
        this.value = value;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rate rate = (Rate) o;

        return id != null ? id.equals(rate.id) : rate.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", targetCode='" + targetCode + '\'' +
                ", baseCode=" + base.getBaseCode() +
                ", targetName='" + targetName + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}