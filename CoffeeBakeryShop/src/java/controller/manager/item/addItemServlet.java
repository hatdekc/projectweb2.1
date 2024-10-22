/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager.item;

import dao.ItemDAO;
import dto.Item;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class addItemServlet extends HttpServlet {

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
            try{
            String itemName = request.getParameter("txtitemname");
            int price = Integer.parseInt(request.getParameter("txtprice"));
            int typeID = Integer.parseInt(request.getParameter("txttypeid"));
            int statusID = Integer.parseInt(request.getParameter("txtstatusid"));
            String image = request.getParameter("txtimage");
            
            if(itemName == null || itemName.isEmpty() || price <= 0 || typeID <= 0 || statusID <= 0 || image == null){
                request.setAttribute("error", "Thông tin không đầy đủ!");
                request.getRequestDispatcher("addItem.jsp").forward(request, response);
                return;//Dừng xử lý nếu dữ liệu không hợp lệ
            }
            
            //gọi DAO để thêm sản phẩm vào database
            Item newItem = new Item(itemName, price, typeID, statusID, image);
            ItemDAO dao = new ItemDAO();
            int result = dao.createItem(image, price, typeID, statusID, image);
            //kiểm tra kết quả và phản hồi người dùng
            if(result > 0){
                response.sendRedirect("viewItem.jsp");//nếu thành công chuyển hướng về trang xem sản phẩm
            } else {
                request.setAttribute("ERROR", "Tạo sản phẩm mới thất bại.");
                request.getRequestDispatcher("addItem.jsp").forward(request, response);
            }
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("ERROR", "Có lỗi xảy ra: "+ e.getMessage());
            request.getRequestDispatcher("addItem.jsp").forward(request, response);
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
