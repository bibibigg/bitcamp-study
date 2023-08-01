package bitcamp.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import bitcamp.dao.MySQLBoardDao;
import bitcamp.dao.MySQLMemberDao;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.handler.GymBoardAddListener;
import bitcamp.myapp.handler.GymBoardDeleteListener;
import bitcamp.myapp.handler.GymBoardDetailListener;
import bitcamp.myapp.handler.GymBoardListListener;
import bitcamp.myapp.handler.GymBoardUpdateListener;
import bitcamp.myapp.handler.GymMemberAddListener;
import bitcamp.myapp.handler.GymMemberDateListener;
import bitcamp.myapp.handler.GymMemberDeleteListener;
import bitcamp.myapp.handler.GymMemberDetailListener;
import bitcamp.myapp.handler.GymMemberListListener;
import bitcamp.myapp.handler.GymMemberUpdateListener;
import bitcamp.myapp.handler.LoginListener;

public class DispatcherListener implements ActionListener {

  // 객체 보관소
  Map<String, Object> beanContainer = new HashMap<>();

  public DispatcherListener() throws Exception {

    // Mybatis 준비
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("bitcamp/myapp/config/mybatis-config.xml")));
    beanContainer.put("sqlSessionFactory", sqlSessionFactory);

    // DAO 준비
    MemberDao memberDao = new MySQLMemberDao(sqlSessionFactory);
    BoardDao boardDao = new MySQLBoardDao(sqlSessionFactory);
    beanContainer.put("memberDao", memberDao);
    beanContainer.put("boardDao", boardDao);

    // Listener 준비
    beanContainer.put("login", new LoginListener(memberDao));

    beanContainer.put("member/add", new GymMemberAddListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/list", new GymMemberListListener(memberDao));
    beanContainer.put("member/detail", new GymMemberDetailListener(memberDao));
    beanContainer.put("member/update", new GymMemberUpdateListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/date", new GymMemberDateListener(memberDao));
    beanContainer.put("member/delete", new GymMemberDeleteListener(memberDao, sqlSessionFactory));

    beanContainer.put("board/add", new GymBoardAddListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/list", new GymBoardListListener(1, boardDao));
    beanContainer.put("board/detail", new GymBoardDetailListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/update", new GymBoardUpdateListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/delete", new GymBoardDeleteListener(1, boardDao, sqlSessionFactory));

    beanContainer.put("reading/add", new GymBoardAddListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("reading/list", new GymBoardListListener(2, boardDao));
    beanContainer.put("reading/detail", new GymBoardDetailListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("reading/update", new GymBoardUpdateListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("reading/delete", new GymBoardDeleteListener(2, boardDao, sqlSessionFactory));
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    ActionListener listener = (ActionListener) beanContainer.get(prompt.getAttribute("menuPath"));
    if (listener == null) {
      throw new RuntimeException("해당 요청을 처리할 수 없습니다.");
    }
    listener.service(prompt);
  }

  public Object getBean(String name) {
    return beanContainer.get(name);
  }
}
