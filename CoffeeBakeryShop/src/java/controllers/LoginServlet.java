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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

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
            String username = request.getParameter("txtusername");
            String password = request.getParameter("txtpassword");
            boolean rememberMe = "on".equals(request.getParameter("rem"));
           
            //tạo các đối tượng DAO để xử lý logic
            AccountDAO accountDao = new AccountDAO();
            Account userLogin = accountDao.getAccountCredentials(username, password);
            
            
            if(userLogin != null){
               //nếu thông tin hợp lệ, tạo session và lưu thông tin user
                HttpSession session = request.getSession();
                session.setAttribute("LoginUser", userLogin);
                    
                //phân quyền dựa trên roleID
                int roleID = userLogin.getRoleID();
                if(roleID == 1){ //trang admin
                    response.sendRedirect("Dashboard.jsp");
                } else if (roleID == 2) {//trang khách hàng
                    response.sendRedirect("guestHome.jsp");
                }else {//nếu role không xác định
//                    out.println("lỗi đăng nhập đối với username: " + userLogin.getUsername());
                    request.setAttribute("ERROR", "Vai trò tài khoản không xác định");
                    request.getRequestDispatcher("loginpage.jsp").forward(request, response);
                }
            } else { //nếu thông tin đăng nhập không hợp lệ
                request.setAttribute("ERROR", "Thông tin đăng nhập không hợp lệ");
                request.getRequestDispatcher("loginpage.jsp").forward(request, response);
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
