// JSON 문자열 --> 객체 : JSON 문자열을 해석하여 객체를 생성하기
// 날짜 문자열 변환1
package com.eomcs.openapi.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Exam0121 {
  public static void main(String[] args) {

    // 1) JSON 문자열 준비
    String jsonStr =
        "{\"no\":100,\"name\":\"홍길동\",\"email\":\"hong@test.com\",\"password\":\"1111\",\"photo\":\"hong.gif\",\"tel\":\"010-2222-1111\",\"registeredDate\":\"2023-07-03\"}";

    // 2) JSON 처리 객체 준비
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    // 2023-07-03 날짜를 문자열로 받기 준비

    // 3) JSON 문자열을 가지고 객체 만들기
    Member m = gson.fromJson(jsonStr, Member.class);

    System.out.println(m);
  }
}

