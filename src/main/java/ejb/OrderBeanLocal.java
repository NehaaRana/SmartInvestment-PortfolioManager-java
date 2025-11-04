/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Orders;
import jakarta.ejb.Local;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Local
public interface OrderBeanLocal {
        void addOrder(Integer uid,Integer aid,String order_type,double units,double amount,String status);
        void updateOrder(Integer oid,Integer uid,Integer aid,String order_type,double units,double amount,String status);
        void removeOrder(Integer oid);
        Collection<Orders> getAllOrders();
        Orders findOrderById(Integer oid);
        Collection<Orders> findOrdersByUser(Integer uid);
        Collection<Orders> findOrdersByAsset(Integer aid);
}
