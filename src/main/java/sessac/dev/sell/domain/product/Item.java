package sessac.dev.sell.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Item")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "productName", nullable = false)
	private String productName;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "stockNumber")
	private Integer stockNumber;

	@Column(name = "productDetail")
	private String productDetail;

	@Column(name = "productImage")
	private String productImage;
}
