<%-- 
    Document   : updateItem
    Created on : Oct 21, 2024, 3:55:05 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật sản phẩm</title>
    </head>
    <body>
        <h1>Cập nhật sản phẩm</h1>
        <a href="Dashboard.jsp">Trang quản lý</a><br/>
        <form action="UpdateItemServlet" method="POST">
            Mã sản phẩm: <input type="number" name="itemId" required><br/>
            Tên sản phẩm: <input type="text" name="name" required><br/>
            Giá: <input type="number" name="price" required><br/>
            Trạng thái: <select name="statusID">
                <option value="1">In Stock</option>
                <option value="2">Out Stock</option>
            </select><br/>
            Hình ảnh:<input type="text" name="image"><br/>
            <input type="submit" value="Cập nhật">
        </form>
    </body>
</html>
