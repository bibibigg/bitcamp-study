package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Member implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final int ONE_MONTH = 1;
  public static final int THREE_MONTH = 3;
  public static final int SIX_MONTH = 6;

  private int no;
  private String name;
  private int age;
  private String phoneNumber;
  private String password;
  private int per;
  private Date createdDate;
  private String photo;

  @Override
  public String toString() {
    return "Member{" +
            "no=" + no +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", password='" + password + '\'' +
            ", per=" + per +
            ", createdDate=" + createdDate +
            ", photo='" + photo + '\'' +
            '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(no);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Member other = (Member) obj;
    return no == other.no;
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


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getPer() {
    return per;
  }

  public void setPer(int per) {
    this.per = per;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }



}
