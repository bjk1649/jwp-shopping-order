package cart.domain;

import cart.ui.paging.Page;
import java.util.List;

public interface OrderRepository {

    Long save(final Order order);

    Order findById(final Long id);

    List<Order> findByMember(final Member member, final Page page);
}
