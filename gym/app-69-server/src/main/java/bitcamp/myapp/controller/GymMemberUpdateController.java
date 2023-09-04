package bitcamp.myapp.controller;

import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Member;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Component("/member/update")
public class GymMemberUpdateController implements PageController {

  MemberDao memberDao;
  PlatformTransactionManager txManager;
  NcpObjectStorageService ncpObjectStorageService;

  public GymMemberUpdateController(MemberDao memberDao, PlatformTransactionManager txManager, NcpObjectStorageService ncpObjectStorageService) {
    this.memberDao = memberDao;
    this.txManager = txManager;
    this.ncpObjectStorageService = ncpObjectStorageService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = txManager.getTransaction(def);


    try {
      Member m = new Member();
      m.setNo(Integer.parseInt(request.getParameter("no")));
      m.setName(request.getParameter("name"));
      m.setAge(Integer.parseInt(request.getParameter("age")));
      m.setPhoneNumber(request.getParameter("phone_number"));
      m.setPassword(request.getParameter("password"));
      m.setPer(Integer.parseInt(request.getParameter("per")));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-13", "member/", photoPart);
        m.setPhoto(uploadFileUrl);
      }

      if (memberDao.update(m) == 0) {
        throw new Exception("회원이 없습니다.");
      } else {
        txManager.commit(status);
        return "redirect:list";
      }
    } catch (Exception e) {
      txManager.rollback(status);
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
