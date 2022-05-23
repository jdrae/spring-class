package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    // 배열이 제공되기 때문에 여러 url 을 넣어도 매핑이 된다
    // 끝에 슬래시(/)가 붙든 안붙든 매핑이 된다
    @RequestMapping({"/hello-basic", "/hello-go"})
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping({"/mapping-get-v2"})
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    // path variable 는 /mapping/userA
    // @PathVariable("userId") String data 가 정석이지만 아래처럼 변수명에 맞춰도 됨
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId={}", userId);
        return userId;
    }

    // 다중매핑
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return userId + " & " + orderId;
    }

    // /mapping-param?mode=debug 처럼 mode=debug 가 있어야 호출
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    // 특정 헤더가 있을 때 호출
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    // 헤더의 content-type 지정. (서버가 보내는 타입)
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }

    // 헤더의 Accept 지정. (클라이언트가 받을 수 있는 타입)
    @PostMapping(value="/mapping-produce", produces= MediaType.TEXT_HTML_VALUE)
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }
}
