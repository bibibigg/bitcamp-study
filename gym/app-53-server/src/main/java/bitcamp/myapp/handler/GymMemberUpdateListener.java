package bitcamp.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class GymMemberUpdateListener implements MemberActionListener {

  SqlSessionFactory sqlSessionFactory;
  MemberDao memberDao;

  public GymMemberUpdateListener(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public void service(BreadcrumbPrompt prompt) throws IOException {
    int memberNo = prompt.inputInt("번호? ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    m.setName(prompt.inputString("이름(%s)? ", m.getName()));
    m.setAge(prompt.inputInt("나이(%d)? ", m.getAge()));
    m.setPhoneNumber(prompt.inputString("핸드폰번호(%s)? ", m.getPhoneNumber()));
    m.setPassword(prompt.inputString("새암호? "));
    m.setPer(MemberActionListener.inputPer(m.getPer(), prompt));

    try {
      memberDao.update(m);
      sqlSessionFactory.openSession(false).commit();
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
