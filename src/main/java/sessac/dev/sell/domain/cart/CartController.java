package sessac.dev.sell.domain.cart;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;
	private final CartItemService cartItemService;

	@GetMapping("")
	public String viewCart(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Long memberId = (Long) session.getAttribute("id");
		// TODO move to service layer
		model.addAttribute("cartItems", cartItemService.getCartItems(memberId));
		model.addAttribute("memberId", memberId);
		return "cart/cart";
	}

	@PostMapping("/add/{itemId}")
	public String addToCart(@PathVariable(name = "itemId") Long itemId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long userId = (Long) session.getAttribute("id");

		CartDto cartItem = new CartDto();
		cartItem.setMemberId(userId);
		cartItem.setItemId(itemId);
		cartItem.setQuantity(1);

		cartService.addToCart(cartItem); // 장바구니에 추가
		return "redirect:/cart"; // 장바구니 페이지로 리다이렉트
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
