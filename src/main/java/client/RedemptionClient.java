/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:RedemptionResource
 * [Redemption]<br>
 * USAGE:
 * <pre>
 *        RedemptionClient client = new RedemptionClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class RedemptionClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/PortfolioManager/resources";

    public RedemptionClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Redemption");
    }

    public void markAsPaid(String rid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("markAsPaid/{0}", new Object[]{rid})).request().put(null);
    }

    public void approveRedemption(String rid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("approveRedemption/{0}", new Object[]{rid})).request().put(null);
    }

    public <T> T findRedemptionById(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("findRedemptionById");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addRedemption(String uid, String aid, String units, String status, String exit_fee_amt) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addRedemption/{0}/{1}/{2}/{3}/{4}", new Object[]{uid, aid, units, status, exit_fee_amt})).request().post(null);
    }

    public <T> T getAllRedemptions(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllRedemptions");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getRedemptionsByUser(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getRedemptionsByUser/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteRedemption(String rid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteRedemption/{0}", new Object[]{rid})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
