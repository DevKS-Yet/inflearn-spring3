package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")  // name과 urlPatterns는 중복되면 안된다.
public class HelloServlet extends HttpServlet {

    // ctrl + o 를 누른 후 service 중에서 자물쇠가 있는 메서드
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("HelloServlet.service");  // soutm ; method
        System.out.println("req = " + req);  // soutv ; value
        System.out.println("resp = " + resp);

        String username = req.getParameter("username");
        System.out.println("username = " + username);

        resp.setContentType("text/plain");  // http header
        resp.setCharacterEncoding("utf-8");  // http header
        resp.getWriter().write("hello " + username);  // http body

    }
}
