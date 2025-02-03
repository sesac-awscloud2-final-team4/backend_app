package sessac.dev.sell.domain.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Long addToCart(CartDto item) {
        return cartRepository.save(Cart.builder()
                        .memberId(item.getMemberId())
                        .itemId(item.getItemId())
                        .quantity(item.getQuantity())
                .build()).getId();
    }

    public List<CartDto> getCartItems(Long memberId) {
        return cartRepository.findAllByMemberId(memberId).orElseThrow().stream()
                .map(CartDto::from)
                .toList();
    }

    public void updateCartItemQuantity(Long memberId, Long itemId, int quantity) {
        Cart cart = cartRepository.findCartByMemberIdAndItemId(memberId, itemId).orElseThrow();
        cart.updateQuantity(quantity);
        cartRepository.save(cart);
    }

    @Transactional
    public void deleteCartItem(Long memberId, Long itemId) {
        cartRepository.deleteAllByMemberIdAndItemId(memberId, itemId);
    }

    @Transactional
    public void clearCart(Long memberId) {
        cartRepository.deleteAllByMemberId(memberId);
    }
}
