/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mylib.DBUtils;

/**
 *
 * @author ADMIN
 */
public class AccountDAO {
    
    public Account getAccount(String username, String password){
        Account acc = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "select [accId],[fullname],[email],[password],[phone],[statusID],[createdDate],[roleID]\n"
                        + "from dbo.Account\n"
                        + "where email=? and password = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, username);
                st.setString(2, password);
                ResultSet table = st.executeQuery();
                if(table != null){
                    while(table.next()) {
                        acc = new Account();
                        acc.setAccID(table.getInt("accID"));
                        acc.setFullname(table.getString("fullname"));
                        acc.setEmail(table.getString("email"));
                        acc.setUsername(table.getString("username"));
                        acc.setPassword(table.getString("password"));
                        acc.setPhone(table.getString("phone"));
                        acc.setStatusID(table.getInt("statusID"));
                        acc.setCreatedDate(table.getDate("createdDate"));
                        acc.setRoleID(table.getInt("roleID"));
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
    } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return acc;
    }
    
//    public int insertAccount(String fullname, String email, String password) {
//        int rs = 0;
//        Connection cn = null;
//        try {
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (cn != null) {
//                    cn.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return rs;
//    }
    
    //phương thức lấy tài khoản theo email
    public Account getAccountByEmail(String email) {
        Account account = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from Account where email=?";
        try {
            cn = DBUtils.makeConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if(rs.next()){
                account = new Account(rs.getInt("accID"), rs.getString("fullname"), rs.getString("email"), rs.getString("username"), rs.getString("password"),
                        rs.getString("phone"), rs.getInt("statusID"), rs.getDate("createdDate"), rs.getInt("roleID"));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
		if (cn != null) {
		    cn.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
        }
        return account;
}
    
    //phương thức lấy tài khoản theo username
    public Account getAccountByUsername(String username){
        Account account = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try{
            cn = DBUtils.makeConnection();
            String sql = "select * from Account where username=?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            
            if(rs.next()){
                //nếu có tài khoản với username đã cho
                account = new Account(
                    rs.getInt("accID"),
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getInt("statusID"),
                    rs.getDate("createdDate"),
                    rs.getInt("roleID"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
		if (cn != null) {
		    cn.close();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
        }
        return account;
    }
    
    //phương thức đăng ký tài khoản
    public boolean registerAccount(Account account){
        Connection cn = null;
        PreparedStatement pst = null;
        String sql = "insert into Account(fullname, email, username, password, phone, statusID, createdDate, roleID)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            cn = DBUtils.makeConnection();
            pst = cn.prepareStatement(sql);
            pst.setString(1, account.getFullname());
            pst.setString(2, account.getEmail());
            pst.setString(3, account.getUsername());
            pst.setString(4, account.getPassword());
            pst.setString(5, account.getPhone());
            pst.setInt(6, account.getStatusID());
            pst.setDate(7, account.getCreatedDate());
            pst.setInt(8, account.getRoleID());
            int rowsAffected = pst.executeUpdate();
            
            if(rowsAffected > 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
		if (cn != null) {
		    cn.close();
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
        }
        return false;
    }
    
    public Account getAccountCredentials(String username, String password){
        Account account = null;
        try{
            //kết nối với database và truy vấn dữ liệu tài khoảng
            Connection cn = DBUtils.getConnection();
            
            //truy vấn cơ sở dữ liệu
            String sql = "select * from Account where username=? and password=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                account = new Account(rs.getInt("accID"),
                                      rs.getString("fullname"),
                                      rs.getString("email"),
                                      rs.getString("username"),
                                      rs.getString("password"),
                                      rs.getString("phone"),
                                      rs.getInt("statusID"),
                                      rs.getDate("createdDate"),
                                      rs.getInt("roleID"));
            }
            
            //đóng tài nguyên
            rs.close();
            pst.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
    
}

