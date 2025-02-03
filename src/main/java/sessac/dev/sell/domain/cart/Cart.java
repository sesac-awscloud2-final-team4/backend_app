package sessac.dev.sell.domain.cart;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Cart")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "memberId", nullable = false)
	private Long memberId;

	@Column(name = "itemId", nullable = false)
	private Long itemId;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	public void updateQuantity(int quantity) {
		if(quantity < 1) return;
		this.quantity = quantity;
	}
}
