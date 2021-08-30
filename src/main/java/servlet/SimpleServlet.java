package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/hello"}
)
public class SimpleServlet extends HttpServlet {

    UsersCollection usersCollection = new UsersCollection();

    @Override
    public void init() {
        System.out.println(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter responseBody = resp.getWriter();

        resp.setContentType("text/html");
        responseBody.println("<h1 align=\"center\">We have been visited by: </h1>");
        usersCollection.getUserMap().put(req.getRemoteAddr(), req.getHeader("user-agent"));
        for (ConcurrentHashMap.Entry<String, String> entry : usersCollection.getUserMap().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.equals(req.getRemoteAddr())) {
                responseBody.println("<main align=\"center\"><b>" + key + value + "<b></main>");
            } else {
                responseBody.println("<main align=\"center\">" + key + value + "</main>");
            }

        }
        responseBody.flush();
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }

}
