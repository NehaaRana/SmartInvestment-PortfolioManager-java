package CDIBean;

import Entity.Orders;
import Entity.Redemptions;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import record.keepRecord;

@Named(value = "orderHistoryBean")
@SessionScoped
public class OrderHistoryBean implements Serializable {

    @Inject
    private keepRecord keepRecord;

    private UserClient userClient;

    private List<Orders> ordersList;
    private List<Redemptions> redemptionList;
    private List<TransactionRecord> allTransactions;

    public OrderHistoryBean() {
        userClient = new UserClient();
    }

    public List<TransactionRecord> getAllTransactions() {
        return allTransactions;
    }

    public void setAllTransactions(List<TransactionRecord> allTransactions) {
        this.allTransactions = allTransactions;
    }

    @PostConstruct
    public void init() {
        userClient = new UserClient(keepRecord.getToken());
        ordersList = new ArrayList<>();
        redemptionList = new ArrayList<>();
        allTransactions = new ArrayList<>();
        loadData();
    }

    public void loadData() {
        loadUserOrders();
        loadUserRedemptions();
            buildAllTransactions();

    }

    
private void buildAllTransactions() {
    allTransactions.clear();
    for (Orders o : ordersList) {
        allTransactions.add(new TransactionRecord(
                o.getAid().getName(),
                "BUY",
                o.getUnits(),
                o.getAmount(),
                o.getStatus(),
                o.getCreatedAt()
        ));
    }

    for (Redemptions r : redemptionList) {
        allTransactions.add(new TransactionRecord(
                r.getAid().getName(),
                "SELL",
                r.getUnits(),
                r.getRedemptionAmount(),
                r.getStatus(),
                r.getRequestedAt()
        ));
    }
}
    public void loadUserOrders() {
        try {
            String uid = keepRecord.getUid();
            if (uid != null && !uid.isEmpty()) {
                Orders[] orderArray = userClient.getUserOrders(Orders[].class, uid);
                ordersList.clear();
                if (orderArray != null) {
                    for (Orders o : orderArray) {
                        ordersList.add(o);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUserRedemptions() {
        try {
            String uid = keepRecord.getUid();
            if (uid != null && !uid.isEmpty()) {
                Redemptions[] redArray = userClient.getUserRedemptions(new GenericType<Redemptions[]>() {}, uid);
                redemptionList.clear();
                if (redArray != null) {
                    for (Redemptions r : redArray) {
                        redemptionList.add(r);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<String> getUniqueAssets() {
    List<String> assets = new ArrayList<>();

    for (Orders o : ordersList) {
        if (!assets.contains(o.getAid().getName())) {
            assets.add(o.getAid().getName());
        }
    }

    for (Redemptions r : redemptionList) {
        if (!assets.contains(r.getAid().getName())) {
            assets.add(r.getAid().getName());
        }
    }

    return assets;
}

    public List<Orders> getOrdersByAsset(String assetName) {
    List<Orders> list = new ArrayList<>();
    for (Orders o : ordersList) {
        if (o.getAid().getName().equals(assetName)) {
            list.add(o);
        }
    }
    return list;
}

public List<Redemptions> getRedemptionsByAsset(String assetName) {
    List<Redemptions> list = new ArrayList<>();
    for (Redemptions r : redemptionList) {
        if (r.getAid().getName().equals(assetName)) {
            list.add(r);
        }
    }
    return list;
}


    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public List<Redemptions> getRedemptionList() {
        return redemptionList;
    }
}
