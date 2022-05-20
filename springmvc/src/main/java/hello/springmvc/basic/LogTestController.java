package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        // "=" + name 는 불필요한 연산이 일어나게 된다
        // 아래와 같은 방법은 파라미터를 넘기기 때문에 info 가 호출될 경우에만 연산이 된다.
        log.info(" info log={}", name);

        // @RestController 기 때문에 http 바디에 바다로 입력. viewName 이 아님.
        return "ok";
    }
}
