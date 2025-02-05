package sessac.dev.sell.domain.purchase;

import lombok.Getter;

@Getter
public class PurchaseDto {
	private Long itemId;
	private String itemName;
	private Integer price;
	private Integer quantity;
}
