/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:PayoutResource [Payout]<br>
 * USAGE:
 * <pre>
 *        PayoutClient client = new PayoutClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class PayoutClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/PortfolioManager/resources";

    public PayoutClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Payout");
    }

    public void removePayout(String payId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removePayout/{0}", new Object[]{payId})).request().delete();
    }

    public <T> T findPayoutsByStatus(Class<T> responseType, String status) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findPayoutsByStatus/{0}", new Object[]{status}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addPayout(String rid, String uid, String amount, String referenceNo, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addPayout/{0}/{1}/{2}/{3}/{4}", new Object[]{rid, uid, amount, referenceNo, status})).request().post(null);
    }

    public void updatePayout(String payId, String rid, String uid, String amount, String referenceNo, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatePayout/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{payId, rid, uid, amount, referenceNo, status})).request().put(null);
    }

    public <T> T findPayoutByPayId(Class<T> responseType, String payId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findPayoutByPayId/{0}", new Object[]{payId}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllPayouts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findPayoutsByUser(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findPayoutsByUser/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
