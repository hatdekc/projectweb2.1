/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AccountDAO;
import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //lay thong tin tu form dang ky
            String email = request.getParameter("txtemail");
            String username = request.getParameter("txtusername");
            String password = request.getParameter("txtpassword");
            String confirmpassword = request.getParameter("txtconfirmpassword");
            String fullname = request.getParameter("txtfullname");
            String phone = request.getParameter("txtphone");
            
            //kiem tra xem mat khau va xac nhan mat khau co khop khong
            if(!password.equals(confirmpassword)){
                request.setAttribute("ERROR", "Mật khẩu không khớp");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            
            //kiem tra xem email da duoc su dung chua
            AccountDAO dao = new AccountDAO();
            Account existingUser = dao.getAccountByEmail(email);
            if(existingUser != null){
                request.setAttribute("ERROR", "Địa chỉ Email đã được sử dụng");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            
            //kiểm tra xem username đã được sử dụng chưa
            Account existingUsernameUser = dao.getAccountByUsername(username);
            if(existingUsernameUser != null){
                request.setAttribute("ERROR", "Tên đăng nhập đã được sử dụng");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
            
            //thiet lap gia tri mac dinh
            int accID = 0; // Giá trị sẽ được tự động tăng trong cơ sở dữ liệu
            int statusID = 1; // Giả sử 1 là trạng thái hoạt động
            Date createdDate = Date.valueOf(LocalDate.now());
            int roleID = 2; // Giả sử 2 là vai trò người dùng thông thường
            
            //tao account khach hang va luu vao database
            Account newAccount = new Account(accID, fullname, email, username, password, phone, statusID, createdDate, roleID);
            
            boolean isRegistered = dao.registerAccount(newAccount);//tao account khach hang moi
            if(isRegistered){
                response.sendRedirect("loginpage.jsp");
            } else {
                request.setAttribute("ERROR","Đăng ký thất bại! Vui lòng thử lại.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
