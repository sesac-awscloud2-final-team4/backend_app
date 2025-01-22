package sessac.dev.sell.domain.cart;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

	private CartService cartService;

	@PostMapping("/add/{itemId}")
	public String addToCart(@PathVariable Long itemId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long userId = (Long) session.getAttribute("id");

		CartItemDto cartItem = new CartItemDto();
		cartItem.setMemberId(userId);
		cartItem.setItemId(itemId);

		cartService.addToCart(cartItem); // 장바구니에 추가

		return "redirect:/cart?memberId=" + userId; // 장바구니 페이지로 리다이렉트
	}

	@GetMapping
	public void viewCart(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Long memberId = (Long) session.getAttribute("id");
		List<CartItemDto> cartItems = cartService.getCartItems(memberId);
	}

	@PostMapping("/update")
	public String updateCartItem(@RequestParam Long memberId,
			@RequestParam Integer itemId, @RequestParam int quantity) {
		cartService.updateCartItemQuantity(memberId, itemId, quantity);
		return "redirect:/cart?memberId=" + memberId; // 장바구니 페이지로 리다이렉트
	}

	@PostMapping("/delete")
	public String deleteCartItem(@RequestParam Long memberId,
			@RequestParam Long itemId) {
		cartService.deleteCartItem(memberId, itemId);
		return "redirect:/cart?memberId=" + memberId; // 장바구니 페이지로 리다이렉트
	}

	@PostMapping("/clear")
	public String clearCart(@RequestParam Long memberId) {
		cartService.clearCart(memberId);
		return "redirect:/cart?memberId=" + memberId; // 장바구니 페이지로 리다이렉트
	}
}
