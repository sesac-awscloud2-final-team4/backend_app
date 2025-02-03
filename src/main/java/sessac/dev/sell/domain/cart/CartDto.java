package sessac.dev.sell.domain.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
    private Long id;        // 장바구니 항목 ID
    private Long memberId;  // 회원 ID
    private Long itemId;    // 상품 ID
    private Integer quantity;

    public static CartDto from(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.id = cart.getId();
        cartDto.memberId = cart.getMemberId();
        cartDto.itemId = cart.getItemId();
        cartDto.quantity = cart.getQuantity();
        return cartDto;
    }
}
