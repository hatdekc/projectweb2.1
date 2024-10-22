/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import dto.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mylib.DBUtils;

/**
 *
 * @author ADMIN
 */
public class OrderDAO {
    
    //Lấy tất cả đơn hàng
    public List<Order> getAllOrders() throws Exception{
        List<Order> list = new ArrayList<>();
        int rs = 0;
        Connection cn = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn!=null){
                String sql = "select orderID, accID, orderDate, statusID, tableID"
                        + "from dbo.Order";
                PreparedStatement st = cn.prepareStatement(sql);
                ResultSet table = st.executeQuery();
                if(table != null){
                    while(table.next()){
                        Order order = new Order();
                        order.setOrderID(table.getInt("orderID"));
                        order.setAccID(table.getInt("accID"));
                        order.setOrderDate(table.getDate("orderDate"));
                        order.setStatusID(table.getInt("statusID"));
                        order.setTableID(table.getInt("tableID"));
                        list.add(order);
                    }
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn != null) cn.close();
             } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    //Lấy Order dựa trên ORDER ID
    public Order getOrderById(int orderID){
        Order order = null;
        Connection cn = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "select orderID, accID, orderDate, statusID, tableID"
                        + "from Order"
                        + "where orderID = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, orderID);
                ResultSet table = st.executeQuery();
                if(table != null){
                    while(table.next()){
                        order = new Order();
                        order.setOrderID(table.getInt("orderID"));
                        order.setAccID(table.getInt("accID"));
                        order.setOrderDate(table.getDate("orderDate"));
                        order.setStatusID(table.getInt("statusID"));
                        order.setTableID(table.getInt("tableID"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn != null) cn.close();
             } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return order;
    }
    
    public int createOrder(int accID, int tableID, HashMap<Item, Integer> cart) {
        int result = 0;
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                // Bắt đầu transaction
                cn.setAutoCommit(false);

                // Chèn đơn hàng mới vào bảng Order
                String sql = "insert into [Order](accID, orderDate, statusID, tableID) VALUES (?, ?, ?, ?)";
                PreparedStatement st = cn.prepareStatement(sql);
                Date orderDate = new Date(System.currentTimeMillis());
                st.setInt(1, accID);
                st.setDate(2, orderDate);
                st.setInt(3, 1); // 1: trạng thái đơn hàng là 'Pending'
                st.setInt(4, tableID);
                result = st.executeUpdate();

                if (result > 0) {
                    // Lấy orderID của đơn hàng vừa chèn
                    sql = "SELECT TOP 1 orderID FROM [Order] ORDER BY orderID DESC";
                    st = cn.prepareStatement(sql);
                    ResultSet rs = st.executeQuery();
                    if (rs != null && rs.next()) {
                        int orderID = rs.getInt("orderID");

                        // Chèn các mặt hàng vào bảng OrderDetails
                        for (Item item : cart.keySet()) {
                            int itemID = item.getItemId();
                            int quantity = cart.get(item);
                            sql = "INSERT INTO OrderDetails(itemID, orderID, quantity) VALUES (?, ?, ?)";
                            st = cn.prepareStatement(sql);
                            st.setInt(1, itemID);
                            st.setInt(2, orderID);
                            st.setInt(3, quantity);
                            result = st.executeUpdate();
                        }
                    }
                }
                // Hoàn tất transaction
                cn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (cn != null) {
                try {
                    // Nếu có lỗi, rollback lại transaction
                    cn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    } 
}
