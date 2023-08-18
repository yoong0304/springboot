package com.shop.service;

import com.shop.dto.CartDetailDto;
import com.shop.dto.CartItemDto;
import com.shop.dto.CartOrderDto;
import com.shop.dto.OrderDto;
import com.shop.entity.*;
import com.shop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public Long addCart(CartItemDto cartItemDto, String email) {
        Item item = itemRepository.findById(cartItemDto.getItemId())
                    .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }
        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
//        현재 장바구니에 이미 해당 상품이 들어 있는지 확인
        if (savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart,item,cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email){
        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();
        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null){
            return cartDetailDtoList;
        }
        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
        return cartDetailDtoList;
    }

    public void updateCartItemCount(Long cartItemId, int count){
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItem.updateCount(count);
    }

    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId, String email) {
        Member curMember = memberRepository.findByEmail(email);
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new); // 아이디가 있는지 없는지 예외처리 필수 아니면 Optional 로 받아야 한다.
        Member savedMember = cartItem.getCart().getMember();
        if (!StringUtils.equals(curMember.getEmail(),savedMember.getEmail())){
            return false;
        }
        return true;
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email){
        List<OrderDto> orderDtoList = new ArrayList<>();
        for(CartOrderDto cartOrderDto : cartOrderDtoList){
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
//            CartItemId() 를 이용하여 cartItem 객체를 반환
            OrderDto orderDto = new OrderDto();
            orderDto.setItemId(cartItem.getItem().getId());
//            주문정보에 해당 상품의 id 설정
            orderDto.setCount(cartItem.getCount());
//            주문 정보에 해당 상품의 수량이 설정됩니다.
            orderDtoList.add(orderDto);
//            생성한 orderDto 객체를 orderDtoList 에 추가
        }
//        cartItem 정보를 가져와서 OrderDto 객체로 변환해서 OrderDtoList 에 추가하는 작업

        Long orderId = orderService.orders(orderDtoList, email);
        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
            CartItem cartItem = cartItemRepository
                    .findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
        }   /* 장바구니 주문을 실행하고 장바구니 비우기(삭제) */
        return orderId;
    }
}
