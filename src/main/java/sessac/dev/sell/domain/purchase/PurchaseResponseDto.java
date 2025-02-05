package sessac.dev.sell.domain.purchase;

import lombok.Getter;

@Getter
public class PurchaseResponseDto {
	private Long orderId;
	private Long itemId;
	private String itemName;
	private Integer price;
	private Integer quantity;

	public static PurchaseResponseDto from(Purchase purchase) {
		PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto();
		purchaseResponseDto.orderId = purchase.getId();
		purchaseResponseDto.itemId = purchase.getItemId();
		purchaseResponseDto.itemName = purchase.getItemName();
		purchaseResponseDto.price = purchase.getPrice();
		purchaseResponseDto.quantity = purchase.getQuantity();
		return purchaseResponseDto;
	}
}
