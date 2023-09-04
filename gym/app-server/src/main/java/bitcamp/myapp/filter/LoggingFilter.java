package bitcamp.myapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebFilter("/*")
public class LoggingFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    // 클라이언트의 IP 주소 가져오기
    String clientIP = request.getRemoteAddr();

    // 현재 시간 가져오기
    Date currentTime = new Date();

    // 날짜 포맷 지정
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedTime = dateFormat.format(currentTime);

    // 접속 정보 로깅 (예: 표준 출력)
    System.out.println("Client IP: " + clientIP + " - Access Time: " + formattedTime);

    // 다음 필터 또는 서블릿으로 요청 전달
    chain.doFilter(request, response);
  }

}