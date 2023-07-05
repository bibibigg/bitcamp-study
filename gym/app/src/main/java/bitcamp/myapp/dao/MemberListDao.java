package bitcamp.myapp.dao;

import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.vo.Member;
import bitcamp.util.JsonDataHelper;

public class MemberListDao implements MemberDao {

  String filename;
  ArrayList<Member> list = new ArrayList<>();

  public MemberListDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, Member.class);
  }

  @Override
  public void insert(Member member) {
    member.setNo(Member.userId++);
    member.setCreatedDate(System.currentTimeMillis());
    this.list.add(member);
    JsonDataHelper.saveJson(filename, list);

  }

  @Override
  public List<Member> list() {
    return this.list;
  }

  @Override
  public Member findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Member m = this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  @Override
  public int update(Member member) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == member.getNo()) {
        list.set(i, member);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int delete(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        list.remove(i);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }
}
