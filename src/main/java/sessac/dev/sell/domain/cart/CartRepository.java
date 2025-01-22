package sessac.dev.sell.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	Optional<List<Cart>> findAllByMemberId(Long memberId);
	void deleteAllByMemberId(Long memberId);
	void deleteAllByMemberIdAndItemId(Long memberId, Long itemId);
}
