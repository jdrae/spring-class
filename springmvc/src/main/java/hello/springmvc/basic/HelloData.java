package hello.springmvc.basic;

import lombok.Data;

@Data // Getter, Setter 등 필요한 기능 자동 생성
public class HelloData {
    private String username;
    private int age;
}
