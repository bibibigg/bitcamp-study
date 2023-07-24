package bitcamp.dao;

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
<<<<<<< HEAD
          "insert into myapp_member(name,email,password,gender) values('%s', '%s', '%s', '%c')",
=======
          "insert into myapp_member(name,email,password,gender) values('%s','%s','%s','%c')",
>>>>>>> a2d5e80f777e7e289e43b38904d2414776944cfb
          member.getName(),
          member.getEmail(),
          member.getPassword(),
          member.getGender()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Member> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_no, name, email, gender from myapp_member order by name asc")) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setEmail(rs.getString("email"));
        m.setGender(rs.getString("gender").charAt(0));

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
            "select member_no, name, email, gender from myapp_member where member_no=" + no)) {

<<<<<<< HEAD

=======
>>>>>>> a2d5e80f777e7e289e43b38904d2414776944cfb
      if (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setEmail(rs.getString("email"));
        m.setGender(rs.getString("gender").charAt(0));
<<<<<<< HEAD

=======
>>>>>>> a2d5e80f777e7e289e43b38904d2414776944cfb
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
          "update myapp_member set"
<<<<<<< HEAD
          + " name='%s',"
          + " email='%s',"
          + " password='%s',"
          + " gender='%c'"
          + " where member_no=%d",
          member.getName(),
          member.getEmail(),
          member.getPassword(),
          member.getGender(),
          member.getNo()));
=======
              + " name='%s',"
              + " email='%s',"
              + " password='%s',"
              + " gender='%c'"
              + " where member_no=%d",
              member.getName(),
              member.getEmail(),
              member.getPassword(),
              member.getGender(),
              member.getNo()));
>>>>>>> a2d5e80f777e7e289e43b38904d2414776944cfb

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
<<<<<<< HEAD
          "delete from myapp_member where member_no=%d", no));
=======
          "delete from myapp_member where member_no=%d",
          no));
>>>>>>> a2d5e80f777e7e289e43b38904d2414776944cfb

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
