package bitcamp.myapp;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

      System.out.println("헬스장 회원 정보 등록 시스템");
      System.out.println("----------------------------------");

      Scanner scanner = new Scanner(System.in);

      System.out.print("번호? ");
      int no = scanner.nextInt();

      System.out.print("이름? ");
      String name = scanner.next();

      System.out.print("나이? ");
      int age = scanner.nextInt();

      System.out.print("성별(남자:M, 여자:W)? ");
      String str = scanner.next();
      char gender = str.charAt(0);

      System.out.print("등록기간(개월)? ");
      int per = scanner.nextInt();
      

      System.out.println("---------------------------------------");


      System.out.printf("번호: %d\n", no);
      System.out.printf("이름: %s\n", name);
      System.out.printf("나이: %d\n",age);
      System.out.printf("성별(남자(M), 여자(W)): %c\n", gender);
      System.out.printf("등록기간: %d 개월\n", per);
  
      scanner.close();
    }
  }
  