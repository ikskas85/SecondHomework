package JDBC.servlets;

import JDBC.dto.EmployeeDto;
import JDBC.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@WebServlet("/update-employee")
public class UpdateEmployeeServlet extends HttpServlet {
    private static final EmployeeService INSTANCE = EmployeeService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/updateEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var map = req.getParameterMap();
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        boolean result = INSTANCE.updateEmployee(new EmployeeDto(
                uuid,
                Arrays.toString(map.get("firstName")),
                Arrays.toString(map.get("role")),
                Arrays.toString(map.get("email")),
                Arrays.toString(map.get("companyName"))));
        if (result) {
            resp.getWriter().write("<H1>Employee Updated Successfully</H1>");
        } else {
            resp.getWriter().write("<H1>Employee Not Updated</H1>");
        }
    }
}