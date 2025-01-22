package sessac.dev.sell.domain.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sessac.dev.sell.common.MessageDto;

@Slf4j
@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
	private final ItemService itemService;

	@GetMapping("/products.do")
	public String showProducts(Model model) {
		// 상품 리스트를 모델에 추가
		model.addAttribute("items", itemService.findAllItem());
		return "item/product"; // templates/product.html을 반환
	}

	@GetMapping("/product/{id}")
	public String showProductDetail(@PathVariable Integer id, Model model)
			throws JsonProcessingException {
		ItemDto item = itemService.findItemById(id);
		model.addAttribute("item", item);

		return "item/product-detail"; // templates/item/product-detail.html을 반환
	}

	@PostMapping("/product/add")
	public String addProduct(final ItemDto params, Model model) {
		itemService.saveItem(params);
		MessageDto message = new MessageDto("상품 업로드 완료되었습니다. ", "/product.do",
				RequestMethod.GET, null);
		return showMessageAndRedirect(message, model);
	}

	@PostMapping("/product/delete.do")
	public String deleteProduct(@RequestParam final Integer id, Model model) {
		itemService.deleteItemById(id);
		MessageDto message = new MessageDto("상품 삭제가 완료되었습니다.", "/product.do",
				RequestMethod.GET, null);
		return showMessageAndRedirect(message, model);
	}

	@GetMapping("/")
	public String openProductListFromRott(Model model, HttpSession session) {
		// 상품 리스트를 모델에 추가
		if (session.getAttribute("userId") == null) {
			return "redirect:/login";
		}
		model.addAttribute("items", itemService.findAllItem());
		return "item/product"; // templates/product.html을 반환
	}

	// 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
	private String showMessageAndRedirect(final MessageDto params, Model model) {
		model.addAttribute("params", params);
		return "common/messageRedirect";
	}
}
