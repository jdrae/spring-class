package hello.springmvc.basic.request;

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
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        response.getWriter().write(messageBody);
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException{
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        responseWriter.write(messageBody);
    }


    @PostMapping("/request-body-string-v3")
    // HttpEntity 는 body 와 header 를 조회할 수 있음. 파라미터를 조회하는 기능과 관계없음.
    // 응답에 사용할 때 바디 정보를 직접 반환. Controller 지만 view 를 조회하지 않음.
    // RequestEntity 로 받고, ResponseEntity 로 반환할 수 있음.
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException{
        String messageBody = httpEntity.getBody();

        return new HttpEntity<>(messageBody);
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    // @RequestBody - @ResponseBody 가 짝이 됨
    public String requestBodyStringV4(@RequestBody String messageBody){
        return messageBody;
    }
}
