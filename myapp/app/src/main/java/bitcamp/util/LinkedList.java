package bitcamp.util;

public class LinkedList implements List {

  Node head;
  Node tail;
  int size;

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.add(Integer.valueOf(100)); // index: 0
    list.add(Integer.valueOf(200)); // index: 1
    list.add(Integer.valueOf(300)); // index: 2
    list.add(Integer.valueOf(400)); // index: 3
    list.add(Integer.valueOf(500)); // index: 4

    print(list);

    // System.out.println(list.remove(Integer.valueOf(300)));
    // System.out.println(list.remove(Integer.valueOf(500)));
    // System.out.println(list.remove(Integer.valueOf(100)));
    // System.out.println(list.remove(Integer.valueOf(200)));
    // System.out.println(list.remove(Integer.valueOf(400)));
    // System.out.println(list.remove(Integer.valueOf(600)));

    System.out.println(list.remove(2));
    System.out.println(list.remove(3));
    System.out.println(list.remove(0));
    System.out.println(list.remove(0));
    System.out.println(list.remove(0));

    list.add(Integer.valueOf(1000));
    list.add(Integer.valueOf(2000));

    print(list);
    // System.out.println(list.retrieve(100));
    // System.out.println(list.retrieve(300));
    // System.out.println(list.retrieve(500));
    // System.out.println(list.retrieve(600));
  }

  static void print(LinkedList list) {
    Object[] arr = list.toArray();
    for (Object obj : arr) {
      System.out.print(obj);
      System.out.print(", ");
    }
    System.out.println();
  }

  @Override
  public boolean add(Object value) {
    Node node = new Node(); // node인 레퍼런스 변수에 Node의 인스턴스 저장
    node.value = value; // node가 가리키는 value에 value값 저장

    if (head == null) {
      head = node;
    } else if (this.tail != null) {
      this.tail.next = node; // 두번째 node생성시 처음node tail에 주소가 저장되어 있기에
                             // tail이 가리키는 next에 두번째 주소를 저장
    }
    this.tail = node; // 처음에 node생성시 tail은 null이기에 tail에 새 node주소 저장
                      // 이후 다른 노드 생성시 teil.next에 node의 주소를 저장한 후 tail에도 주소를 저장
    this.size++;
    return true;
  }

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[this.size];

    Node cursor = this.head;
    for (int i = 0; i < this.size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }

    return arr;
  }

  public Object get(int index) {
    if (!isValid(index)) {
      return null;
    }

    Node cursor = this.head;

    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }

    return cursor.value;
  }

  @Override
  public boolean remove(Object value) {
    Node prev = null;
    Node cursor = this.head;

    while (cursor != null) {
      if (cursor.value.equals(value)) {
        if (prev == null) {
          // 삭제할 노드가 시작 노드라면
          head = cursor.next;

          // 삭제할 노드가 끝 노드라면
          if (head == null) {
            tail = null;
          }

        } else if (cursor.next == null) {
          // 삭제할 노드가 끝 노드라면
          tail = prev;
          tail.next = null;
        } else {
          // 중간 노드라면, 다음 노드의 주소를 이전 노드에 저장한다.
          prev.next = cursor.next;
        }
        size--;

        // 가비지 객체를 초기화시켜서 가비지가 인스턴스를 가리키지 않도록 한다.
        cursor.next = null;
        cursor.value = null;

        return true;
      }
      // 현재 커서가 가리키는 노드를 prev에 보관한다.
      prev = cursor;

      // 현재 커서를 다음 노드로 이동한다.
      cursor = cursor.next;
    }

    return false;
  }

  public Object remove(int index) {
    if (!isValid(index)) {
      return null;
    }

    // 삭제하려는 값이 있는 노드까지 이동한다,
    Node prev = null;
    Node cursor = this.head;

    for (int i = 0; i < index; i++) {
      prev = cursor; // 다음 노드로 이동하기 전에 현재 커서가 가리키는 노드를 prev에 보관한다.
      cursor = cursor.next; // 커서를 다음 노드로 이동시킨다.
    }

    // 삭제할 값을 리턴할 수 있도록 보관한다.
    Object old = cursor.value;

    if (prev == null) {
      head = cursor.next;
      if (head == null) {
        tail = null;
      }
    } else if (cursor.next == null) {
      tail = prev;
      tail.next = null;
    } else {
      prev.next = cursor.next; // 현재 커서의 다음 노드를 현재 커서의 이전 노드와 연결한다.
    }
    size--;
    cursor.next = null;
    cursor.value = null;

    return old;
  }

  @Override
  public int size() {
    return this.size();
  }

  private boolean isValid(int index) {
    return index >= 0 && index < this.size;
  }

  static class Node {
    Object value;
    Node next;
  }

}

