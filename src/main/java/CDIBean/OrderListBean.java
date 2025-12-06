package CDIBean;

import Entity.Orders;
import client.AdminClient;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
    private int selectedOrderId;
    private String newStatus;

    public int getSelectedOrderId() {
        return selectedOrderId;
    }

    public void setSelectedOrderId(int selectedOrderId) {
        this.selectedOrderId = selectedOrderId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
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
    
    
    
    // Change updateStatus to use these properties
   public String updateStatus() {
        try {
            AdminClient ac = new AdminClient(keepRecord.getToken());
            ac.updateOrderStatus(String.valueOf(selectedOrderId), newStatus);
            loadOrders();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Status Updated to " + newStatus));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
}

    
//    public String updateStatus(int oid, String status) {
//        try {
//            AdminClient ac = new AdminClient(keepRecord.getToken());
//
//            // convert int → string before calling your client
//            ac.updateOrderStatus(String.valueOf(oid), status);
//
//            loadOrders();
//            FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage("Status Updated to " + status));
//
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }





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
