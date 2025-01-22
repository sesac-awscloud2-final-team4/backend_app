package sessac.dev.sell.domain.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Long id;        // 장바구니 항목 ID
    private Long memberId;  // 회원 ID
    private Long itemId;    // 상품 ID

    public static CartItemDto from(Cart cart) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.id = cart.getId();
        cartItemDto.memberId = cart.getMemberId();
        cartItemDto.itemId = cart.getItemId();
        return cartItemDto;
    }
}
