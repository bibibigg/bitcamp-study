package bitcamp.myapp;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

      System.out.println("헬스장 회원 정보 등록 시스템");
      System.out.println("----------------------------------");

      Scanner scanner = new Scanner(System.in);

      final int MAX_SIZE = 3;
      int[] no = new int[MAX_SIZE];
      String[] name = new String[MAX_SIZE];
      int[] age = new int[MAX_SIZE];
      char[] gender = new char[MAX_SIZE];
      int[] per = new int[MAX_SIZE];

      for (int i = 0; i < MAX_SIZE; i++) {

      System.out.print("번호? ");
      no[i] = scanner.nextInt();

      System.out.print("이름? ");
      name[i] = scanner.next();

      System.out.print("나이? ");
      age[i] = scanner.nextInt();

      System.out.print("성별(남자:M, 여자:W)? ");
      String str = scanner.next();
      gender[i] = str.charAt(0);

      System.out.print("등록기간(개월)? ");
      per[i] = scanner.nextInt();
      
  }
      System.out.println("---------------------------------------");

      for (int i = 0; i < MAX_SIZE; i++) {
      System.out.printf("번호: %d\n", no[i]);
      System.out.printf("이름: %s\n", name[i]);
      System.out.printf("나이: %d\n",age[i]);
      System.out.printf("성별(남자(M), 여자(W)): %c\n", gender[i]);
      System.out.printf("등록기간: %d 개월\n", per[i]);
     }
      scanner.close();
    
    }
  }
  