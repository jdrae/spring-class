package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 스프링 빈의 이름을 url 로 하면 호출이 된다.
// url 을 받으면 BeanNameUrlHandlerMapping 에서 OldController 를 찾는다.
// Controller 인터페이스를 실행할 수 있는 SimpleControllerHandlerAdapter 를 찾고 실행된다.
@Component("/springmvc/old-controller")
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
