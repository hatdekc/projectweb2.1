<%-- 
    Document   : guestHome
    Created on : Oct 13, 2024, 2:33:44 AM
    Author     : ADMIN
--%>

<%@page import="dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang Khách</title>
    </head>
    <body>
        <h1>Trang Khách</h1>
        <%
            session = request.getSession(false);
            Account user = (Account) session.getAttribute("LoginUser");
            if(user == null ||user.getRoleID() != 2){//kiểm tra quyền truy cập của người dùng
                response.sendRedirect("loginpage.jsp");
                return;
            }
        %>
        <h2>
            <% if (user != null) {
                    out.print("Welcome " + user.getUsername() + " comeback");
                } else {
                    request.setAttribute("ERROR", "invalid login");
                    request.getRequestDispatcher("loginpage.jsp").forward(request, response);         
                }
            %>
        </h2>
        <a href="Home.jsp">Trang chủ</a><br/>
        <a href="getITems">Xem sản phẩm</a><br/>
        <a href="searchPage">Tìm kiếm sản phẩm</a><br/>
        <a href="orderPage">Đặt hàng</a><br/>
        <a href="LogoutServlet">Đăng xuất</a><br/>
    </body>
</html>
