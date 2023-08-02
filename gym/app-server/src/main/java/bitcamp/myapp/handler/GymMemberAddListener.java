package bitcamp.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/member/add")
public class GymMemberAddListener implements MemberActionListener {

  SqlSessionFactory sqlSessionFactory;
  MemberDao memberDao;

  public GymMemberAddListener(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;

  }

  public void service(BreadcrumbPrompt prompt) throws IOException {
    Member m = new Member();
    m.setName(prompt.inputString("이름? "));
    m.setPhoneNumber(prompt.inputString("핸드폰번호? "));
    m.setAge(prompt.inputInt("나이? "));
    m.setPassword(prompt.inputString("암호? "));
    m.setPer(MemberActionListener.inputPer(0, prompt));
    try {
      memberDao.insert(m);
      sqlSessionFactory.openSession(false).commit();
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
