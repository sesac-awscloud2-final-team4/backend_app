package sessac.dev.sell.domain.purchase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
	private final PurchaseService purchaseService;

	@GetMapping("")
	public String showPurchases(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Long memberId = (Long) session.getAttribute("id");
		model.addAttribute("orderItems", purchaseService.getPurchaseItems(memberId));
		return "purchase/purchase";
	}

	@ResponseBody
	@PostMapping("/buy")
	public String buyCartItems(HttpServletRequest request, @RequestBody List<PurchaseDto> purchaseDto) {
		HttpSession session = request.getSession();
		Long memberId = (Long) session.getAttribute("id");
		purchaseService.buyCartItems(memberId, purchaseDto);
		return "success";
	}
}
