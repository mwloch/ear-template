/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author marcin.wloch
 */
public class ExampleTable {
    private Integer tableID;
    private String charField;
    private Integer intField;
    private Timestamp dateTimeField;
    private BigDecimal moneyField;
    private Boolean booleanField;
    private Date dateField;

    public ExampleTable() {
    }

    public ExampleTable(Integer tableID, String charField, Integer intField, Timestamp dateTimeField, BigDecimal moneyField, Boolean booleanField, Date dateField) {
        this.tableID = tableID;
        this.charField = charField;
        this.intField = intField;
        this.dateTimeField = dateTimeField;
        this.moneyField = moneyField;
        this.booleanField = booleanField;
        this.dateField = dateField;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.tableID);
        hash = 53 * hash + Objects.hashCode(this.charField);
        hash = 53 * hash + Objects.hashCode(this.intField);
        hash = 53 * hash + Objects.hashCode(this.dateTimeField);
        hash = 53 * hash + Objects.hashCode(this.moneyField);
        hash = 53 * hash + Objects.hashCode(this.booleanField);
        hash = 53 * hash + Objects.hashCode(this.dateField);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExampleTable other = (ExampleTable) obj;
        if (!Objects.equals(this.charField, other.charField)) {
            return false;
        }
        if (!Objects.equals(this.tableID, other.tableID)) {
            return false;
        }
        if (!Objects.equals(this.intField, other.intField)) {
            return false;
        }
        if (!Objects.equals(this.dateTimeField, other.dateTimeField)) {
            return false;
        }
        if (!Objects.equals(this.moneyField, other.moneyField)) {
            return false;
        }
        if (!Objects.equals(this.booleanField, other.booleanField)) {
            return false;
        }
        if (!Objects.equals(this.dateField, other.dateField)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExampleTable{" + "tableID=" + tableID + ", charField=" + charField + ", intField=" + intField + ", dateTimeField=" + dateTimeField + ", moneyField=" + moneyField + ", booleanField=" + booleanField + ", dateField=" + dateField + '}';
    }

    public Integer getTableID() {
        return tableID;
    }

    public void setTableID(Integer tableID) {
        this.tableID = tableID;
    }

    public String getCharField() {
        return charField;
    }

    public void setCharField(String charField) {
        this.charField = charField;
    }

    public Integer getIntField() {
        return intField;
    }

    public void setIntField(Integer intField) {
        this.intField = intField;
    }

    public Timestamp getDateTimeField() {
        return dateTimeField;
    }

    public void setDateTimeField(Timestamp dateTimeField) {
        this.dateTimeField = dateTimeField;
    }

    public BigDecimal getMoneyField() {
        return moneyField;
    }

    public void setMoneyField(BigDecimal moneyField) {
        this.moneyField = moneyField;
    }

    public Boolean getBooleanField() {
        return booleanField;
    }

    public void setBooleanField(Boolean booleanField) {
        this.booleanField = booleanField;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }
    
    
    
}
