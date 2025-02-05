package sessac.dev.sell.domain.product;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value = "local")
@RequiredArgsConstructor
public class ProductInitializer {
	private final ProductService productService;

	@PostConstruct
	public void init() {
		productService.saveItem(makeItemDto( "상품 2", 15000, 30, "상품 2 상세 설명"));
		productService.saveItem(makeItemDto( "상품 3", 20000, 20, "상품 3 상세 설명"));
		productService.saveItem(makeItemDto( "상품 4", 25000, 10, "상품 4 상세 설명"));
		productService.saveItem(makeItemDto( "상품 5", 30000, 5, "상품 5 상세 설명"));
		productService.saveItem(makeItemDto( "상품 6", 35000, 0, "상품 6 상세 설명"));
	}

	public ItemDto makeItemDto(String itemName, int price, int stockNumber, String itemDetail) {
		ItemDto itemDto = new ItemDto();
		itemDto.setItemName(itemName);
		itemDto.setPrice(price);
		itemDto.setStockNumber(stockNumber);
		itemDto.setItemDetail(itemDetail);
		return itemDto;
	}
}