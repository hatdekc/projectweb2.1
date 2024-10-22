<%-- 
    Document   : addItem
    Created on : Oct 21, 2024, 3:39:56 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo sản phẩm mới</title>
    </head>
    <body>
        <h1>Thêm sản phẩm</h1>
        <a href="Dashboard.jsp">Trang quản lý</a>
        <form action="addItemServlet" method="POST">
            <label for="txtitemname">Tên sản phẩm:</label>
            <input type="text" id="txtitemname" name="txtitemname" required><br/>
            
            <label for="txtprice">Giá:<label/>
            <input type="number" id="txtprice" name="txtprice" required><br/>
            
            <label for="txttypeid">Loại sản phẩm:</label>
            <select id="txttypeid" name="txtypeid" required>
                <option value="1">Drink</option>
                <option value="2">Food</option>
            </select><br/>
            
            <label for="txtstatusid">Trạng thái:</label>
            <select name="txtstatusid">
                <option value="1">In Stock</option>
                <option value="2">Out Stock</option>
            </select><br/>
            
            <label for="txtimage">Hình ảnh:</label>
            <input type="text" id="txtimage" name="txtimage" required><br/>
            <input type="submit" value="Thêm sản phẩm">
        </form>
        <p>
            <%
                String msg = (String)request.getAttribute("ERROR");
                if(msg != null){
                    out.print(msg);
                }
            %>
        </p>
    </body>
</html>
