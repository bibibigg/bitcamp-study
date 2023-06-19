package bitcamp.util;

public class LinkedList {

  Node head;
  Node tail;
  int size;

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.add(100);
    list.add(200);
    list.add(300);
    list.add(400);
    list.add(500);

    print(list);

    System.out.println(list.remove(300));
    System.out.println(list.remove(500));
    System.out.println(list.remove(200));
    System.out.println(list.remove(100));
    System.out.println(list.remove(400));
    System.out.println(list.remove(600));

    list.add(1000);
    list.add(2000);
    print(list);
  }

  static void print(LinkedList list) {
    Object[] arr = list.getList();
    for (Object obj : arr) {
      System.out.print(obj);
      System.out.print(", ");
    }
    System.out.println();
  }

  public void add(Object value) {
    Node node = new Node();
    node.value = value;

    if (head == null) {
      head = node;
    } else if (this.tail != null) {
      this.tail.next = node;
    }

    this.tail = node;
    this.size++;
  }

  public Object[] getList() {
    Object[] arr = new Object[this.size];

    Node cursor = this.head;
    for (int i = 0; i < this.size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }

  public Object retrieve(Object value) {
    Node cursor = this.head;

    while (cursor != null) {
      if (cursor.value.equals(value)) {
        return cursor.value;
      }
      cursor = cursor.next;
    }
    return null;
  }

  public boolean remove(Object value) {
    Node prev = null;
    Node cursor = this.head;

    while (cursor != null) {
      if (cursor.value.equals(value)) {
        if (prev == null) {
          head = cursor.next;

          if (head == null) {
            tail = null;
          }

        } else if (cursor.next == null) {
          tail = prev;
          tail.next = null;


        } else {
          prev.next = cursor.next;
        }
        size--;

        cursor.next = null;
        cursor.value = null;

        return true;
      }
      prev = cursor;
      cursor = cursor.next;
    }

    return false;
  }

  static class Node {
    Object value;
    Node next;
  }
}
