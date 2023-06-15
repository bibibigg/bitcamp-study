package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;

public class GymMemberList {
  private static final int DEFAULT_SIZE = 3;
  private Member[] members = new Member[DEFAULT_SIZE];
  private int length;

  public void add(Member m) {
    if (this.length == members.length) {
      increase();
    }
    this.members[this.length++] = m;
  }

  public void increase() {
    Member[] arr = new Member[members.length + (members.length >> 1)];

    for (int i = 0; i < length; i++) {
      arr[i] = members[i];
    }
    members = arr;
    System.out.println("배열증가" + arr.length);
  }

  public Member[] list() {
    Member[] arr = new Member[this.length];

    for (int i = 0; i < this.length; i++) {
      arr[i] = this.members[i];
    }
    return arr;
  }

  public Member get(int no) {
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  public boolean delete(int no) {
    int deletedIndex = indexOf(no);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.members[i] = this.members[i + 1];
    }
    this.members[--this.length] = null;
    return true;
  }

  private int indexOf(int memberNo) {
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }



}


