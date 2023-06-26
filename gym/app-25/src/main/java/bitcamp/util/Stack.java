package bitcamp.util;

public class Stack<E> extends LinkedList<E> {
  public static void main(String[] args) {
    Stack<String> s = new Stack<>();

    s.push("홍길동");
    s.push("유관순");
    s.push("안중근");

    Iterator<String> iterator = s.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  public void push(E value) {
    this.add(value);
  }

  public E pop() {
    if (this.empty()) {
      return null;
    } else {
      return remove(this.size() - 1);
    }
  }

  public E peek() {
    if (this.empty()) {
      return null;
    } else {
      return get(this.size() - 1);
    }
  }

  public boolean empty() {
    return this.size() == 0;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        return !Stack.this.empty();
      }

      @Override
      public E next() {
        return Stack.this.pop();
      }
    };
  }

}
