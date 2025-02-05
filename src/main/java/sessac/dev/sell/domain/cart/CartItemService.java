package sessac.dev.sell.domain.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sessac.dev.sell.domain.product.ItemDto;
import sessac.dev.sell.domain.product.ProductService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemService {
	private final CartService cartService;
	private final ProductService productService;

	public List<CartItemDto> getCartItems(Long memberId) {
		List<CartDto> cartList = cartService.getCartItems(memberId);
		List<ItemDto> itemList = productService.findAllById(cartList.stream()
				.map(CartDto::getItemId)
				.toList());
		Map<Long, ItemDto> itemMap = itemList.stream()
				.collect(Collectors.toMap(ItemDto::getId, itemDto -> itemDto));
		return cartList.stream()
				.map(cartDto -> {
					CartItemDto cartItemDto = new CartItemDto();
					cartItemDto.setItemId(cartDto.getItemId());
					cartItemDto.setPrice(itemMap.get(cartDto.getItemId()).getPrice());
					cartItemDto.setQuantity(cartDto.getQuantity());
					cartItemDto.setItemName(itemMap.get(cartDto.getItemId()).getItemName());
					return cartItemDto;
				})
				.toList();
	}
}
