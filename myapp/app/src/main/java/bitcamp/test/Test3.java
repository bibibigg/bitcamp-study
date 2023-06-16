package bitcamp.test;

public class Test3 {
  public static void main(String[] args) {
    // 17 - 3 - 4 = 10

    Calculator2 c = new Calculator2();

    int result = c.minus(17, 3);
    result = c.minus(result, 4);
    System.out.println(result);

    result = c.minus(17, 3, 4);
    System.out.println(result);
  }

  // 상속 받은 메서드와 같은 기능을 수행하는 메서드에 대해
  // 같은 이름을 부여함으로써 프로그래밍의 일관성을 제공하는 문법
  // => 오버로딩
  public int plus(int a, int b, int c) {
    return a + b + c;
  }
}
