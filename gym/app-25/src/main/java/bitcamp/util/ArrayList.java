package bitcamp.util;

import java.lang.reflect.Array;

public class ArrayList<E> extends AbstractList<E> {
  private static final int DEFAULT_SIZE = 3;
  private Object[] list = new Object[DEFAULT_SIZE];

  @Override
  public boolean add(E obj) {
    if (this.size == list.length) {
      increase();
    }
    this.list[this.size++] = obj;
    return true;
  }

  public void increase() {
    Object[] arr = new Object[list.length + (list.length >> 1)];

    for (int i = 0; i < size; i++) {
      arr[i] = list[i];
    }
    list = arr;
    // System.out.println("배열증가" + arr.length);
  }

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[this.size];
    for (int i = 0; i < this.size; i++) {
      arr[i] = this.list[i];
    }
    return arr;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T[] toArray(T[] arr) {
    T[] values = null;

    if (arr.length < this.size) {
      values = (T[]) Array.newInstance(arr.getClass().getComponentType(), this.size);
    } else {
      values = arr;
    }

    for (int i = 0; i < this.size; i++) {
      values[i] = (T) list[i];
    }
    return values;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E get(int index) {
    if (!isValid(index)) {
      return null;
    }
    return (E) this.list[index];
  }

  @Override
  public boolean remove(E obj) {
    int deletedIndex = indexOf(obj);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.size - 1; i++) {
      this.list[i] = this.list[i + 1];
    }
    this.list[--this.size] = null;
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E remove(int index) {
    if (!isValid(index)) {
      return null;
    }

    Object old = this.list[index];

    for (int i = index; i < this.size - 1; i++) {
      this.list[i] = this.list[i + 1];
    }
    this.list[--this.size] = null;
    return (E) old;
  }

  private int indexOf(Object obj) {
    for (int i = 0; i < this.size; i++) {
      Object item = this.list[i];
      if (item.equals(obj)) {
        return i;
      }
    }
    return -1;
  }



}


