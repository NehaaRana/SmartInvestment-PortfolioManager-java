/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Assets;
import client.AdminClient;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Named(value = "adminDashboardBean")
@RequestScoped
public class AdminDashboardBean {

    /**
     * Creates a new instance of AdminDashboardBean
     */
    @Inject
    private  AdminClient ac;
    
    
    public Collection<Assets> getAllAssets() {
        return ac.getAllAssets();
    }

    
    public long getTotalUsers(){
        return ac.getTotalUsers(Long.class);
    }

    public long getTotalAssets(){
        return ac.getTotalAssets(Long.class);
    }

    public BigDecimal getTotalInvestmentAmount(){
        return ac.getTotalInvestedAmount(BigDecimal.class);
    }

    public BigDecimal getTotalPayouts(){
        return ac.getTotalPayouts(BigDecimal.class);
    }
    

}
