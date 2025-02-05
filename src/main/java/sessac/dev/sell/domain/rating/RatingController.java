package sessac.dev.sell.domain.rating;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {
	private final RatingService ratingService;

	@ResponseBody
	@PostMapping("")
	public String rateItem(HttpServletRequest request, @RequestBody RatingDto ratingDto) {
		HttpSession session = request.getSession();
		Long memberId = (Long) session.getAttribute("id");
		ratingService.saveRating(memberId, ratingDto);
		return "success";
	}
}
