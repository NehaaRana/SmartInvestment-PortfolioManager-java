/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import java.util.Date;
import java.math.BigDecimal;

/**
 *
 * @author User
 */
//@Named(value = "transactionRecord")
//@Dependent
public class TransactionRecord {
    private String assetName;
    private String type; // BUY / SELL
    private BigDecimal units;
    private BigDecimal amount;
    private String status;
    private Date date;

    public TransactionRecord(String assetName, String type, BigDecimal  units, BigDecimal  amount, String status, Date date) {
        this.assetName = assetName;
        this.type = type;
        this.units = units;
        this.amount = amount;
        this.status = status;
        this.date = date;
    }

    // Getters and setters
    public String getAssetName() { return assetName; }
    public String getType() { return type; }
    public BigDecimal  getUnits() { return units; }
    public BigDecimal  getAmount() { return amount; }
    public String getStatus() { return status; }
    public Date getDate() { return date; }
}
