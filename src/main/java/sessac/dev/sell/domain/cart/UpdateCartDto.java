package sessac.dev.sell.domain.cart;

import lombok.Getter;

@Getter
public class UpdateCartDto {
	private Long itemId;
	private Integer quantity;
}
