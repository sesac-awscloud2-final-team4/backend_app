package sessac.dev.sell.domain.item;

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

	@Column(name = "itemName", nullable = false)
	private String itemName;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "stockNumber")
	private Integer stockNumber;

	@Column(name = "itemDetail")
	private String itemDetail;
}
