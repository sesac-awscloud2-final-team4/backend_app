package sessac.dev.sell.domain.rating;

import lombok.Getter;

@Getter
public class RatingDto {
	private Long orderId;
	private Long itemId;
	private Integer rating;
}