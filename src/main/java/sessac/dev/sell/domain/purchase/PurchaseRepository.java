package sessac.dev.sell.domain.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	Optional<List<Purchase>> findAllByMemberId(Long memberId);
}
