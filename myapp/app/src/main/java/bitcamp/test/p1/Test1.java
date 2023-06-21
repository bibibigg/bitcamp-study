package bitcamp.test.p1;

public class Test1 {
  public static void main(String[] args) {
    A obj = new A();

    // obj.v1 = 100; // 접근 불가!
    obj.v2 = 200;
    obj.v3 = 300;
    obj.v4 = 400;
    obj.m(); // 같은 패키지이기에 접근 가능!
  }
}
