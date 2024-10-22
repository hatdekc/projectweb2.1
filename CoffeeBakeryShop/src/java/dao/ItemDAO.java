/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mylib.DBUtils;

/**
 *
 * @author ADMIN
 */
public class ItemDAO {
    
    //TẠO MỚI, THÊM SẢN PHẨM
    public int createItem(String name, int price, int type, int status, String imagepath){
        int rs = 0;
        Connection cn = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "insert dbo.Items (ItemName, Price, typeID, statusID, Image) values (?,?,?,?,?)";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, name);
                st.setInt(2, price);
                st.setInt(3, type);
                st.setInt(4, status);
                st.setString(5, imagepath);
                rs = st.executeUpdate();
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
        return rs;
    }
    
    //CẬP NHẬT SẢN PHẨM
    public boolean updateItem(int itemId, String name, int price, int statusID, String image){
        Connection cn = null;
        boolean result = false;
        try{
            cn = DBUtils.makeConnection();
            String sql = "update Items set ItemName=?, Price=?, typeID=?, statusID=?, Image=?"
                    + "where ItemId";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, price);
            st.setInt(3, statusID);
            st.setString(4, image);
            st.setInt(5, itemId);
            int rows = st.executeUpdate();
            result = (rows > 0);
        }catch (Exception e) {
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
        return result;
    }
    
    //XÓA SẢN PHẨM
    public boolean deleteItem(int itemId){
        Connection cn = null;
        boolean success = false;
        try{
           cn = DBUtils.makeConnection();
           String sql = "delete from Items where ItemId=?";
           PreparedStatement st = cn.prepareStatement(sql);
           st.setInt(1, itemId);
           int rows = st.executeUpdate();
           success = (rows > 0);
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
        return success;
    }
    
    
    
    //lấy tất cả các item dựa vào TÊN SẢN PHẨM
    public ArrayList<Item> getAllItemByName(String name){
        ArrayList<Item> list = new ArrayList<>();
        Connection cn = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "select ItemId, ItemName, Price, typeID, statusID, Image\n"
                        + "from dbo.Items\n"
                        + "where ItemName like ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, "%" + name + "%");
                ResultSet table = st.executeQuery();
                if(table != null){
                    while(table.next()){
                        Item t = new Item();
                        t.setItemId(table.getInt("ItemId"));
                        t.setItemName(table.getString("ItemName"));
                        t.setPrice(table.getInt("Price"));
                        t.setTypeID(table.getInt("typeID"));
                        t.setStatusID(table.getInt("statusID"));
                        t.setImage(table.getString("Image"));
                        list.add(t);
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
    
    //lấy tất cả các món dựa trên TYPEID(1: Drink, 2: Food)
    public List<Item> getItemsByType(int typeID) throws SQLException, ClassNotFoundException{
        List<Item> items = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "select ItemId, ItemName, Price, typeID, statusID, Image\n"
                        + "from dbo.Items\n"
                        + "where typeID=?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, typeID);
                ResultSet table = st.executeQuery();
                if (table != null){
                    while(table.next()){
                        Item t = new Item();
                        t.setItemId(table.getInt("ItemId"));
                        t.setItemName(table.getString("ItemName"));
                        t.setPrice(table.getInt("Price"));
                        t.setTypeID(table.getInt("typeID"));
                        t.setStatusID(table.getInt("statusID"));
                        t.setImage(table.getString("Image"));
                        items.add(t);
                    }
                }
            }
        }
           catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(cn != null) cn.close();
             } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return items; 
            
        }

    public List<Item> getAllItems(){
        List<Item> itemList = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            String sql = "select itemName, price, typeID, statusID, image"
                    + "from Item";
            
            ps = cn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                String itemName = rs.getString("itemName");
                int price = rs.getInt("price");
                int typeID = rs.getInt("typeID");
                int statusID = rs.getInt("statusID");
                String image = rs.getString("image");
                
                Item item = new Item(typeID, itemName, price, typeID, statusID, image);
                itemList.add(item);
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
        return itemList; 
    }
    
}

