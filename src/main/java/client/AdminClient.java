/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import Entity.Assets;
import Entity.Orders;
import Entity.Users;
import Filter.MyFilter;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Jersey REST client generated for REST resource:AdminResource [Admin]<br>
 * USAGE:
 * <pre>
 *        AdminClient client = new AdminClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class AdminClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://localhost:8181/PortfolioManager/resources";

    public AdminClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Admin");
    }
    
    public AdminClient(String token){
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        client.register(new MyFilter(token));
        webTarget = client.target(BASE_URI).path("Admin");
    }
    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {

            public boolean verify(String hostname,
                    javax.net.ssl.SSLSession sslSession) {
                if (hostname.equals("localhost")) {
                    return true;
                }
                return false;
            }
        });
    }
    
    public <T> T getAllTransactions(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllTransactions");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addPayout(String rid, String uid, String amount, String referenceNo, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addPayout/{0}/{1}/{2}/{3}/{4}", new Object[]{rid, uid, amount, referenceNo, status})).request().post(null);
    }

    public void addUser(String fullName, String email, String password, String phone, String gid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addUser/{0}/{1}/{2}/{3}/{4}", new Object[]{fullName, email, password, phone, gid})).request().post(null);
    }

    public <T> T getTotalAssets(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getTotalAssets");
//        return resource.get(responseType);
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

//    public <T> T getAllOrders(Class<T> responseType) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path("getAllOrders");
//        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
//    }
    public Collection<Orders> getAllOrders() throws ClientErrorException { 
    WebTarget resource = webTarget.path("getAllOrders");
    return resource
            .request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
            .get(new jakarta.ws.rs.core.GenericType<Collection<Orders>>() {}); 
}

    public Collection<Assets> getAllAssetsPublic() throws ClientErrorException {
        WebTarget resource = webTarget.path("getAllAssetsPublic");

        return resource
                .request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
                .get(new jakarta.ws.rs.core.GenericType<Collection<Assets>>() {});
    }

    public void approveRedemption(String rid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("approveRedemption/{0}", new Object[]{rid})).request().put(null);
    }

    public <T> T findUserById(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findUserById/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllRedemptions(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllRedemptions");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addAsset(String symbol, String name, String type, String currentPrice, String status, String risk) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addAsset/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{symbol, name, type, currentPrice, status, risk})).request().post(null);
    }
    
    public void updateAsset(int aid, String symbol, String name, String type,
                        String currentPrice, String status, String risk) 
                        throws ClientErrorException {
        webTarget
            .path(java.text.MessageFormat.format(
                "updateAsset/{0}/{1}/{2}/{3}/{4}/{5}/{6}",
                new Object[]{aid, symbol, name, type, currentPrice, status, risk}
            ))
            .request()
            .put(Entity.json("{}"));
    }


    public void deleteAsset(int aid) throws ClientErrorException {
    webTarget
        .path(java.text.MessageFormat.format("deleteAsset/{0}", new Object[]{aid}))
        .request()
        .delete();
    }

//    public <T> T getAllUsers(Class<T> responseType) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
//    }
    public Collection<Users> getAllUsers() throws ClientErrorException {
    WebTarget resource = webTarget;
    return resource
            .request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
            .get(new jakarta.ws.rs.core.GenericType<Collection<Users>>() {});
}


    public <T> T getAllPayments(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllPayments");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updatePayoutStatus(String payId, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatePayoutStatus/{0}/{1}", new Object[]{payId, status})).request().put(null);
    }

    public void updateAssetStatus(String aid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateAssetStatus/{0}/{1}", new Object[]{aid, status})).request().put(null);
    }

    public <T> T getAllPayouts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllPayouts");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updatePaymentStatus(String pid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatePaymentStatus/{0}/{1}", new Object[]{pid, status})).request().put(null);
    }

    public void updateOrderStatus(String oid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateOrderStatus/{0}/{1}", new Object[]{oid, status})).request().put(null);
    }

    public void deleteUser(String uid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteUser/{0}", new Object[]{uid})).request().delete();
    }

    public void rejectRedemption(String rid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("rejectRedemption/{0}", new Object[]{rid})).request().put(null);
    }

    public <T> T getTotalInvestedAmount(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getTotalInvestedAmount");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
//        return resource.get(responseType);
    }

    public <T> T getTotalPayouts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getTotalPayouts");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
//        return resource.get(responseType);
    }

    public void updateAssetPrice(String aid, String newPrice) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateAssetPrice/{0}/{1}", new Object[]{aid, newPrice})).request().put(null);
    }

    public void updateTransactionStatus(String tid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateTransactionStatus/{0}/{1}", new Object[]{tid, status})).request().put(null);
    }

    public <T> T getTotalUsers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getTotalUsers");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
//        return resource.get(responseType);
    }

//    public <T> T getAllAssets(Class<T> responseType) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path("getAllAssets");
//        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
//    }

    public Collection<Assets> getAllAssets() {
        WebTarget resource = webTarget.path("getAllAssets");
        return resource.request(MediaType.APPLICATION_JSON)
                       .get(new GenericType<Collection<Assets>>() {});
    }

    public <T> T findAssetById(Class<T> responseType, String aid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findAssetById/{0}", new Object[]{aid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
