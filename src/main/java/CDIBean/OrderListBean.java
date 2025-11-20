package CDIBean;

import Entity.Orders;
import client.AdminClient;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import record.keepRecord;

@Named(value = "orderManagedBean")
@ViewScoped
public class OrderListBean implements Serializable {

    @Inject
    private keepRecord keepRecord;

    private Collection<Orders> orderList;

    public Collection<Orders> getOrderList() {
        return orderList;
    }

    @PostConstruct
    public void init() {
        loadOrders();
    }

    public void loadOrders() {
        try {
            System.out.println("=== Loading Orders ===");

            AdminClient ac = new AdminClient(keepRecord.getToken());
            orderList = ac.getAllOrders();

            System.out.println("Loaded " + orderList.size() + " orders");

        } catch (Exception e) {
            System.out.println("Error loading orders: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    public String deleteOrder(String oid) {
//        try {
//            AdminClient ac = new AdminClient(keepRecord.getToken());
//            ac.deleteOrder(oid);
//
//            loadOrders();
//
//            return "Order.xhtml?faces-redirect=true";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public String viewOrder(String oid) {
        return "/Admin/OrderDetails.xhtml?faces-redirect=true&amp;oid=" + oid;
    }
}
