/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
public class UserBean implements UserBeanLocal {
    @PersistenceContext(unitName="my_pu")
    EntityManager em;
    @Override
    public void registerUser(String fullName, String email, String password, String phone) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          Users u = new Users();
          u.setFullName(fullName);
          u.setEmail(email);
          u.setPassword(password);
          u.setPhone(phone);
          em.persist(u);
          
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Users findByEmail(String email) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       try{
           return em.createNamedQuery("Users.findByEmail",Users.class)
                   .setParameter("email", email)
                   .getSingleResult();
       }catch(NoResultException ex){
            return null;
       }
    }
}
