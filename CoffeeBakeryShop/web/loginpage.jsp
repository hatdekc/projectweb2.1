<%-- 
    Document   : loginpage
    Created on : Oct 10, 2024, 11:12:12 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <a href="Home.jsp">Trang chủ</a><br>
        <form action="LoginServlet">
            Tên đăng nhập: <input type="text" name="txtusername"/>
            </br>
            Mật khẩu: <input type="password" name="txtpassword"/>
            </br>
            <input type="submit" value="login"/>
        </form>
        <%
                String msg = (String)request.getAttribute("ERROR");
                if(msg != null){
                    out.print(msg);
                }
           
        %>

    </body>
</html>
