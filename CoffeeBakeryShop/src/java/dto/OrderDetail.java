/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author ADMIN
 */
public class OrderDetail {
    private int oDetailID;
    private int orderID;
    private int ItemID;
    private int quantity;
    private double totalPrice;
    private int typeID;

    public OrderDetail() {
    }

    public OrderDetail(int oDetailID, int orderID, int ItemID, int quantity, double totalPrice, int typeID) {
        this.oDetailID = oDetailID;
        this.orderID = orderID;
        this.ItemID = ItemID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.typeID = typeID;
    }

    public int getoDetailID() {
        return oDetailID;
    }

    public void setoDetailID(int oDetailID) {
        this.oDetailID = oDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int ItemID) {
        this.ItemID = ItemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "oDetailID=" + oDetailID + ", orderID=" + orderID + ", ItemID=" + ItemID + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", typeID=" + typeID + '}';
    }
    
    
}
