package JDBC.servlets;

import JDBC.services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet {
    private static final EmployeeService employeeService = EmployeeService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.write("<H3>Список сотрудников</H3>");
            out.write("<ul>");
            employeeService.getAllEmployees().forEach(flightDto -> {
                out.write("""
                        <li>
                            %s %s %s %s
                        </li>
                        """.formatted(flightDto.uuid().toString(), flightDto.firstName(), flightDto.role(), flightDto.email())
                );
            });
            out.write("</ul>");
        }
    }
}
