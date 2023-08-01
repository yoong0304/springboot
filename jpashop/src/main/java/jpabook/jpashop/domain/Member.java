package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data   // getter setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @JsonIgnore     // 아래 항목 무시?제외?
//    직렬화 시 해당 필드를 포함시키고 싶지 않을 때 선언하는 어노테이션이다.
//    해당 어노테이션을 사용하면 Response 데이터에서 해당 필드가 제외된다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

//    Member 엔티티와의 관계를 매핑하고 'mappedBy' 속성에는 이 관계를 정의 하는 필드명인
//    member 속성이 존재

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
