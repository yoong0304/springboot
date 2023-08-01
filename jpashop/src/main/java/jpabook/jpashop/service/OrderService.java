package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    /* 주문 */
//    주문하는 회원식별자, 상품식별자, 주문수량을 받아서 실제 주문 엔티티를 생성한 후 저장
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
//        엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

//        배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

//        주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

//        주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

//        주문 저장
        orderRepository.save(order);
        return order.getId();
    }
    /* 주문 취소 */
//    주문 식별자를 받아서 주문 엔티티를 조회한 후 주문 엔티티에 주문 취소를 요청
    @Transactional
    public void cancelOrder(Long orderId){
//        주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    /* 주문 검색 */
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }
//    OrderSearch 객체를 기반으로 주문(Order)을 검색하는 메서드
//    orderSearch - 주문에 필요한 조건



}
