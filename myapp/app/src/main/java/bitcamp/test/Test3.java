package bitcamp.test;

import bitcamp.test.p1.A;

public class Test3 extends A {
  public static void main(String[] args) {
    A obj = new A();

    // obj.v1 = 100; // 접근 불가
    // obj.v2 = 200; // 접근 불가
    // obj.v3 = 300; // Test3는 A의 자식클래스이지만 상속받은 멤버가 아니기에 접근 불가!
    obj.v4 = 400;
    // obj.m(); // 같은패키지는 아니지만 서브클래스인데 접근 불가능한 이유
    // 상속받은 멤버가 아니기에 접근 불가!

    m2();

    Test3 obj2 = new Test3();
    // m3(); // 호출이 안되는 이유는 m3는 non-static 멤버이기에 호출시 인스턴스 주소를 넘겨줘야함!
    obj2.m3(); // obj2에 인스턴스 주소를 넘겨준 후 호출 가능!
    // obj2.v1 = 100; // 접근 불가!
    // obj2.v2 = 200; // 접근 불가!
    obj2.v3 = 300; // 접근 가능한 이유 : 위에서는 A클래스의 설계도에 따라 만들어 졌고
                   // obj2는 상속 받아서 만든 필드이기에 자식 클래스가 접근 가능!
                   // 자식클래스에서 그냥 만들 시에는 안되고 자식클래스에서 상속을 받아서 만들어야지 접근 가능!
    obj2.v4 = 400;
    obj2.m(); // 자식 클래스가 상속 받아서 사용하는 멤버이기에 호출 가능!
    // protected는 자식클래스에서 사용 가능한 것이 아니라
    // 자식클래스에서 상속받은 필드에서 사용이 가능함!!
  }

  static void m2() {

  }

  void m3() {

  }
}
