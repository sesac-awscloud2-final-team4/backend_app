package sessac.dev.sell.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import sessac.dev.sell.domain.purchase.PurchaseDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EventLogProducer {
	private final KinesisProducerService kinesisProducerService;

	public void productEvent(Long productId, String main, String productIdsList) {
		if(!ObjectUtils.isEmpty(productIdsList)) {
			List<Long> recommended = Arrays.stream(productIdsList.split(","))
					.map(Long::parseLong)
					.toList();
			Map<String, Object> map = new HashMap<>();
			map.put("rec_prod_list", recommended);
			kinesisProducerService.sendEventMessage(EventType.RECOMMEND_CLICK,productId, main, map);
		} else {
			kinesisProducerService.sendEventMessage(EventType.PRODUCT_CLICK,productId, main, null);
		}
	}

	public void purchaseEvent(String page, List<PurchaseDto> purchaseDto) {
		List<Long> productIds = purchaseDto.stream()
				.map(PurchaseDto::getItemId)
				.toList();
		Long totalPrice = purchaseDto.stream()
				.map(dto -> dto.getPrice() * dto.getQuantity())
				.map(Integer::longValue)
				.reduce(0L, Long::sum);
		Map<String, Object> map = new HashMap<>();
		map.put("pur_list", productIds);
		map.put("pur_amount", totalPrice);
		kinesisProducerService.sendEventMessage(EventType.PURCHASE, null, page, map);
	}

	public void ratingEvent(Long productId, String page, Integer rating) {
		Map<String, Object> map = new HashMap<>();
		map.put("rating", rating);
		kinesisProducerService.sendEventMessage(EventType.REVIEW_RATING, productId, page, map);
	}

	public void cartEvent(Long productId, String page) {
		kinesisProducerService.sendEventMessage(EventType.TO_CART, productId, page, null);
	}
}
