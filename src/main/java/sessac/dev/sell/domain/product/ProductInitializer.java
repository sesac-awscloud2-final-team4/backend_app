package sessac.dev.sell.domain.product;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value = "local")
@RequiredArgsConstructor
public class ProductInitializer {
	private final ProductService productService;

	@PostConstruct
	public void init() {
		if(!productService.findAllItem().isEmpty()) return;
		productService.saveItem(makeItemDto( "달바 화이트 트러플 프라임 인텐시브 세럼, 50ml", 7500, 100, "스킨케어, 세럼, 미백", "1.jpg"));
		productService.saveItem(makeItemDto( "피지오겔 데일리 모이스쳐 테라피 페이셜 크림, 75ml", 12300, 100, "스킨케어, 페이셜크림, 보습", "2.jpg"));
		productService.saveItem(makeItemDto( "웰라쥬 리얼 히알루로닉 블루 앰플, 100ml", 19950, 100, "스킨케어, 앰플, 보습", "3.jpg"));
		productService.saveItem(makeItemDto( "더마팩토리 나이아신아마이드 20% 세럼, 30ml", 7080, 100, "스킨케어, 세럼, 미백", "4.jpg"));
		productService.saveItem(makeItemDto( "닥터지 블랙 스네일크림, 50ml", 11400, 100, "스킨케어, 크림, 영양", "5.jpg"));
		productService.saveItem(makeItemDto( "메디필 수분 크림 보습 물광 폭탄 캡슐 히알루론산 펩타이드 판테놀 악건성 수부지 속건조 피부 장벽 강화 50g", 21800, 100, "스킨케어, 수분크림, 보습", "6.jpg"));
		productService.saveItem(makeItemDto( "미샤 글로우 스킨밤, 50ml", 9650, 100, "스킨케어, 스킨밤, 보습", "7.jpg"));
		productService.saveItem(makeItemDto( "라운드랩 1025 독도 크림, 80ml", 9450, 100, "스킨케어, 크림, 진정", "8.jpg"));
		productService.saveItem(makeItemDto( "닥터지 닥터지 레드 블레미쉬 클리어 퀵 수딩 팩, 70개입", 11400, 100, "스킨케어, 마스크팩, 트러블케어", "9.jpg"));
		productService.saveItem(makeItemDto( "마미케어 바다포도 포어 모공 리프팅 앰플, 50ml", 14150, 100, "스킨케어, 앰플, 포어케어", "10.jpg"));
		productService.saveItem(makeItemDto( "듀이트리 픽앤퀵 화장발 뜯어쓰는 더블 패드 380g", 14520, 100, "스킨케어, 패드, 보습", "11.jpg"));
		productService.saveItem(makeItemDto( "나인위시스 하이드라 앰플 크림, 50ml", 11000, 100, "스킨케어, 앰플크림, 보습", "12.jpg"));
		productService.saveItem(makeItemDto( "이니스프리 비자 트러블 로션, 100ml", 9520, 100, "스킨케어, 로션, 피지조절", "13.jpg"));
		productService.saveItem(makeItemDto( "미샤 글로우 스킨밤, 20ml", 5670, 100, "스킨케어, 스킨밤, 보습", "14.jpg"));
		productService.saveItem(makeItemDto( "마몽드 플로라글로우 로즈 리퀴드 마스크, 80ml", 19190, 100, "스킨케어, 마스크, 진정", "15.jpg"));
		productService.saveItem(makeItemDto( "스킨푸드 캐롯 카로틴 데일리 마스크, 30개입", 13150, 100, "스킨케어, 마스크팩, 수분", "16.jpg"));
		productService.saveItem(makeItemDto( "차앤박 프로폴리스 앰플 미스트, 100ml", 7500, 100, "스킨케어, 미스트, 진정", "17.jpg"));
		productService.saveItem(makeItemDto( "김정문알로에 라센스 큐어 플러스 크림, 80g", 8140, 100, "스킨케어, 크림, 진정", "18.jpg"));
		productService.saveItem(makeItemDto( "이니스프리 New 그린티 씨드 히알루론산 고수분 크림, 50ml", 16670, 100, "스킨케어, 크림, 보습", "19.jpg"));
		productService.saveItem(makeItemDto( "피부미 센텔루션 시카 크림 쓰리엑스 지복합성, 100ml", 20500, 100, "스킨케어, 시카크림, 장벽", "20.jpg"));
	}

	public FullItemDto makeItemDto(String itemName, int price, int stockNumber, String itemDetail, String productImage) {
		FullItemDto itemDto = new FullItemDto();
		itemDto.setItemName(itemName);
		itemDto.setPrice(price);
		itemDto.setStockNumber(stockNumber);
		itemDto.setItemDetail(itemDetail);
		itemDto.setProductImage(productImage);
		return itemDto;
	}
}