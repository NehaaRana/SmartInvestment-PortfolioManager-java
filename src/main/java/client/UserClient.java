/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import Filter.MyFilter;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import java.text.MessageFormat;

/**
 * Jersey REST client generated for REST resource:UserResource [User]<br>
 * USAGE:
 * <pre>
 *        UserClient client = new UserClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class UserClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://localhost:8181/PortfolioManager/resources";

    public UserClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("User");
    }
    
    public UserClient(String token) {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
//        client.register(new MyRestFilter(token));  // SAFE
        if(token != null){
            client.register(new MyFilter(token));
        }
        webTarget = client.target(BASE_URI).path("User");

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
    
    public void requestRedemption(String uid, String aid, String units, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("requestRedemption/{0}/{1}/{2}/{3}", new Object[]{uid, aid, units, status})).request().post(null);
    }

    public void makePayment(String uid, String tid, String method, String referenceNo, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("makePayment/{0}/{1}/{2}/{3}/{4}", new Object[]{uid, tid, method, referenceNo, status})).request().post(null);
    }

    public void updatePortfolioValue(String uid, String aid, String currentValue) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatePortfolioValue/{0}/{1}/{2}", new Object[]{uid, aid, currentValue})).request().put(null);
    }
    
    public void addOrUpdatePortfolio(String uid, String aid, String units, String amount) throws ClientErrorException {
    webTarget.path(MessageFormat.format(
            "addOrUpdatePortfolio/{0}/{1}/{2}/{3}", uid, aid, units, amount))
            .request()
            .post(null);
    }

//    public void updateProfile(String uid, String fullName, String phone, String password) throws ClientErrorException {
//        webTarget.path(java.text.MessageFormat.format("updateProfile/{0}/{1}/{2}/{3}", new Object[]{uid, fullName, phone, password})).request().put(null);
//    }
    
    public void updateProfile(String uid, String fullName, String phone, String password) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateProfile/{0}/{1}/{2}/{3}", new Object[]{uid, fullName, phone, password})).request().put(jakarta.ws.rs.client.Entity.text(""));
    }

    public void placeOrder(String uid, String aid, String orderType, String units, String amount) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("placeOrder/{0}/{1}/{2}/{3}/{4}", new Object[]{uid, aid, orderType, units, amount})).request().post(null);
    }

    public <T> T getAssetById(Class<T> responseType, String aid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAssetById/{0}", new Object[]{aid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserTransactions(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserTransactions/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getProfile(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProfile/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserPortfolio(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserPortfolio/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserGoals(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserGoals/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserWatchlist(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserWatchlist/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addToWatchlist(String uid, String aid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addToWatchlist/{0}/{1}", new Object[]{uid, aid})).request().post(null);
    }

//    public void recordTransaction(String oid, String uid, String amount, String txnType, String status) throws ClientErrorException {
//        webTarget.path(java.text.MessageFormat.format("recordTransaction/{0}/{1}/{2}/{3}/{4}", new Object[]{oid, uid, amount, txnType, status})).request().post(null);
//    }
    
    
    public <T> T recordTransaction(
        Class<T> responseType,
            String oid, String uid, String amount, String txnType, String status) {

        return webTarget.path(MessageFormat.format(
                "recordTransaction/{0}/{1}/{2}/{3}/{4}",
                oid, uid, amount, txnType, status
        ))
        .request(MediaType.APPLICATION_JSON)
        .post(null, responseType);
    }

    
    public void updateGoalProgress(String goalId, String currentAmt) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateGoalProgress/{0}/{1}", new Object[]{goalId, currentAmt})).request().put(null);
    }

    public <T> T getUserOrders(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserOrders/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserPayments(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserPayments/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
    
    public boolean updateOrderStatus(String oid, String status) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(MessageFormat.format(
                "updateOrderStatus/{0}/{1}", 
                new Object[]{oid, status}
        ));

        return resource
                .request(MediaType.APPLICATION_JSON)
                .get(Boolean.class);
    }


    public <T> T getUserRedemptions(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserRedemptions/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void createGoal(String uid, String goalName, String targetAmt, String targetDate) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("createGoal/{0}/{1}/{2}/{3}", new Object[]{uid, goalName, targetAmt, targetDate})).request().post(null);
    }

    public <T> T getUserPayouts(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserPayouts/{0}", new Object[]{uid}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeFromWatchlist(String wid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeFromWatchlist/{0}", new Object[]{wid})).request().delete();
    }

    public <T> T findByEmail(Class<T> responseType, String email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findByEmail/{0}", new Object[]{email}));
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllAssets(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllAssets");
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void register(String fullName, String email, String rawPassword, String phone) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("register/{0}/{1}/{2}/{3}", new Object[]{fullName, email, rawPassword, phone})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
