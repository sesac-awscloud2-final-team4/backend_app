package sessac.dev.sell.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
public class EventMessageDto {
	private LocalDateTime timestamp;

	@JsonProperty("user_id")
	private Long memberId;

	@JsonProperty("event_type")
	private EventType eventType;

	@JsonProperty("product_id")
	private Long productId;

	@JsonProperty("page")
	private String page;

	@JsonProperty("rec_prod_list")
	private Long rec_prod_list;

	@JsonProperty("rating")
	private Long rating;

	@JsonProperty("pur_list")
	private Long pur_list;

	@JsonProperty("pur_amount")
	private Long pur_amount;

	public String getTimestamp() {
		return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public String getProductId() {
		if(productId == null) return null;
		return String.format("prod%d", productId);
	}

	public String getMemberId() {
		return String.format("user%d", memberId);
	}
}
