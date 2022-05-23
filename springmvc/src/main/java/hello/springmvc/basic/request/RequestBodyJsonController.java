package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        response.getWriter().write(helloData.toString());
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        return helloData.toString();
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    // @RequestBody 를 생략하면 @ModelAttribute 가 적용되기 때문에 요청파라미터를 찾고,
    // 파라미터가 아닌 body 로 넘어왔기 때문에 helloData 의 프로퍼티는 null.
    public String requestBodyJsonV3(@RequestBody HelloData helloData){

        return helloData.toString();
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity){
        HelloData helloData = httpEntity.getBody();

        return helloData.toString();
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    // HelloData 객체 직접 반환하면 json 으로 변환되어 반환.
    // 클라이언트 json -> @RequestBody 객체 -> @ResponseBody json
    // @RequestBody 와 @ResponseBody 에 HTTP 메세지 컨버터를 통해 변환한다.
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData){
        return helloData;
    }


}
