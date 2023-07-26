package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class GymMemberAddListener implements MemberActionListener {

  DataSource ds;
  MemberDao memberDao;

  public GymMemberAddListener(MemberDao memberDao, DataSource ds) {
    this.memberDao = memberDao;
    this.ds = ds;

  }

  public void service(BreadcrumbPrompt prompt) throws IOException {
    Member m = new Member();
    m.setName(prompt.inputString("이름? "));
    m.setPhoneNumber(prompt.inputString("핸드폰번호? "));
    m.setAge(prompt.inputInt("나이? "));
    m.setPassword(prompt.inputString("암호? "));
    m.setPer(MemberActionListener.inputPer(0, prompt));
    try {
      memberDao.insert((m));
      ds.getConnection().commit();
    } catch (Exception e) {
      try {
        ds.getConnection().rollback();
      } catch (Exception e2) {
      }
      throw new RuntimeException(e);
    }
  }
}
