<%-- 
    Document   : viewItem
    Created on : Oct 21, 2024, 4:11:57 AM
    Author     : ADMIN
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.Item"%>
<%@page import="dto.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách sản phẩm</title>
    </head>
    <body>
        <h1>Danh sách sản phẩm</h1>
        <p><a href="Dashboard.jsp">Trang quản lý</a> |
           <a href="ViewCart.jsp">Xem giỏ hàng</a> |
           <a href="ViewWishlistServlet">Sản phẩm yêu thích</a> |
        <table border="1">
            <tr>
                <th>Mã sản phẩm</th>
                <th>Tên sản phẩm</th>
                <th>Giá</th>
                <th>Loại</th>
                <th>Trạng thái</th>
                <th>Hình ảnh</th>
            </tr>
            <%
            ArrayList<Item> list = (ArrayList<Item>) request.getAttribute("itemList");
            if (list != null) {
                for (Item item : list) {
            %>
            <tr>
                <td><%=item.getItemId()%></td>
                <td><%=item.getItemName()%></td>
                <td><%=item.getPrice()%></td>
                <td><%=item.getTypeID()%></td>
                <td><%=item.getStatusID()%></td>
                <td><img src="<%=item.getImage()%>" width="100"></td>
            </tr>
            
            <%
                   }
                }
            %>
        </table>
    </body>
</html>
