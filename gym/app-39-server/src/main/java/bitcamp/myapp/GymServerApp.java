package bitcamp.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import bitcamp.myapp.dao.BoardListDao;
import bitcamp.myapp.dao.MemberListDao;
import bitcamp.myapp.net.RequestEntity;
import bitcamp.myapp.net.ResponseEntity;

public class GymServerApp {

  int port;
  ServerSocket serverSocket;

  HashMap<String, Object> daoMap = new HashMap<>();

  public GymServerApp(int port) throws Exception {
    this.port = port;
    daoMap.put("member", new MemberListDao("member.json"));
    daoMap.put("board", new BoardListDao("board.json"));
  }

  public void close() throws Exception {
    serverSocket.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.out.println("실행 예) java ... bitcamp.myapp.ServerApp 포트번호");
      return;
    }

    GymServerApp app = new GymServerApp(Integer.parseInt(args[0]));
    app.excute();
    app.close();
  }

  public void excute() throws Exception {
    System.out.println("[bitcamp Gym 강남점]");
    this.serverSocket = new ServerSocket(port);
    System.out.println("서버 실행중...");

    Socket socket = serverSocket.accept();
    DataInputStream in = new DataInputStream(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

    while (true) {
      RequestEntity request = RequestEntity.fromJson(in.readUTF());

      String command = request.getCommand();
      System.out.println(command);

      if (command.equals("quit")) {
        break;
      }

      String[] values = command.split("/");
      String dataName = values[0];
      String methodName = values[1];

      Object dao = daoMap.get(dataName);
      if (dao == null) {
        out.writeUTF(
            new ResponseEntity().status(ResponseEntity.ERROR).result("데이터를 찾을 수 없습니다.").toJson());
        continue;
      }

      Method method = findMethod(dao, methodName);
      if (method == null) {
        out.writeUTF(
            new ResponseEntity().status(ResponseEntity.ERROR).result("데이터를 찾을 수 없습니다.").toJson());
        continue;
      }

      Object result = call(dao, method, request);
      ResponseEntity response = new ResponseEntity();
      response.status(ResponseEntity.SUCCESS);
      response.result(result);
      out.writeUTF(response.toJson());
    }


    in.close();
    out.close();
    socket.close();
  }

  public static Method findMethod(Object obj, String methodName) {
    Method[] methods = obj.getClass().getDeclaredMethods();
    for (int i = 0; i < methods.length; i++) {
      if (methods[i].getName().equals(methodName)) {
        return methods[i];
      }
    }
    return null;
  }

  public static Object call(Object obj, Method method, RequestEntity request) throws Exception {
    Parameter[] params = method.getParameters();
    if (params.length > 0) {
      return method.invoke(obj, request.getObject(params[0].getType()));
    } else {
      return method.invoke(obj);
    }
  }


}