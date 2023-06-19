package bitcamp.util;

public interface List {

  boolean add(Object value);

  Object get(int Ondex);

  Object[] toArray();

  Object remove(int index);

  boolean remove(Object value);

  int size();
}
