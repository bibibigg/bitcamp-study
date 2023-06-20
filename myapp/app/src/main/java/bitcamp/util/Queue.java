package bitcamp.util;

public class Queue extends LinkedList {

  public static void main(String[] args) {
    Queue q = new Queue();
    q.offer("홍길동");
    q.offer("임꺽정");
    q.offer("유관순");
    q.offer("안중근");
    q.offer("윤봉길");

    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());
    System.out.println(q.poll());


  }

  public void offer(Object value) {
    this.add(value);
  }

  public Object poll() { // 선입선출 원칙에 따라 요소를 저장하고 액세스
    if (this.size() == 0) { // 비어있을 시 null을 반환
      return null;
    }
    return this.remove(0); // size가 0이 아닐시 요소를 제거
  }

}
