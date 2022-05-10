package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // 세개 다 같은 인스턴스
        // 실제로 3번의 MemberRepository 접근이 있지만, 1번만 함수를 호출한다.
        // @Bean 이 붙어있기 때문에 스프링 빈에서 먼저 인스턴스를 찾고, 없을 때에만 호출을한다.(CGLIB)
        // AppConfig 에 @Configuration 을 없애면 MemberRepository 는 3번 호출되고, 객체도 다르다.
        // @AutoWired 로 memberRepository 를 생성후 주입하는 방법도 싱글톤을 유지할 수 있다.
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("memberRepository1 = " + memberRepository1);
//        System.out.println("memberRepository2 = " + memberRepository2);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
