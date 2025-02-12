package sessac.dev.sell.domain.product;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sessac.dev.sell.common.EventLogProducer;
import sessac.dev.sell.common.MessageDto;

@Slf4j
@Controller
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	private final EventLogProducer producer;

	@GetMapping("/product")
	public String showProducts(Model model) {
		model.addAttribute("items", productService.findAllItem());
		return "item/product";
	}

	@GetMapping("/product/{id}")
	public String showProductDetail(@PathVariable(name = "id") Long productId, @RequestParam(value = "rec", required = false) String recommended, Model model) {
		model.addAttribute("item", productService.findItemById(productId));
		model.addAttribute("items", productService.findAllItem().stream().limit(6));
		producer.productEvent(productId, "main", recommended);
		return "item/product-detail";
	}

	@PostMapping("/product/add")
	public String addProduct(final FullItemDto params, Model model) {
		productService.saveItem(params);
		MessageDto message = new MessageDto("상품 업로드 완료되었습니다. ", "/product",
				RequestMethod.GET, null);
		return showMessageAndRedirect(message, model);
	}

	@PostMapping("/product/delete")
	public String deleteProduct(@RequestParam final Long id, Model model) {
		productService.deleteItemById(id);
		MessageDto message = new MessageDto("상품 삭제가 완료되었습니다.", "/product",
				RequestMethod.GET, null);
		return showMessageAndRedirect(message, model);
	}

	@GetMapping("/")
	public String openProductListFromRott(Model model, HttpSession session) {
		// 상품 리스트를 모델에 추가
		if (session.getAttribute("userId") == null) {
			return "redirect:/login";
		}
		model.addAttribute("items", productService.findAllItem());
		return "item/product"; // templates/product.html을 반환
	}

	// 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
	private String showMessageAndRedirect(final MessageDto params, Model model) {
		model.addAttribute("params", params);
		return "common/messageRedirect";
	}
}
