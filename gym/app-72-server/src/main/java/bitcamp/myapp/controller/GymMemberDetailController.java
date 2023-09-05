package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller("/member/detail")
public class GymMemberDetailController implements PageController {

  @Autowired
  MemberService memberService;


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("member", memberService.get(Integer.parseInt(request.getParameter("no"))));
    request.setAttribute("member",
            memberService.calculateEndDate(
                    memberService.get(Integer.parseInt(request.getParameter("no")))));
    return "/WEB-INF/jsp/member/detail.jsp";
  }
}
