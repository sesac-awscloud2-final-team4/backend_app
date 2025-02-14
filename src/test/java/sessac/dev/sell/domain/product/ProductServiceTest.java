package sessac.dev.sell.domain.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	void findItemById() {
		//given
		Product product = Product.builder().id(1L).productName("test").price(1234).build();
		when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));

		// when
		FullItemDto result = productService.findItemById(1L);

		// then
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result.getId()).isEqualTo(1L);
	}
}