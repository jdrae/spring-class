package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 스프링이 자동으로 스프링 빈으로 등록(내부에 @Component)
// RequestMappingHandlerMapping 에서 꺼낼 수 있는 애노테이션 기반 컨트롤러로 인식(@RequestMapping)
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){ // 애노테이션 기반이기 때문에 메소드 이름 무관
        return new ModelAndView("new-form");
    }
}
