package jpabook.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private  Long id;

    @ManyToOne(fetch = FetchType.LAZY) // EAGER 조회할 때 다 해버림 LAZY 는 연관관계인 것만
    @JoinColumn(name = "member_id")
    private Member member;  // 주문 회원
//    Order 엔티티 클래스에 Member 라는 필드가 있으며 Member 엔티티와 다대일(ManyToOne) 관계 이며
//    member_id 컬럼과 연결

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;  // 배송 정보

    private LocalDateTime orderDate;   // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]

    /* 연관관계 메서드 */
//    양쪽을 동기화
    public void setMember(Member member) {
        this.member = member;
//        현재 order 엔티티에서 member 엔티티를 설정
//        order 엔티티가 member 엔티티를 참조
        member.getOrders().add(this);
//        주어진 order 엔티티를 Member 엔티티의 Order
//        엔티티를 Member 엔티티의 orders 컬렉션에 추가하는 부분
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    /* 생성 메서드 */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {    // OrderItem... 가변길이 매개변수
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
    /*
        1. 새로운 order 객체 생성
        2. 주문을 만든 Member 엔티티를 order.setMember(member)로 설정
        3. 주문의 배송 정보를 order.setDelivery(delivery);
        4. 여러개의 주문 항목을 for-each 를 통해서 order.addOrderItem(orderItem);을 사용해서 추가
        5. 주문 상태를 order.setStatus(OrderStatus.ORDER); 지정
        6. 주문 날짜를 order.setOrderDate(LocalDateTime.now()); - 현재시간
        7. 생성된 주문 객체를 반환
    */



//    비즈니스 로직
//    주문 취소
    public void cancel() {
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);

        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }
// 조회 로직
    /* 전체 주문 가격 조회 */
    public int getTotalPrice() {
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();    // 각 상품마다의 최종 가격을 totalPrice 에 누적
        }
        return totalPrice;
    }

}
