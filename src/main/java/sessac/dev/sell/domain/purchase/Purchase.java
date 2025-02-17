package sessac.dev.sell.domain.purchase;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Purchase")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "memberId", nullable = false)
	private Long memberId;

	@Column(name = "itemId", nullable = false)
	private Long itemId;

	@Column(name = "cartId", nullable = false)
	private String cartId;

	@Column(name = "itemName", nullable = false)
	private String itemName;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "buyDate")
	private Timestamp buyDate;
}
