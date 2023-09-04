package bitcamp.myapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.NcpObjectStorageService;
import org.apache.ibatis.session.SqlSessionFactory;

public class GymMemberAddController implements PageController {

  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;
  NcpObjectStorageService ncpObjectStorageService;

  public GymMemberAddController(MemberDao memberDao,
                                SqlSessionFactory sqlSessionFactory,
                                NcpObjectStorageService ncpObjectStorageService) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
    this.ncpObjectStorageService = ncpObjectStorageService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/member/form.jsp";
    }

    try {
    Member m = new Member();
    m.setName(request.getParameter("name"));
    m.setPhoneNumber(request.getParameter("phone_number"));
    m.setAge(Integer.parseInt(request.getParameter("age")));
    m.setPassword(request.getParameter("password"));
    m.setPer(Integer.parseInt(request.getParameter("per")));

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile(
          "bitcamp-nc7-bucket-13", "member/", photoPart);
      m.setPhoto(uploadFileUrl);
    }

      memberDao.insert(m);
      sqlSessionFactory.openSession(false).commit();
      return "redirect:list";

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("message", "회원 등록 오류!");
      request.setAttribute("refresh", "2;url=list");
      request.setAttribute("exception", e);
      throw e;
    }
  }
}