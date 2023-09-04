package bitcamp.myapp.dao;

import java.util.List;
import bitcamp.myapp.vo.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {
  int insert(Member member);

  List<Member> findAll();

  Member findBy(int no);

  Member findByPhoneAndPassword(@Param("phoneNumber")String phoneNumber, @Param("password") String password);

  int update(Member member);

  int delete(int no);

}
