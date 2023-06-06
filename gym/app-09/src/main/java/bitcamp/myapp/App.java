package bitcamp.myapp;

import bitcamp.myapp.handler.GymMemberHandler;
import bitcamp.util.GymPrompt;

public class App {

    public static void main(String[] args) {

      GymprintTitle();

      while (GymMemberHandler.available()) {
        GymMemberHandler.InputGymMember();
        if(!promptContinue()) {
          break;
        }
    }

    GymMemberHandler.printGymMember();

    GymPrompt.close();
  }

    static void GymprintTitle() {
      System.out.println("헬스장 회원 정보 등록 시스템");
      System.out.println("----------------------------------");
    }

    static boolean promptContinue() {
      String response = GymPrompt.inputString("계속 하시겠습니까?(Y/n) ");
      if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
        return false;
      }
      return true;
    }
}
  
  