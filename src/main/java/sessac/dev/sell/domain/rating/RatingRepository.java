package sessac.dev.sell.domain.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	boolean existsRatingByMemberIdAndOrderIdAndProductId(long memberId, long orderId, long productId);
}
