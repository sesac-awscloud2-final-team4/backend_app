package sessac.dev.sell.common;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EventType {
	PRODUCT_CLICK("product_click"),
	TO_CART("to_cart"),
	RECOMMEND_CLICK("rec_click"),
	PURCHASE("purchase"),
	REVIEW_RATING("review_rating");

	private final String name;

	@JsonValue
	public String getName() {
		return name;
	}
}
