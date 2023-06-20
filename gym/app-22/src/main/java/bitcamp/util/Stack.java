package bitcamp.util;

public class Stack extends LinkedList {
  public static void main(String[] args) {
    Stack s = new Stack();

    s.push("홍길동");
    s.push("유관순");
    s.push("안중근");

    System.out.println(s.pop());
    System.out.println(s.pop());
    System.out.println(s.pop());
    System.out.println(s.pop());
  }

  public void push(Object value) {
    this.add(value);
  }

  public Object pop() {
    if (this.empty()) {
      return null;
    } else {
      return remove(this.size() - 1);
    }
  }

  public Object peek() {
    if (this.empty()) {
      return null;
    } else {
      return get(this.size() - 1);
    }
  }


  public boolean empty() {
    return this.size() == 0;
  }

}
