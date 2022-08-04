package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/putServlet")
public class PutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("code");
        int code = Integer.parseInt(sid);

        String name = request.getParameter("name");
        String price = request.getParameter("price");

        Employee employee = new Employee();
        employee.setCode(code);
        employee.setName(name);
        employee.setPrice(price);
        employee.setAvailability(request.getParameter("availability"));

        int status = EmployeeRepository.update(employee);

        if (status > 0) {
            response.sendRedirect("viewServlet");
        } else {
            out.println("Sorry! unable to update record");
        }
        out.close();
    }
}