package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan( // @Component 가 붙어있는 클래스를 스캔해서 빈으로 등록한다
        // 디폴트는 @ComponentScan 클래스의 패키지가 시작위치(AutoAppConfig.java 의 위치)
        // basePackages = "hello.core",
        // 기존 AppConfig 를 스캔&등록하지 않기 위해서
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
)
public class AutoAppConfig {


}
