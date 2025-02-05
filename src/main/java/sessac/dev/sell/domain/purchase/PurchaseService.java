package sessac.dev.sell.domain.purchase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
	private final PurchaseRepository purchaseRepository;

	public List<PurchaseResponseDto> getPurchaseItems(Long memberId) {
		return purchaseRepository.findAllByMemberId(memberId).orElseThrow().stream()
				.map(PurchaseResponseDto::from)
				.toList();
	}

	public void buyCartItems(Long memberId, List<PurchaseDto> purchaseDto) {
		purchaseRepository.saveAll(purchaseDto.stream()
				.map(dto -> Purchase.builder()
						.memberId(memberId)
						.itemId(dto.getItemId())
						.price(dto.getPrice())
						.quantity(dto.getQuantity())
						.itemName(dto.getItemName())
						.build())
				.toList());
	}
}
