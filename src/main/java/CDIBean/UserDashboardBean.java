/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBean;

import Entity.Orders;
import Entity.Payouts;
import Entity.Portfolio;
import Entity.Users;
import client.UserClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import record.keepRecord;

/**
 *
 * @author Admin
 */
@Named(value = "userDashboardBean")
@SessionScoped
public class UserDashboardBean implements  Serializable{

    /**
     * Creates a new instance of UserDashboardBean
     */
    
    @Inject
    private keepRecord keepRecord;
    
    UserClient userClient;
    
    private Object profile;
    private Object portfolio;
    private Object goals;
    private Object orders;
    private Object payments;
    private Object transactions;
    
    
    // Dashboard Summary Fields
    private BigDecimal totalPortfolioValue= BigDecimal.ZERO;
    private BigDecimal profitLoss = BigDecimal.ZERO;
    private BigDecimal todayChange = BigDecimal.ZERO;
    private long completedOrders;
    private BigDecimal totalInvested;

    public BigDecimal getTotalInvested() {
        return totalInvested;
    }

    public void setTotalInvested(BigDecimal totalInvested) {
        this.totalInvested = totalInvested;
    }
    private BigDecimal totalReturns = BigDecimal.ZERO;

    
    @Inject
    PortfolioBean portfolioBean;
    
    @Inject
    PayoutBean payoutBean;


    public BigDecimal getTotalPortfolioValue() {
        return totalPortfolioValue;
    }

    public void setTotalPortfolioValue(BigDecimal totalPortfolioValue) {
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public BigDecimal getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(BigDecimal profitLoss) {
        this.profitLoss = profitLoss;
    }

    public BigDecimal getTodayChange() {
        return todayChange;
    }

    public void setTodayChange(BigDecimal todayChange) {
        this.todayChange = todayChange;
    }

    public BigDecimal getTotalReturns() {
        if (payoutBean != null) {
            totalReturns = payoutBean.getTotalReturns();
        }
        return totalReturns;

    }
    
    public void setTotalReturns(BigDecimal totalReturns) {
        this.totalReturns = totalReturns;
    }

    
    public long getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(long completedOrders) {
        this.completedOrders = completedOrders;
    }
    
    @Inject
    private ProfileBean profileBean;

    public ProfileBean getProfileBean() {
        return profileBean;
    }

    public void setProfileBean(ProfileBean profileBean) {
        this.profileBean = profileBean;
    }

    public UserDashboardBean() {
    }
    public String getUserId() {
        // Returns the current logged-in user's ID
        return keepRecord.getUid();
    }

//    @PostConstruct
//    public void init(){
//        try{
//            System.out.println("INIT START");
//            userClient = new UserClient(keepRecord.getToken());
//            String token = keepRecord.getToken();
//            String uid = keepRecord.getUid();
//            
//            System.out.println("Token= " + token);
//            System.out.println("UID= " + uid);
//            
////            UserClient uc = new UserClient(token);
//            
//            Users u = userClient.getProfile(Users.class, uid);
//            
//            this.profile = u; // set to dashboard
//            System.out.println("User loaded: " + u.getFullName());
//            
//        }catch(Exception e){
//            System.out.println("ERROR IN init(): " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
    
    @PostConstruct
    public void init() {
        try {
            userClient = new UserClient(keepRecord.getToken());
            Users u = userClient.getProfile(Users.class, keepRecord.getUid());
            this.profile = u;

            calculateDashboard(); // <-- calculate totals from portfolioBean
            
            
            if (keepRecord.getUid() != null) {
                int uidInt = Integer.parseInt(keepRecord.getUid());
                payoutBean.loadUserPayouts(uidInt); // ensure payouts are loaded
            }

        

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
//    public void calculateDashboard() {
//        try {
//            List<Portfolio> list = portfolioBean.getPortfolioList();
//            List<Orders> ordersList = portfolioBean.getPortfolioOrders();
//
//            BigDecimal totalValue = BigDecimal.ZERO;
//            BigDecimal totalInvested = BigDecimal.ZERO;
//
//            for (Portfolio p : list) {
//                if (p.getCurrentValue() != null) {
//                    totalValue = totalValue.add(p.getCurrentValue());
//                }
//
//                if (p.getAvgPrice() != null && p.getUnits() != null) {
//                    totalInvested = totalInvested.add(
//                        p.getAvgPrice().multiply(p.getUnits())
//                    );
//                }
//            }
//
//            totalPortfolioValue = totalValue;
//            // PROFIT LOSS = total current value − total invested
//
//            profitLoss = totalValue.subtract(totalInvested);
//
//            // Completed Orders
//            completedOrders = ordersList.stream()
//                .filter(o -> "PAID".equalsIgnoreCase(o.getStatus()))
//                .count();
//
//            // Dummy Today PL
//            todayChange = profitLoss.divide(BigDecimal.valueOf(10), 2, BigDecimal.ROUND_HALF_UP);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    
//    public void calculateDashboard() {
//        try {
//            List<Portfolio> list = portfolioBean.getPortfolioList();
//            List<Orders> ordersList = portfolioBean.getPortfolioOrders();
//
//            BigDecimal totalValue = BigDecimal.ZERO;
//            BigDecimal totalInvested = BigDecimal.ZERO;
//
//            for (Portfolio p : list) {
//
//                // total current value = units × live price
//                BigDecimal units = p.getUnits();
//                BigDecimal currentPrice = p.getAid().getCurrentPrice();
//                BigDecimal currentValue = units.multiply(currentPrice);
//
//                totalValue = totalValue.add(currentValue);
//
//                // total invested = DB current_value column
//                if (p.getCurrentValue() != null) {
//                    totalInvested = totalInvested.add(p.getCurrentValue());
//                }
//            }
//
//            this.totalPortfolioValue = totalValue;
//            this.totalInvested = totalInvested;
//
//            // Profit Loss
//            profitLoss = totalValue.subtract(totalInvested);
//
//            // Completed orders
//            completedOrders = ordersList.stream()
//                    .filter(o -> "PAID".equalsIgnoreCase(o.getStatus()))
//                    .count();
//
//            todayChange = profitLoss.divide(BigDecimal.valueOf(10), 2, BigDecimal.ROUND_HALF_UP);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    
    public void calculateDashboard() {
        try {
            List<Portfolio> list = portfolioBean.getPortfolioList();
            List<Orders> ordersList = portfolioBean.getPortfolioOrders();

            BigDecimal totalValue = BigDecimal.ZERO;      // current value
            BigDecimal totalInvested = BigDecimal.ZERO;   // actual money user paid

            // 1️⃣ Total Current Value
            for (Portfolio p : list) {
                BigDecimal currentVal = p.getUnits().multiply(p.getAid().getCurrentPrice());
                totalValue = totalValue.add(currentVal);
            }

            // 2️⃣ Total Invested (from Orders table)
            for (Orders o : ordersList) {
                if ("PAID".equalsIgnoreCase(o.getStatus())) {
                    totalInvested = totalInvested.add(o.getAmount());
                }
            }

            this.totalPortfolioValue = totalValue;
            this.totalInvested = totalInvested;

            // 3️⃣ Profit/Loss
            this.profitLoss = totalValue.subtract(totalInvested);

            // 4️⃣ Completed Orders Count
            completedOrders = ordersList.stream()
                .filter(o -> "PAID".equalsIgnoreCase(o.getStatus()))
                .count();

            // 5️⃣ Dummy today change
            todayChange = profitLoss.divide(BigDecimal.valueOf(10), 2, BigDecimal.ROUND_HALF_UP);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    
//    public void calculateTotalReturns() {
//        try {
//            totalReturns = BigDecimal.ZERO;
//
//            Collection<Payouts> payouts = payoutBean.getUserPayoutList();
//
//            if (payouts != null) {
//                for (Payouts p : payouts) {
//
//                    // Only count PAID payouts
//                    if ("PAID".equalsIgnoreCase(p.getStatus())) {
//
//                        if (p.getAmount() != null) {
//                            totalReturns = totalReturns.add(p.getAmount());
//                        }
//                    }
//                }
//            }
//
//            System.out.println("Total Returns Calculated = " + totalReturns);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    
    
    
    
    
    public void loadDashboardData() {
        try {

            userClient = new UserClient(keepRecord.getToken());
            String uid = keepRecord.getUid();

            // API calls
            profile = userClient.getProfile(Object.class, uid);
            portfolio = userClient.getUserPortfolio(Object.class, uid);
//            goals = userClient.getUserGoals(Object.class, uid);
            goals = userClient.getUserGoals(
                        new jakarta.ws.rs.core.GenericType<java.util.Collection<Entity.Goals>>() {},
                        uid
                    );
            orders = userClient.getUserOrders(Object[].class, uid);
            payments = userClient.getUserPayments(Object.class, uid);
            transactions = userClient.getUserTransactions(Object.class, uid);

            // ==============================
            //   SAFE HANDLING FOR PORTFOLIO
            // ==============================
            // ---- SAFE PORTFOLIO HANDLING (BIGDECIMAL CORRECTED) ----
             if (portfolio instanceof Map) {

                Map map = (Map) portfolio;

                // Total Portfolio Value
                Object tv = map.get("totalValue");
                if (tv != null) {
                    totalPortfolioValue = new BigDecimal(tv.toString());
                }

                // Profit Loss
                Object invest = map.get("investedAmount");
                if (invest != null) {
                    BigDecimal investedAmt = new BigDecimal(invest.toString());
                    profitLoss = totalPortfolioValue.subtract(investedAmt);
                }

                // Today PL
                Object tpl = map.get("todayPL");
                if (tpl != null) {
                    todayChange = new BigDecimal(tpl.toString());
                }
            } else {
                System.out.println("Portfolio is not a map → " + portfolio.getClass());
            }


            // ==============================
            //   SAFE HANDLING FOR ORDERS
            // ==============================
            completedOrders = 0; // default

            if (orders instanceof Object[]) {
                Object[] arr = (Object[]) orders;

                completedOrders = Arrays.stream(arr)
                        .filter(o -> (o instanceof Map))
                        .map(o -> (Map) o)
                        .filter(m -> {
                            Object status = m.get("status");
                            return status != null && "COMPLETED".equalsIgnoreCase(status.toString());
                        })
                        .count();
            }
            System.out.println("Dashboard Data Loaded Successfully");

        } catch (Exception e) {
            System.out.println("ERROR in loadDashboardData(): " + e.getMessage());
            e.printStackTrace();
        }
    }


    
    
//    public void loadDashboardData() {
//        String uid = keepRecord.getUid();
//
//        profile = userClient.getProfile(Object.class, uid);
//        portfolio = userClient.getUserPortfolio(Object.class, uid);
//        goals = userClient.getUserGoals(Object.class, uid);
//        orders = userClient.getUserOrders(Object[].class, uid);
//        payments = userClient.getUserPayments(Object.class, uid);
//        transactions = userClient.getUserTransactions(Object.class, uid);
//    }
    
    public Object getProfile() {
        return profile;
    } 

    public Object getPortfolio() {
        return portfolio;
    }

    public Object getGoals() {
        return goals;
    }

    public Object getOrders() {
        return orders;
    }

    public Object getPayments() {
        return payments;
    }

    public Object getTransactions() {
        return transactions;
    }

    public void logout() {
        try {
            // Reset keepRecord
            if (keepRecord != null) {
                keepRecord.reset();
            }

            // Invalidate session
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }

            // Redirect to Login.jsf at root
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Login.jsf"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}