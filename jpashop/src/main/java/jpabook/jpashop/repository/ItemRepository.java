package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor // final 에 대한 생성자를 만들기 위해
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        if(item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item); // 아이템을 업데이트
        }
    }
    /* em.merge(item)
    merge 메서드를 사용하여 이미 존재하는 item 엔티티를 데이터 베이스에 업데이트한다.
    merge 메서드는 엔티티를 영속성 컨텍스트에 병합하는 역할 */

    public Item findOne(Long id) {
        return em.find(Item.class, id); // jpa 에선 class 를 꼭 넣어줘야 된다.
    }
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
