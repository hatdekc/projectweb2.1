txt<%-- 
    Document   : register
    Created on : Oct 11, 2024, 12:00:35 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang Đăng Ký</title>
    </head>
    <body>
        <h2>Đăng Ký Tài Khoản</h2>
        <a href="Home.jsp">Trang chủ</a><br>
        <form action="RegisterServlet" method="POST">
            <label>Email:</label><br>
            <input type="email" name="txtemail" required/><br>
            
            <label>Tên đăng nhập:</label><br>
            <input type="username" name="txtusername" required/><br>
            
            <label>Mật khẩu:</label><br>
            <input type="password" name="txtpassword" required/><br>
            
            <label>Xác nhận mật khẩu:</label><br>
            <input type="password" name="txtconfirmpassword" required/><br>
            
            <lable>Họ và Tên</lable><br>
            <input type="text" name="txtfullname" required/><br>
            
            <lable>Số Điện Thoại</lable><br>
            <input type="text" name="txtphone" required/><br>
            
            <input type="submit" value="Register"/>
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
