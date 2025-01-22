package sessac.dev.sell.domain.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private CartRepository cartRepository;

    public Long addToCart(CartItemDto item) {
        return cartRepository.save(Cart.builder()
                        .memberId(item.getMemberId())
                        .itemId(item.getItemId())
                .build()).getId();
    }

    public List<CartItemDto> getCartItems(Long memberId) {
        return cartRepository.findAllByMemberId(memberId).orElseThrow()
                .stream().map(CartItemDto::from)
                .toList();
    }

    public void updateCartItemQuantity(Long memberId, Integer itemId, int quantity) {
//        cartMapper.updateCartItemQuantity(memberId, itemId, quantity);
    }

    public void deleteCartItem(Long memberId, Long itemId) {
        cartRepository.deleteAllByMemberIdAndItemId(memberId, itemId);
    }

    public void clearCart(Long memberId) {
        cartRepository.deleteAllByMemberId(memberId);
    }
}
