package bitcamp.test;

class A {
  {
    System.out.println("111");
    System.out.println("222");
    System.out.println("333");
  }
  int v1 = 100;
  int v2 = 200;


  public A() {
    System.out.println("444");
  }

  public A(int a) {
    System.out.println("555");
  }

  public A(int a, int b) {
    System.out.println("666");
  }

  // 생성자를 만들지 않으면 컴파일러가 pubilc A() {}로 생성자를 생성
  // 인스턴스 블록은 생성자의 위치에 상관없이 생성자 앞에 붙는다
  // 따라서 444,111,222,333으로 출력되지 않고 111,222,333,444로 출력
  // 또한 인스턴스 블록은 여러 생성자가 있을 시 여러 생성자 모든곳에 들어가게 된다.
}


public class Exam01 {

  public static void main(String[] args) {
    new A();
    new A();
    new A();
  }
}

