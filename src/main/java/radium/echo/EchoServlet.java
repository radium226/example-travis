package radium.echo;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "echoServlet", urlPatterns = {"/*"})
public class EchoServlet extends HttpServlet {
    
    public EchoServlet() {
        super();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String text = request.getParameter("text");
        response.getOutputStream().println(
                new StringBuilder("<pre>").append(text).append("</pre>")
            .toString());
    }
    
}
