package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter // 값 타입은 변경 불가능하게 해야한다. 생성자로 접근. 기본생성자 protected(jpa 위해)
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
