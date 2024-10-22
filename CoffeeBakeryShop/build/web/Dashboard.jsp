<%-- 
    Document   : Dashboard
    Created on : Oct 10, 2024, 11:07:42 PM
    Author     : ADMIN
--%>

<%@page import="dto.Account"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang Quản Lý</title>
    </head>
    <body>
        <%
            session = request.getSession(false);
            Account user = (Account) session.getAttribute("LoginUser");
            if (user == null || user.getRoleID() != 1) {//kiểm tra quyền truy cập của người dùng
                response.sendRedirect("loginpage.jsp");
                return;
            }
        %>
        <h2>
            <% if (user != null) {
                    out.print("Chào mừng " + user.getUsername() + ", bạn đang ở trang Quản lý");
                } else {
                    request.setAttribute("ERROR", "invalid login");
                    request.getRequestDispatcher("loginpage.jsp").forward(request, response);         
                }
            %>
        </h2>
        <h1>Dashboard</h1>
        <p>
            <a href="Home.jsp">Trang chủ</a><br/>
            <a href="viewOrders.jsp">Xem và Cập nhật đơn hàng</a><br/>
            <a href="reportPage">Xem báo cáo doanh thu</a><br/>
        </p>
        <h3>QUẢN LÝ SẢN PHẨM</h3>
        <a href="Itempage_M.jsp">Xem sản phẩm</a><br/>
        <a href="addItem.jsp">Thêm sản phẩm</a><br/>
        <a href="updateItem.jsp">Cập nhật sản phẩm</a><br/>
        
        
        <a href="LogoutServlet">Đăng xuất</a>
    </body>
</html>
