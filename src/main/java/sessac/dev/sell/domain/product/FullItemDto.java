package sessac.dev.sell.domain.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullItemDto {
	private Long id;                // 상품 ID
	private String itemName;        // 상품 이름
	private Integer price;          // 가격
	private Integer stockNumber;    // 재고 수량
	private String itemDetail;      // 상품 상세 설명
	private String productImage;    // 상품 이미지

	public static FullItemDto from(Item item) {
		FullItemDto fullItemDto = new FullItemDto();
		fullItemDto.id = item.getId();
		fullItemDto.itemName = item.getProductName();
		fullItemDto.price = item.getPrice();
		fullItemDto.stockNumber = item.getStockNumber();
		fullItemDto.itemDetail = item.getProductDetail();
		fullItemDto.productImage = item.getProductImage();
		return fullItemDto;
	}
}
