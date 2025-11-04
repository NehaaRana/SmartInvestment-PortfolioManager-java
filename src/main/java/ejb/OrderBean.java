/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Assets;
import Entity.Orders;
import Entity.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Admin
 */
@Stateless
public class OrderBean implements OrderBeanLocal {

    @PersistenceContext(unitName="my_pu")
    EntityManager em;
    
    @Override
    public void addOrder(Integer uid, Integer aid, String order_type, double units, double amount, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         Orders order  = new Orders();
         Assets asset = em.find(Assets.class, aid);
         Users user = em.find(Users.class,uid);
         order .setUid(user);
         order .setAid(asset);
         order .setOrderType(order_type);
         order .setUnits(BigDecimal.valueOf(units));
         order .setAmount(BigDecimal.valueOf(amount));
         order .setStatus(status);
         em.persist(order);
    }

    @Override
    public void updateOrder(Integer oid, Integer uid, Integer aid, String order_type, double units, double amount, String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Orders order  = (Orders) em.find(Orders.class, oid);
        Assets asset = em.find(Assets.class, aid);
        Users user = em.find(Users.class,uid);
        order .setAid(asset);
        order .setUid(user);
        order .setOrderType(order_type);
        order .setAmount(BigDecimal.valueOf(amount));
        order .setUnits(BigDecimal.valueOf(units));
        order .setStatus(status);
        em.merge(order);
    }

    @Override
    public void removeOrder(Integer oid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Orders order  = (Orders) em.find(Orders.class,oid);
        if(order !=null){
            em.remove(order);
        }
    }

    @Override
    public Collection<Orders> getAllOrders() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Orders.findAll").getResultList();
    
    }

    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Orders findOrderById(Integer oid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            return em.createNamedQuery("Orders.findByOid", Orders.class)
                     .setParameter("oid", oid)
                     .getSingleResult();
        }catch (NoResultException ex) {
            return null;
        }
    
    }

    @Override
    public Collection<Orders> findOrdersByUser(Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createNamedQuery("Orders.findByUid")
                .setParameter("uid",uid)
                .getResultList();
        
    
    }

    @Override
    public Collection<Orders> findOrdersByAsset(Integer aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
        return em.createNamedQuery("Orders.findByAid")
                .setParameter("aid", aid)
                .getResultList();
    }
}
