package bitcamp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into gym_member(name,phone_number,age,password,per)" + " values(?,?,sha1(?),?)")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getPhoneNumber());
      stmt.setInt(3, member.getAge());
      stmt.setString(4, member.getPassword());
      stmt.setInt(5, member.getPer());


      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Member> list() {
    try (
        PreparedStatement stmt = con
            .prepareStatement("select member_no, name, phone_number, age, per from gym_member order by name asc");
        ResultSet rs = stmt.executeQuery()) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setName(rs.getString("name"));
        m.setPhoneNumber(rs.getString("phone_number"));
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
    try (PreparedStatement stmt = con
        .prepareStatement("select member_no, name, phone_number, age, per, created_date"
            + " from gym_member where member_no=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member m = new Member();
          m.setNo(rs.getInt("member_no"));
          m.setName(rs.getString("name"));
          m.setPhoneNumber(rs.getString("phone_number"));
          m.setAge(rs.getInt("age"));
          m.setPer(rs.getInt("per"));
          m.setCreatedDate(rs.getDate("created_date"));
          return m;
        }

        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Member findByPhoneAndPassword(Member param) {
    try (PreparedStatement stmt = con
        .prepareStatement("select member_no, name, phone_number, age, per, created_date"
            + " from gym_member"
            + "where phone_number=? and password=sha1(?)")) {

      stmt.setString(1, param.getPhoneNumber());
      stmt.setString(1, param.getPassword());

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member m = new Member();
          m.setNo(rs.getInt("member_no"));
          m.setName(rs.getString("name"));
          m.setPhoneNumber(rs.getString("phone_number"));
          m.setAge(rs.getInt("age"));
          m.setPer(rs.getInt("per"));
          m.setCreatedDate(rs.getDate("created_date"));
          return m;
        }

        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public int update(Member member) {
    try (PreparedStatement stmt = con.prepareStatement(
        "update gym_member set"
    + " name=?,"
    + " phone_number=?,"
        + " age=?,"
    + " password=sha1(?),"
        + " per=?" + " where member_no=?")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getPhoneNumber());
      stmt.setInt(3, member.getAge());
      stmt.setString(4, member.getPassword());
      stmt.setInt(5, member.getPer());
      stmt.setInt(6, member.getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (
        PreparedStatement stmt = con.prepareStatement("delete from gym_member where member_no=?")) {

      stmt.setInt(1, no);

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }



}
