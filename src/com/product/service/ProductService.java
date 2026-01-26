/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product.service;

import com.product.model.Product;
import com.product.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ProductService {
    
    Connection con;
    
    public int save(Product p) throws SQLException{
        con = DBConnection.getConnection();
        String sql = "insert into products (pcode, pname, qty, price) values (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, p.getPcode());
        ps.setString(2, p.getPname());
        ps.setInt(3, p.getQty());
        ps.setDouble(4, p.getPrice());
        int status = ps.executeUpdate();
        return status;
    }
    
    public int update(Product p) throws SQLException{
        con = DBConnection.getConnection();
        String sql = "update products set pname = ?, qty = ? , price = ? where pcode = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getPname());
        ps.setInt(2, p.getQty());
        ps.setDouble(3, p.getPrice());
        ps.setInt(4, p.getPcode());
        int status = ps.executeUpdate();
        return status;
    }

    public int delete(int pcode) throws SQLException{
        con = DBConnection.getConnection();
        String sql = "delete from products where pcode = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, pcode);
        int status = ps.executeUpdate();
        return status;
    }
    
    public Product getByID(int pcode) throws SQLException{
        con = DBConnection.getConnection();
        String sql = "select * from products where pcode = (?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, pcode);
        ResultSet rs = ps.executeQuery();
        Product p = new Product();
        while(rs.next()){
            p.setPname(rs.getString("pname"));
            p.setQty(rs.getInt("qty"));
            p.setPrice(rs.getDouble("price"));
        }
        return p;
    }

    public List<Product> getAll() throws SQLException{
        con = DBConnection.getConnection();
        String sql = "select * from products";
        PreparedStatement ps = con.prepareStatement(sql);
        List products = new ArrayList();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Product p = new Product();
            p.setPname(rs.getString("pname"));
            p.setQty(rs.getInt("qty"));
            p.setPrice(rs.getDouble("price"));
            products.add(p);
        }
        return products;
    }
}
