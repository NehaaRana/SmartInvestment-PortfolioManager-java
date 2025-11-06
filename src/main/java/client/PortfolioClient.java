/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:PortfolioResource
 * [portfolio]<br>
 * USAGE:
 * <pre>
 *        PortfolioClient client = new PortfolioClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class PortfolioClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/PortfolioManager/resources";

    public PortfolioClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("portfolio");
    }

    public <T> T findPortfolioById(Class<T> responseType, String pf_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findPortfolioById/{0}", new Object[]{pf_id}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllPortfolio(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updatePortfolio(String uid, String aid, String units, String avgPrice) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatePortfolio/{0}/{1}/{2}/{3}", new Object[]{uid, aid, units, avgPrice})).request().put(null);
    }

    public <T> T findPortfolioByAsset(Class<T> responseType, String aid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findPortfolioByAsset/{0}", new Object[]{aid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findPortfolioByUser(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findPortfolioByUser/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removePortfolio(String pf_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deletePortfolio/{0}", new Object[]{pf_id})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
