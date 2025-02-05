package sessac.dev.sell.domain.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long id;            // 상품 ID
    private String itemName;      // 상품 이름
    private Integer price;            // 가격
    private Integer stockNumber;      // 재고 수량
    private String itemDetail;     // 상품 상세 설명

    public static ItemDto from(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.id = item.getId();
        itemDto.itemName = item.getItemName();
        itemDto.price = item.getPrice();
        itemDto.stockNumber = item.getStockNumber();
        itemDto.itemDetail = item.getItemDetail();
        return itemDto;
    }
}
