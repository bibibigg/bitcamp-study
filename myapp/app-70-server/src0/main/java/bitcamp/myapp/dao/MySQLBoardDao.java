package bitcamp.myapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.vo.Board;

public class MySQLBoardDao implements BoardDao {

  // 커넥션을 같은것을 쓰지않게 하기위해서 sqlSession은 밖에서 생성하지않음
  SqlSessionFactory sqlSesstionFactory;

  public MySQLBoardDao(SqlSessionFactory sqlSesstionFactory) {
    this.sqlSesstionFactory = sqlSesstionFactory;
  }

  @Override
  public void insert(Board board) {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);
    sqlSession.insert("bitcamp.myapp.dao.BoardDao.insert", board);
  }

  /*
   * select b.board_no, b.title, b.view_count, b.created_date, m.member_no, m.name from myapp_board
   * b inner join myapp_member m on b.writer=m.member_no where category=1 order by board_no desc
   */

  @Override
  public List<Board> findAll(int category) {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);
    return sqlSession.selectList("bitcamp.myapp.dao.BoardDao.findAll", category);
  }

  @Override
  public Board findBy(int category, int no) {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);

    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("categoryNo", category);
    paramMap.put("boardNo", no);

    return sqlSession.selectOne("bitcamp.myapp.dao.BoardDao.findBy", paramMap);
  }

  @Override
  public int update(Board board) {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);
    return sqlSession.update("bitcamp.myapp.dao.BoardDao.update", board);
  }

  public int updateCount(Board board) {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);
    return sqlSession.update("bitcamp.myapp.dao.BoardDao.updateCount", board);
  }

  @Override
  public int delete(Board board) {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);
    return sqlSession.delete("bitcamp.myapp.dao.BoardDao.delete", board);
  }
}
