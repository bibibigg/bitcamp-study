package bitcamp.util;

public class Queue extends LinkedList {
  public static void main(String[] args) {
    Queue q = new Queue();
    q.offer("홍길동");
    q.offer("유관순");
    q.offer("안중근");

    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
  }

  public void offer(Object value) {
    this.add(value);
  }

  public Object poll() {
    if (this.size() == 0) {
      return null;
    } else {
      return remove(0);
    }
  }

}
