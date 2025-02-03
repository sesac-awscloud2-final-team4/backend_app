package sessac.dev.sell.domain.member;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "loginId")
	private String loginId;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private Gender gender;

	@Column(name = "birthday")
	private LocalDate birthday;

	@Builder.Default
	@Column(name = "deleteYn")
	private Boolean deleteYn = false;
}
