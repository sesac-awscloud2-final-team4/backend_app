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
	@GeneratedValue
	private Long id;

	@Column(name = "memberId")
	private Long memberId;

	@Column(name = "itemId")
	private Long itemId;

	@Column(name = "quantity")
	private Long quantity;
}
