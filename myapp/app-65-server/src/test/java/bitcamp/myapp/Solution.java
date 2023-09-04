package bitcamp.myapp;

public class Solution {

  public static void main(String[] args) {
    String my_string = "3 + 4";
    int answer = 0;
    String[] strarr = my_string.split(" ");
    for (int i = 0; i < strarr.length; i++) {
      if (strarr[i].equals("+")) {
        answer += Integer.parseInt(strarr[i - 1]) + Integer.parseInt(strarr[i + 1]);
      } else if (strarr[i].equals("-")) {
        answer += Integer.parseInt(strarr[i - 1]) - Integer.parseInt(strarr[i + 1]);
      }
    }
    System.out.println(answer);
  }

}
