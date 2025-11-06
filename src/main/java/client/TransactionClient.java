/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:TransactionResource
 * [Transaction]<br>
 * USAGE:
 * <pre>
 *        TransactionClient client = new TransactionClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class TransactionClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/PortfolioManager/resources";

    public TransactionClient() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Transaction");
    }

    public <T> T getAllTransactions(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(jakarta.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateTransaction(String tid, String oid, String uid, String amount, String txn_type, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateTransaction/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{tid, oid, uid, amount, txn_type, status})).request().put(null);
    }

    public void addTransaction(String oid, String uid, String txn_type, String amount, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addTransaction/{0}/{1}/{2}/{3}/{4}", new Object[]{oid, uid, txn_type, amount, status})).request().post(null);
    }

    public void deleteTransaction() throws ClientErrorException {
        webTarget.path("deleteTransaction").request().delete();
    }

    public void close() {
        client.close();
    }
    
}
