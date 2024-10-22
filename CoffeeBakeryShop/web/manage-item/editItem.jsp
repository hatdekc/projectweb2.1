<%-- 
    Document   : editItem
    Created on : Oct 12, 2024, 3:36:42 AM
    Author     : ADMIN
--%>

<%@page import="dto.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product</title>
    </head>
    <body>
        <h1>Chỉnh sửa sản phẩm</h1>
        <form action="EditItemServlet" method="POST">
            <input type="hidden" name="ItemId" value="<%= ((Item)request.getAttribute("item")).getItemId()%>" />
        Tên sản phẩm: <input type="text" name="ItemName" value="<%=((Item)request.getAttribute("item")).getItemName()%>" required/><br/>
        Giá: <input type="text" name="Price" value="<%= ((Item)request.getAttribute("item")).getPrice()%>" required/><br/>
        Loại sản phẩm: <input type="text" name="TypeID" value="<%= ((Item)request.getAttribute("item")).getTypeID()%>" required/><br/>
        Trạng thái: <input type="text" name="StatusID" value="<%= ((Item)request.getAttribute("item")).getStatusID()%>" required/><br/>
        Hình ảnh: <input type="text" name="Image" value="<%= ((Item)request.getAttribute("item")).getImage()%>" required/><br/>
        <input type="submit" value="Cập nhật sản phẩm"/>
        </form>
    </body>
</html>
