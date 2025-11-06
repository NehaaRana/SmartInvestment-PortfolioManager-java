/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:OrderResource [Order]<br>
 * USAGE:
 * <pre>
 *        OrderClient client = new OrderClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class OrderClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/PortfolioManager/resources";

    public OrderClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Order");
    }

    public void removeOrder(String oid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeOrder/{0}", new Object[]{oid})).request().delete();
    }

    public <T> T findOrdersByUser(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findOrderByUser/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addOrder(String uid, String aid, String order_type, String units, String amount, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addOrder/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{uid, aid, order_type, units, amount, status})).request().post(null);
    }

    public void updateOrder(String oid, String uid, String aid, String order_type, String units, String amount, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateOrder/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{oid, uid, aid, order_type, units, amount, status})).request().put(null);
    }

    public <T> T findOrdersByAsset(Class<T> responseType, String aid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findOrderByUser/{0}", new Object[]{aid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findOrderById(Class<T> responseType, String oid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findOrderById/{0}", new Object[]{oid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllOrders(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
