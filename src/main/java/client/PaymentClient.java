/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:PaymentResource [Payment]<br>
 * USAGE:
 * <pre>
 *        PaymentClient client = new PaymentClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class PaymentClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/PortfolioManager/resources";

    public PaymentClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Payment");
    }

    public <T> T getPaymentByStatus(Class<T> responseType, String status) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getPaymentByStatus/{0}", new Object[]{status}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllPayments(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getPaymentByUser(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getPaymentByUser/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findPaymentById(Class<T> responseType, String pid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findPaymentById/{0}", new Object[]{pid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addPayment(String uid, String tid, String method, String reference_no, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addPayment/{0}/{1}/{2}/{3}/{4}", new Object[]{uid, tid, method, reference_no, status})).request().post(null);
    }

    public void updatePayment(String pid, String tid, String method, String reference_no, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatePayment/{0}/{1}/{2}/{3}/{4}", new Object[]{pid, tid, method, reference_no, status})).request().put(null);
    }

    public void deletePayment(String pid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deletePayment/{0}", new Object[]{pid})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
