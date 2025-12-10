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

    public OrderHistoryBean() {
        userClient = new UserClient();
    }

    @PostConstruct
    public void init() {
        userClient = new UserClient(keepRecord.getToken());
        ordersList = new ArrayList<>();
        redemptionList = new ArrayList<>();
        loadData();
    }

    public void loadData() {
        loadUserOrders();
        loadUserRedemptions();
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
//            if (uid != null && !uid.isEmpty()) {
//                Redemptions[] redArray = userClient.getUserRedemptions(Redemptions[].class, uid);
//                redemptionList.clear();
//                if (redArray != null) {
//                    for (Redemptions r : redArray) {
//                        redemptionList.add(r);
//                    }
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public List<Redemptions> getRedemptionList() {
        return redemptionList;
    }
}
