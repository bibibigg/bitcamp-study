package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySQLMemberDao implements MemberDao {

  SqlSessionFactory sqlSesstionFactory;

  public MySQLMemberDao(SqlSessionFactory sqlSesstionFactory) {
    this.sqlSesstionFactory = sqlSesstionFactory;
  }

  @Override
  public void insert(Member member) {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);
    sqlSession.insert("bitcamp.myapp.dao.MemberDao.insert", member);
  }

  @Override
  public List<Member> findAll() {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);
    return sqlSession.selectList("bitcamp.myapp.dao.MemberDao.findAll");
  }

  @Override
  public Member findBy(int no) {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);
    return sqlSession.selectOne("bitcamp.myapp.dao.MemberDao.findBy", no);
  }

  @Override
  public Member findByPhoneAndPassword(Member member) {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);
    return sqlSession.selectOne("bitcamp.myapp.dao.MemberDao.findByPhoneAndPassword", member);
  }

  @Override
  public int update(Member member) {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);
    return sqlSession.update("bitcamp.myapp.dao.MemberDao.update", member);
  }

  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSesstionFactory.openSession(false);
    return sqlSession.delete("bitcamp.myapp.dao.MemberDao.delete", no);
  }



}
