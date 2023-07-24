package bitcamp.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.vo.Member;

public class MySQLMemberDao implements MemberDao {

  Connection con;

  public MySQLMemberDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member member) {
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(String.format(
          "insert into gym_member(name,age,password,per)" + " values('%s',%d,'%s',%d)",
          member.getName(), member.getAge(), member.getPassword(), member.getPer()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Member> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt
            .executeQuery("select member_no, name, age, per from gym_member order by name asc")) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setAge(rs.getInt("age"));
        m.setPer(rs.getInt("per"));

        list.add(m);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Member findBy(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_no, name, age, per from gym_member where member_no=" + no)) {


      if (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setAge(rs.getInt("age"));
        m.setPer(rs.getInt("per"));
        return m;
      }

      return null;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Member member) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
          "update gym_member set" + " name='%s'," + " age=%d," + " password='%s'," + " per=%d"
              + " where member_no=%d",
          member.getName(), member.getAge(), member.getPassword(), member.getPer(),
          member.getNo()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format("delete from gym_member where member_no=%d", no));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
