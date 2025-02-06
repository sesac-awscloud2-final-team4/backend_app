package sessac.dev.sell.domain.rating;

import lombok.Getter;

@Getter
public class RatingDto {
	private Long orderId;
	private Long productId;
	private Integer rating;
}