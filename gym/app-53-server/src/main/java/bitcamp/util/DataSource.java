package bitcamp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
  String jdbcUrl;
  String username;
  String password;

  List<Connection> connectionPool = new ArrayList<>();

  ThreadLocal<Connection> connectionBox = new ThreadLocal<>();

  public DataSource(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection(boolean autoCommit) throws Exception {
    Connection con = this.getConnection();
    con.setAutoCommit(autoCommit);
    return con;
  }

  public Connection getConnection() throws Exception {

    Connection con = connectionBox.get();

    if (con == null) {

      if (connectionPool.size() > 0) {
        con = connectionPool.remove(0);
        System.out.printf("[%s] - DB 커넥션풀에서 꺼냄!\n", Thread.currentThread().getName());

      } else {
        con = DriverManager.getConnection(jdbcUrl, username, password);
        con.setAutoCommit(true);
        System.out.printf("[%s] - 새 DB 커넥션 생성!\n", Thread.currentThread().getName());
      }

      connectionBox.set(con);
      System.out.printf("[%s] - 스레드에 커넥션 객체 보관!\n", Thread.currentThread().getName());

    } else {
      System.out.printf("[%s] - 스레드에 보관된 커넥션 사용!\n", Thread.currentThread().getName());
    }
    return con;
  }

  public void clean() {
    Connection con = connectionBox.get();

    if (con != null) {
      try {con.rollback();} catch (Exception e) {}
      
      connectionPool.add(con);
      System.out.printf("[%s] - 커넥션풀에 DB 커넥션 저장!\n", Thread.currentThread().getName());

      connectionBox.remove();

      System.out.printf("[%s] - 스레드에서 커넥션 제거!\n", Thread.currentThread().getName());
    }
  }

}
