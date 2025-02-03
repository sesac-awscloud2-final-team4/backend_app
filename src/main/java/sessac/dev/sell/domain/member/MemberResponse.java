package sessac.dev.sell.domain.member;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class MemberResponse implements Serializable {

    private Long id;                       // 회원 번호 (PK)
    private String loginId;                // 로그인 ID
    private String password;               // 비밀번호
    private String name;                   // 이름
    private Gender gender;                 // 성별
    private LocalDate birthday;            // 생년월일
    private Boolean deleteYn;              // 삭제 여부

    public static MemberResponse from(Member member) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.id = member.getId();
        memberResponse.loginId = member.getLoginId();
        memberResponse.password = member.getPassword();
        memberResponse.name = member.getName();
        memberResponse.gender = member.getGender();
        memberResponse.birthday = member.getBirthday();
        memberResponse.deleteYn = member.getDeleteYn();
        return memberResponse;
    }

    public void clearPassword() {
        this.password = "";
    }

}