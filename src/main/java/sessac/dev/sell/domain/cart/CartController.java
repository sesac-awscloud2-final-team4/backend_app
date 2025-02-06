package sessac.dev.sell.domain.cart;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sessac.dev.sell.common.EventLogProducer;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
	private final CartService cartService;
	private final CartItemService cartItemService;
	private final EventLogProducer producer;

	@GetMapping("")
	public String viewCart(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Long memberId = (Long) session.getAttribute("id");
		model.addAttribute("cartItems", cartItemService.getCartItems(memberId));
		model.addAttribute("memberId", memberId);
		return "cart/cart";
	}

	@PostMapping("/add/{productId}")
	public String addToCart(HttpServletRequest request, @PathVariable(name = "productId") Long productId, @RequestParam(value = "page", required = false) String page) {
		HttpSession session = request.getSession();
		Long userId = (Long) session.getAttribute("id");

		CartDto cartItem = new CartDto();
		cartItem.setMemberId(userId);
		cartItem.setItemId(productId);
		cartItem.setQuantity(1);

		cartService.addToCart(cartItem);
		producer.cartEvent(productId, page);
		return "redirect:/cart";
	}

	@ResponseBody
	@PostMapping("/update")
	public Long updateCartItem(HttpServletRequest request,
			@RequestBody UpdateCartDto updateCartDto) {
		HttpSession session = request.getSession();
		Long memberId = (Long) session.getAttribute("id");
		cartService.updateCartItemQuantity(memberId, updateCartDto.getItemId(), updateCartDto.getQuantity());
		return updateCartDto.getItemId();
	}

	@ResponseBody
	@PostMapping("/delete/{itemId}")
	public Long deleteCartItem(HttpServletRequest request, @PathVariable(name = "itemId") Long itemId) {
		HttpSession session = request.getSession();
		Long memberId = (Long) session.getAttribute("id");
		cartService.deleteCartItem(memberId, itemId);
		return itemId;
	}

	@PostMapping("/clear")
	public Long clearCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long memberId = (Long) session.getAttribute("id");
		cartService.clearCart(memberId);
		return memberId;
	}
}
