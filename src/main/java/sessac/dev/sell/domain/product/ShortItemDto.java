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

    public static ShortItemDto from(Product product) {
        ShortItemDto shortItemDto = new ShortItemDto();
        shortItemDto.id = product.getId();
        shortItemDto.itemName = product.getProductName();
        shortItemDto.price = product.getPrice();
        shortItemDto.stockNumber = product.getStockNumber();
        shortItemDto.itemDetail = product.getProductDetail();
        shortItemDto.productImage = product.getProductImage();
        return shortItemDto;
    }

    public String getItemName() {
        return itemName.length() > 20 ? itemName.substring(0, 20) + "..." : itemName;
    }
}
