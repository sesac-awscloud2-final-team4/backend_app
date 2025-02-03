package sessac.dev.sell.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import sessac.dev.sell.domain.member.MemberResponse;

public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {

		// 1. 세션에서 회원 정보 조회
		HttpSession session = request.getSession();
		MemberResponse member = (MemberResponse) session.getAttribute("loginMember");

		// 2. 회원 정보 체크
		if (member == null || member.getDeleteYn() == true) {
			response.sendRedirect("/login");
			return false;
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}