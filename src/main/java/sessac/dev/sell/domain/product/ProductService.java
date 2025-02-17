package sessac.dev.sell.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * 물건 저장
     * @param shortItemDto - 저장할 물건 정보
     */
    public Long saveItem(FullItemDto shortItemDto) {
        return productRepository.save(Product.builder()
                        .productName(shortItemDto.getItemName())
                        .price(shortItemDto.getPrice())
                        .stockNumber(shortItemDto.getStockNumber())
                        .productDetail(shortItemDto.getItemDetail())
                        .productImage(shortItemDto.getProductImage())
                .build()).getId();
    }

    /**
     * 물건 상세정보 조회
     * @param id - 물건 ID
     * @return 물건 상세정보
     */
    public FullItemDto findItemById(Long id) {
        return FullItemDto.from(productRepository.findById(id).orElseThrow());
    }

    /**
     * 모든 물건 리스트 조회
     *
     * @return 물건 리스트
     */
    public List<ShortItemDto> findAllItem(){
        return productRepository.findAll().stream()
                .map(ShortItemDto::from)
                .toList();
    }

    /**
     * 해당 물건 리스트 조회
     *
     * @return 물건 리스트
     */
    public List<ShortItemDto> findAllById(List<Long> itemIds){
        return productRepository.findAllById(itemIds).stream()
                .map(ShortItemDto::from)
                .toList();
    }

    /**
     * 물건 삭제
     * @param id - 삭제할 물건 ID
     */
    public void deleteItemById(Long id) {
        productRepository.deleteById(id);
    }
}
