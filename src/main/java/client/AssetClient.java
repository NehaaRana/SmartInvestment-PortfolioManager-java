/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:AssetsResource [Assets]<br>
 * USAGE:
 * <pre>
 *        AssetClient client = new AssetClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class AssetClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/PortfolioManager/resources";

    public AssetClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Assets");
    }

    public void addAsset(String symbol, String name, String type, String current_price, String status, String risk) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addAsset/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{symbol, name, type, current_price, status, risk})).request().post(null);
    }

    public <T> T findAssetsByName(Class<T> responseType, String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findAssetsByName/{0}", new Object[]{name}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeAsset(String aid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeAsset/{0}", new Object[]{aid})).request().delete();
    }

    public void updateAsset(String aid, String symbol, String name, String type, String current_price, String status, String risk) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateAsset/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{aid, symbol, name, type, current_price, status, risk})).request().put(null);
    }

    public <T> T getAllAssets(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findAssetsBySymbol(Class<T> responseType, String symbol) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findAssetsBySymbol/{0}", new Object[]{symbol}));
//        return resource.get(responseType);
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);

    }

    public <T> T findAssetsByType(Class<T> responseType, String type) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findAssetsByType/{0}", new Object[]{type}));
//        return resource.get(responseType);
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);

    }

    public void close() {
        client.close();
    }
    
}
