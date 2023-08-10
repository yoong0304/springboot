package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart extends BaseEntity {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//           (fetch = FetchType.LAZY) 실무에서 많이 씀
//           (fetch = FetchType.EAGER) 공부할 때 쓰면 좋음
    @OneToOne(fetch = FetchType.LAZY) /* 하나의 멤버의 하나의 장바구니*/
    @JoinColumn(name = "member_id")
    private Member member;

    public static Cart createCart(Member member){   /* 공용의 연관관계 메서드*/
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }

}
