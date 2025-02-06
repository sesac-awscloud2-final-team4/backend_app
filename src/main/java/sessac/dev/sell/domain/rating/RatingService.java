package sessac.dev.sell.domain.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {
	private final RatingRepository ratingRepository;

	public void saveRating(Long memberId, RatingDto ratingDto) {
		if(ratingRepository.existsRatingByMemberIdAndOrderIdAndProductId(memberId, ratingDto.getOrderId(), ratingDto.getProductId())) {
			return;
		}

		ratingRepository.save(Rating.builder()
						.memberId(memberId)
						.orderId(ratingDto.getOrderId())
						.productId(ratingDto.getProductId())
						.rating(ratingDto.getRating())
				.build());
	}
}
