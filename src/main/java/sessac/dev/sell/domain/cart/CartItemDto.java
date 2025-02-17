package sessac.dev.sell.domain.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
	private Long itemId;
	private String itemName;
	private Integer price;
	private Integer quantity;
	private String productImage;
}
