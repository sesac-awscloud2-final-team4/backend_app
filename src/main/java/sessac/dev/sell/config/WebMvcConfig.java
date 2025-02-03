package sessac.dev.sell.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sessac.dev.sell.interceptor.LoggerInterceptor;
import sessac.dev.sell.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor())
				.excludePathPatterns("/css/**", "/images/**", "/js/**");

		registry.addInterceptor(new LoginCheckInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/login", "/member")
				.excludePathPatterns("/**/*.js", "/**/*.css", "/**/*.jpg", "/**/*.png", "/**/*.gif", "/**/*.ico");  // 정적 리소스 제외
	}
}