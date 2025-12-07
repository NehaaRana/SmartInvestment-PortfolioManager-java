/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Redemptions;
import client.AdminClient;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import record.keepRecord;

/**
 *
 * @author User
 */
@Named(value = "redemptionBean")
@ViewScoped
public class RedemptionBean implements Serializable {

    @Inject
    private keepRecord keepRecord;
    private AdminClient adminClient;

    private Collection<Redemptions> redemptionList;
    
    public Collection<Redemptions> getRedemptionList() {
        return redemptionList;
    }

    
    /**
     * Creates a new instance of RedemptionBean
     */
    public void setRedemptionList(Collection<Redemptions> redemptionList) {
        this.redemptionList = redemptionList;
    }

    @PostConstruct
    public void init() {
        String token = keepRecord.getToken();
        adminClient = new AdminClient(token);
        loadRedemptions();
    }

    public void loadRedemptions() {
        redemptionList = adminClient.getAllRedemptions();
    }
    
    public void approve(Redemptions r){
        try{
            adminClient.approveRedemption(r.getRid().toString());
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("redemption approved"));
            loadRedemptions();
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error:"+e.getMessage()));
        }
    }
    
    public void reject(Redemptions r){
        try{
            adminClient.rejectRedemption(r.getRid().toString());
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("redemption rejected"));
            loadRedemptions();
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error:"+e.getMessage()));
        }
    }
    
    public RedemptionBean() {
    }
    
}
