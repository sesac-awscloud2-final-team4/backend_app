package sessac.dev.sell.domain.purchase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sessac.dev.sell.domain.product.ProductService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseService {
	private final PurchaseRepository purchaseRepository;
	private final ProductService productService;

	public List<PurchaseResponseDto> getPurchaseItems(Long memberId) {
		return purchaseRepository.findAllByMemberId(memberId).orElseThrow().stream()
				.map(PurchaseResponseDto::from)
				.peek(dto -> dto.setProductImage(productService.findItemById(dto.getItemId()).getProductImage()))
				.toList();
	}

	public void buyCartItems(Long memberId, List<PurchaseDto> purchaseDto) {
		purchaseRepository.saveAll(purchaseDto.stream()
				.map(dto -> Purchase.builder()
						.memberId(memberId)
						.itemId(dto.getItemId())
						.cartId(UUID.randomUUID().toString())
						.price(dto.getPrice())
						.quantity(dto.getQuantity())
						.itemName(dto.getItemName())
						.buyDate(Timestamp.valueOf(LocalDateTime.now()))
						.build())
				.toList());
	}
}
