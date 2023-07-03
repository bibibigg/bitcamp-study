package bitcamp.myapp.vo;

import java.io.Serializable;

public class Member implements Serializable, CsvObject, AutoIncrement {

  private static final long serialVersionUID = 1L;

  public static int userId = 1;

  public static final int ONE_MONTH = 1;
  public static final int THREE_MONTH = 3;
  public static final int SIX_MONTH = 6;

  private int no;
  private String name;
  private int age;
  private String phoneNumber;
  private int per;
  private long createdDate;

  public Member() {}

  public Member(int no) {
    this.no = no;
  }

  public static Member fromCsv(String csv) {
    String[] values = csv.split(",");

    Member member = new Member(Integer.parseInt(values[0]));
    member.setName(values[1]);
    member.setAge(Integer.parseInt(values[2]));
    member.setPhoneNumber(values[3]);
    member.setPer(Integer.parseInt(values[4]));


    if (Member.userId <= member.getNo()) {
      Member.userId = member.getNo() + 1;
    }
    return member;
  }

  public String toCsvString() {
    return String.format("%d,%s,%d,%s,%d", this.getNo(), this.getName(), this.getAge(),
        this.getPhoneNumber(), this.getPer());
  }

  @Override
  public void updatekey() {
    if (Member.userId <= this.no) {
      Member.userId = this.no + 1;
    }
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

  public long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(long createdDate) {
    this.createdDate = createdDate;
  }

}
