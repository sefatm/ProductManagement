/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */
public class DBConnection {
    static Connection con;
    
    public static Connection getConnection(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/productmgt";
            String user = "root";
            String password = "root";
            
            con = DriverManager.getConnection(url, user, password);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}
