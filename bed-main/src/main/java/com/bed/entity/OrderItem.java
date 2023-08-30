package com.bed.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격
    private int count; // 수량

    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item); //주문할 상품과
        orderItem.setCount(count);// 주문수량셋팅
        orderItem.setOrderPrice(item.getPrice()); // 상품가격을 주문가격으로 세팅
        item.removeStock(count);// item의 메서드인 removeStock을 사용하여 재고를 제거
        //주문수량만큼 상품의 재고수량 감소
        return orderItem;
    }

    public int getTotalPrice(){
        return orderPrice*count;
    }//주문가격과 주문수량을 곱해서 해당 상품을 주문한 총가격을 계산하는 메소드

    public void cancel() {
        this.getItem().addStock(count);
    }
    //주문 취소시 주문 수량 만큼 상품의 재고를 더해줍니다.






}
