package sessac.dev.sell.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    /**
     * 물건 저장
     * @param itemDto - 저장할 물건 정보
     */
    public Long saveItem(ItemDto itemDto) {
        return itemRepository.save(Item.builder()
                        .itemName(itemDto.getItemName())
                        .price(itemDto.getPrice())
                        .stockNumber(itemDto.getStockNumber())
                        .itemDetail(itemDto.getItemDetail())
                .build()).getId();
    }

    /**
     * 물건 상세정보 조회
     * @param id - 물건 ID
     * @return 물건 상세정보
     */
    public ItemDto findItemById(Long id) {
        return ItemDto.from(itemRepository.findById(id).orElseThrow());
    }

    /**
     * 모든 물건 리스트 조회
     *
     * @return 물건 리스트
     */
    public List<ItemDto> findAllItem(){
        return itemRepository.findAll().stream()
                .map(ItemDto::from)
                .toList();
    }

    /**
     * 해당 물건 리스트 조회
     *
     * @return 물건 리스트
     */
    public List<ItemDto> findAllById(List<Long> itemIds){
        return itemRepository.findAllById(itemIds).stream()
                .map(ItemDto::from)
                .toList();
    }

    /**
     * 물건 삭제
     * @param id - 삭제할 물건 ID
     */
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
