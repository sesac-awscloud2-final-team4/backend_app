package sessac.dev.sell.domain.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortItemDto {
    private Long id;                // 상품 ID
    private String itemName;        // 상품 이름
    private Integer price;          // 가격
    private Integer stockNumber;    // 재고 수량
    private String itemDetail;      // 상품 상세 설명
    private String productImage;    // 상품 이미지

    public static ShortItemDto from(Item item) {
        ShortItemDto shortItemDto = new ShortItemDto();
        shortItemDto.id = item.getId();
        shortItemDto.itemName = item.getProductName();
        shortItemDto.price = item.getPrice();
        shortItemDto.stockNumber = item.getStockNumber();
        shortItemDto.itemDetail = item.getProductDetail();
        shortItemDto.productImage = item.getProductImage();
        return shortItemDto;
    }

    public String getItemName() {
        return itemName.length() > 20 ? itemName.substring(0, 20) + "..." : itemName;
    }
}
