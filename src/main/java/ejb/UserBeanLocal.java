/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Users;
import jakarta.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface UserBeanLocal {
    
    void registerUser(String fullName , String email , String password ,String phone );
  
    Users findByEmail(String email);
}
