<%-- 
    Document   : viewOrders
    Created on : Oct 22, 2024, 1:55:05 PM
    Author     : ADMIN
--%>

<%@page import="dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý đơn hàng</title>
    </head>
    <body>
        <h1>Tất cả các đơn hàng</h1>
        <table border = "1">
            <tr>
                <th>Mã sản phẩm</th>
                <th>Mã khách hàng</th>
                <th>Ngày đặt hàng</th>
                <th>Trạng thái đơn hàng</th>
                <th>Bàn số</th>
                <th>Hành động</th>
            </tr>
            <%
                ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
                for (Order order : orders){
            %>
            <tr>
                <td><%= order.getOrderID() %></td>
                <td><%= order.getAccID() %></td>
                <td><%= order.getOrderDate()%></td>
                <td><%= order.getStatusID()%></td>
                <td><%= order.getTableID()%></td>
                <td>
                    <form action="UpdateOrderStatusServlet" method="POST">
                        <input type="hidden" name="orderID" value="<%= order.getOrderID()%>"/>
                        <select name="statusID">
                            <option value="1" <%= order.getStatusID() == 1 ? "selected" :""%>>Pending</option>
                            <option value="1" <%= order.getStatusID() == 2 ? "selected" :""%>>Processing</option>
                            <option value="1" <%= order.getStatusID() == 3 ? "selected" :""%>>Shipping</option>
                            <option value="1" <%= order.getStatusID() == 4 ? "selected" :""%>>Delivered</option>
                            <option value="1" <%= order.getStatusID() == 5 ? "selected" :""%>>Canceled</option>
                        </select>
                        <input type="submit" value="Update"/>
                    </form>
                </td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
