package com.shop.service;

import com.shop.dto.OrderDto;
import com.shop.dto.OrderHistDto;
import com.shop.dto.OrderItemDto;
import com.shop.entity.*;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;    // 상품 정보가 담겨있는 저장소
    private final MemberRepository memberRepository;    // 주문자 정보가 담겨있는 저장소
    private final OrderRepository orderRepository;  // 주문 정보가 담겨있는 저장소
    private final ItemImgRepository itemImgRepository;


    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
//        ↑ 주문하려는 아이템을 itemRepository 에서 itemId 로 조회, 존재하지 않으면 EntityNotFoundException

//         주문자
        Member member = memberRepository.findByEmail(email);   // 로그인으로 인해 주문자가 무조건 있기 때문에 orElseThrow 불필요
//        주문을 생성하는 회원 memberRepository 에서 이메일을 이용해 조회

        List<OrderItem> orderItemList = new ArrayList<>();
//        주문 항목 리스트를 생성

        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
//        주문 항목을 생성 OrderItem 클래스의 createOrderItem 메서드를 호출해서 주문항목을 생성
//        이때 필요한 정보 -> 아이템과 주문 수량

        orderItemList.add(orderItem);
//        생성한 주문 항목을 주문 항목 리스트에 추가

        Order order = Order.createOrder(member, orderItemList); // 주문을 생성
//        Order 클래스의 createOrder 메서드를 호출하여 주문을 생성하며,
//        이때 회원 정보와 주문 항목 리스트가 제공

        orderRepository.save(order);
//        생성한 주문을 데이터베이스에 저장

        return order.getId();
//        생성된 주문의 ID를 반환
    }

    @Transactional(readOnly = true)
    public Page<OrderHistDto> getOrderList(String email, Pageable pageable) {
        List<Order> orders = orderRepository.findOrders(email, pageable);
//        주문 레포지토리에서 입력받은 메일 주소와 페이징 정보를 기반으로 주문 목록 조회
        Long totalCount = orderRepository.countOrder(email);
//        유저의 주문 총 개수를 구함

        List<OrderHistDto> orderHistDtos = new ArrayList<>();

        for (Order order : orders) {
//            조회된 주문 목록을 순회하면서 각 주문에 대한 정보를 orderHistDto 로 변환
            OrderHistDto orderHistDto = new OrderHistDto(order);
            List<OrderItem> orderItems = order.getOrderItems();
//            해당 주문에 속한 모든 주문항목을 조회
            for (OrderItem orderItem : orderItems) {
//                조회된 주문 항목을 순회하면서
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(), "Y");
//                findByItemIdAndRepimgYn(orderItem.getItem().getId(), "Y"); 대표 이미지 조회
                OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
//                orderItem 과 이미지 주소 조회
                orderHistDto.addOrderItemDto(orderItemDto);
//                생성된 orderItemDto 를 orderHistDto 리스트에 추가
            }
            orderHistDtos.add(orderHistDto);
//            orderHistDto 리스트에 추가
        }

        return new PageImpl<OrderHistDto>(orderHistDtos, pageable, totalCount);
//        생성된 orderHistDto 리스트를 페이징 처리하여 반환
    }
}
