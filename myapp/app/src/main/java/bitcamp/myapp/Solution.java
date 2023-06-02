package bitcamp.myapp;

class Solution {
  public int solution(int a, int b) {
    a = 2;
    b = 91;
    int answer = 0;
    answer = (Integer.parseInt(a + "" + b + "") > (2 * a * b)) ? Integer.parseInt(a + "" + b + "") : (2 * a * b);
    return answer;
  }
}