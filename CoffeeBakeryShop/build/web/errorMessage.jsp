<%-- 
    Document   : errorMessage
    Created on : Oct 12, 2024, 2:49:32 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông báo lỗi</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 50px;
            }
            .error-container {
                border: 1px solid #f00;
                padding: 20px;
                background-color: #fee;
            }
            h1 {
                color: #f00;
            }
            p {
                font-size: 18px;
            }
            a {
                color: #007bff;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div>
            <h1>Có lỗi xảy ra</h1>
            <p><%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "Đã xảy ra lỗi không xác định."%></p>
            <p><a href="Home.jsp">Quay lại trang chủ</a></p>
        </div>
    </body>
</html>
