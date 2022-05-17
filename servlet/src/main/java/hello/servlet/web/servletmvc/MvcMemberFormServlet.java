package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // 서버 내부에서 호출하여 다른 서블릿이나 JSP 이동(클라이언트가 인지 불가. url 그대로)
        // /servlet-mvc/members/new-form 가 /WEB-INF/views/new-form.jsp 를 불러오나
        // /WEB-INF 안에 파일이 있으면 직접 new-form.jsp 를 url 으로 호출할 수는 없다.
        dispatcher.forward(request, response);
    }
}
