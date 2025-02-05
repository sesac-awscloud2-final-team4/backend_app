package sessac.dev.sell.domain.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Long addToCart(CartDto cartDto) {
        Optional<Cart> cart = cartRepository.findCartByMemberIdAndItemId(cartDto.getMemberId(), cartDto.getItemId());

        if (cart.isPresent()) {
            Cart existingCart = cart.get();
            existingCart.updateQuantity(existingCart.getQuantity() + cartDto.getQuantity());
            return cartRepository.save(existingCart).getId();
        }

        // 존재하지 않으면 새로 저장
        Cart newCart = Cart.builder()
                .memberId(cartDto.getMemberId())
                .itemId(cartDto.getItemId())
                .quantity(cartDto.getQuantity())
                .build();

        return cartRepository.save(newCart).getId();

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
