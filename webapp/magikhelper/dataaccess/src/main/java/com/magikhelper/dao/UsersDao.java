/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magikhelper.dao;

import java.util.List;

import com.magikhelper.entities.User;

public interface UsersDao extends GenericDao<User, Integer> {
    public static final String name = "usersDao";

    public List<Object[]> getVendorWithSkills(Integer vendorId);
    
    public List<User> getCleints(Integer clientId);
    
    public User loginUser(String email, String password);
}
