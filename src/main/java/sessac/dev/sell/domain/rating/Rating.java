package sessac.dev.sell.domain.rating;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Rating")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "memberId", nullable = false)
	private Long memberId;
	@Column(name = "orderId", nullable = false)
	private Long orderId;
	@Column(name = "productId", nullable = false)
	private Long productId;
	@Column(name = "rating", nullable = false)
	private float rating;
}
