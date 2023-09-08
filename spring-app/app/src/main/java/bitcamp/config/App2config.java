package bitcamp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UrlPathHelper;

@ComponentScan("bitcamp.app2")
@EnableWebMvc
public class App2config implements WebMvcConfigurer {

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    UrlPathHelper pathHelper = new UrlPathHelper();
    pathHelper.setRemoveSemicolonContent(false);
    
    // Dispatcher Servlet의 MVC Path 관련 경로 설정
    configurer.setUrlPathHelper(pathHelper);
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vr = new InternalResourceViewResolver();
    vr.setPrefix("/WEB-INF/jsp2/"); // 접두사
    vr.setSuffix(".jsp"); // 접미사
    
    // 예)  return "c01_2/h1" 을 하게되면 /WEB-INF/jsp2/c01_1/h1.jsp 로 가게됨
    vr.setViewClass(JstlView.class);
    return vr;
  }
}
