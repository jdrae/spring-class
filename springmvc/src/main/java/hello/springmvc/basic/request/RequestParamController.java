package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody // @Controller 지만 return 이 view 가 아니라 단순 문자열일때
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-v4")
    // String, int, Integer 등의 단순타입이면 @RequestParam 생략 가능(하지만 모호하게됨)
    public String requestParamV3(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    // 필수 파라미터 설정. 디폴트는 true.
    // int 파라미터를 required=false 한다면 int null 이 들어가서 에러. Integer 로 해야.
    // 만약 ?username= 처럼 값을 안넣으면 빈문자열("") 이 들어와서 값이 있는것으로 처리된다.
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age
    ) {
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    // defaultValue 는 빈문자열인 경우에도 디폴트 값이 적용된다.
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age
    ) {
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    // 중복 파라미터가 없을 경우에는 Map 사용 가능.
    // 여러개일경우 MultiValueMap. (key=userIds, value=[id1, id2])
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);
        return helloData.toString();
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    // 파라미터와 객체의 프로퍼티 자동 바인딩.
    // 타입 다를경우 BindException
    // @ModelAttribute 생략 가능
    public String modelAttributeV2(@ModelAttribute HelloData helloData){
        return helloData.toString();
    }


}
