
package com.product.service;

import com.product.model.ProductUI;
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
    
    public int save(ProductUI p) throws SQLException{
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
    
    public int update(ProductUI p) throws SQLException{
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
    
    public ProductUI getByID(int pcode) throws SQLException{
        con = DBConnection.getConnection();
        String sql = "select * from products where pcode = (?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, pcode);
        ResultSet rs = ps.executeQuery();
        ProductUI p = new ProductUI();
        while(rs.next()){
            p.setPname(rs.getString("pname"));
            p.setQty(rs.getInt("qty"));
            p.setPrice(rs.getDouble("price"));
        }
        return p;
    }

    public List<ProductUI> getAll() throws SQLException{
        con = DBConnection.getConnection();
        String sql = "select * from products";
        PreparedStatement ps = con.prepareStatement(sql);
        List products = new ArrayList();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            ProductUI p = new ProductUI();
            p.setPcode(rs.getInt("pcode"));
            p.setPname(rs.getString("pname"));
            p.setQty(rs.getInt("qty"));
            p.setPrice(rs.getDouble("price"));
            products.add(p);
        }
        return products;
    }
}
