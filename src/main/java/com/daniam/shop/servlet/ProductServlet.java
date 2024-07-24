package com.daniam.shop.servlet;

import com.daniam.shop.util.JspHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ProductServlet", urlPatterns = {"/product/create", "/product/list", "/product/update"})
public class ProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/product/create".equals(path)) {

            RequestDispatcher dispatcher = request.getRequestDispatcher(JspHelper.getJspFormat("createProduct"));
            dispatcher.forward(request, response);
        } else if ("/product/update".equals(path)) {

            RequestDispatcher dispatcher = request.getRequestDispatcher(JspHelper.getJspFormat("updateProduct"));
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/product/list".equals(path)) {

            RequestDispatcher dispatcher = request.getRequestDispatcher(JspHelper.getJspFormat("listProducts"));
            dispatcher.forward(request, response);
        }
    }
}
