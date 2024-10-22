/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Order {
    private int orderID;
    private int accID;
    private Date orderDate;
    private int statusID;
    private int tableID;
    private List<OrderDetail> listDetails;

    public Order() {
    }

    public Order(int orderID, int accID, Date orderDate, int statusID, int tableID, List<OrderDetail> listDetails) {
        this.orderID = orderID;
        this.accID = accID;
        this.orderDate = orderDate;
        this.statusID = statusID;
        this.tableID = tableID;
        this.listDetails = listDetails;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public List<OrderDetail> getListDetails() {
        return listDetails;
    }

    public void setListDetails(List<OrderDetail> listDetails) {
        this.listDetails = listDetails;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", accID=" + accID + ", orderDate=" + orderDate + ", statusID=" + statusID + ", tableID=" + tableID + ", listDetails=" + listDetails + '}';
    }
    
    
}
