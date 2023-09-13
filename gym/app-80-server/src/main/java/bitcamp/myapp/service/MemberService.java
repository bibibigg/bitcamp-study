package bitcamp.myapp.service;

import bitcamp.myapp.vo.Member;

import java.util.List;

public interface MemberService {
  int add(Member member) throws Exception;

  List<Member> list() throws Exception;

  Member calculateEndDate(Member member) throws Exception;

  Member get(int memberNo) throws Exception;

  Member get(String phoneNumber, String password) throws Exception;

  int update(Member member) throws Exception;

  int delete(int memberNo) throws Exception;
}
