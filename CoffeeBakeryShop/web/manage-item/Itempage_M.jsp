<%-- 
    Document   : Itempage_M
    Created on : Oct 12, 2024, 1:41:40 AM
    Author     : ADMIN
--%>

<%@page import="dao.ItemDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1>Danh sách sản phẩm</h1>
        <a href="addItem.jsp">Thêm sản phẩm mới</a><br/><br/>
        
        <%
            try {
                ItemDAO dao = new ItemDAO();
                List<Item> itemList = dao.getAllItems();
                
                if (itemList != null && !itemList.isEmpty()) {
        %>
        
        <table border="1">
            <thead>
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Loại sản phẩm</th>
                    <th>Trạng thái</th>
                    <th>Hình ảnh</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Duyệt qua từng sản phẩm và hiển thị trên bảng
                    for (Item item : itemList) {
                %>
                <tr>
                    <td><%= item.getItemName() %></td>
                    <td><%= item.getPrice() %></td>
                    <td>
                        <%= item.getTypeID() == 1 ? "Drink" : "Food" %>
                    </td>
                    <td>
                        <%= item.getStatusID() == 1 ? "In Stock" : "Out Stock" %>
                    </td>
                    <td>
                        <%
                            String imageUrl = item.getImage();
                            if (imageUrl != null && !imageUrl.isEmpty()) {
                        %>
                        <img src="<%= imageUrl %>" alt="Image" width="100" height="100">
                        <%
                            } else {
                                out.print("Không có hình ảnh");
                            }
                        %>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        
        <%
                } else {
                    out.print("Hiện tại chưa có sản phẩm nào.");
                }
            } catch (Exception e) {
                out.print("Có lỗi xảy ra: " + e.getMessage());
            }
        %>
    </body>
</html>
