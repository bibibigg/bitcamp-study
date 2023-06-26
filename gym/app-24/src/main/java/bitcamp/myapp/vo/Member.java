package bitcamp.myapp.vo;

public class Member {

  private static int userId = 1;

  public static final int ONE_MONTH = 1;
  public static final int THREE_MONTH = 3;
  public static final int SIX_MONTH = 6;

  private int no;
  private String name;
  private int age;
  private String phoneNumber;
  private int per;

  public Member() {
    this.no = userId++;
  }

  public Member(int no) {
    this.no = no;
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }
    Member m = (Member) obj;

    if (this.getNo() != m.getNo()) {
      return false;
    }

    return true;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public int getPer() {
    return per;
  }

  public void setPer(int per) {
    this.per = per;
  }
}
