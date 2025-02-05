package sessac.dev.sell.domain.rating;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingService {
	private final RatingRepository ratingRepository;

	public void saveRating(Long memberId, RatingDto ratingDto) {
		if(ratingRepository.existsRatingByMemberIdAndOrderIdAndItemId(memberId, ratingDto.getOrderId(), ratingDto.getItemId())) {
			return;
		}

		ratingRepository.save(Rating.builder()
						.memberId(memberId)
						.orderId(ratingDto.getOrderId())
						.itemId(ratingDto.getItemId())
						.rating(ratingDto.getRating())
				.build());
	}
}
