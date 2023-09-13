package bitcamp.myapp.controller;

import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MemberController {

  @Autowired
  MemberService memberService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @GetMapping("form")
  public void form() {
  }

  @PostMapping("add")
  public String add(
          Member member,
          MultipartFile photofile,
          Model model) throws Exception {

    try {
      if (photofile.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-13", "member/", photofile);
        member.setPhoto(uploadFileUrl);
      }

      memberService.add(member);
      return "redirect:list";

    } catch (Exception e) {
      model.addAttribute("message", "회원 등록 오류!");
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }

  @GetMapping("list")
  public void list(@RequestParam(name = "search", required = false) String searchKeyword, Model model) throws Exception {
    model.addAttribute("list", memberService.list());
    model.addAttribute("searchKeyword", searchKeyword);
  }

  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) throws Exception {
    model.addAttribute("member", memberService.get(no));
    model.addAttribute("member",
            memberService.calculateEndDate(
                    memberService.get(no)));
    return "member/detail";
  }

  @GetMapping("delete")
  public String delete(int no, Model model) throws Exception {

    try {
      if (memberService.delete(no) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } else {
        return "redirect:list";
      }

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }

  @PostMapping("update")
  public String update(
          Member member,
          MultipartFile photofile,
          Model model) throws Exception {

    try {
      if (photofile.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-13", "member/", photofile);
        member.setPhoto(uploadFileUrl);
      }

      if (memberService.update(member) == 0) {
        throw new Exception("회원이 없습니다.");
      } else {
        return "redirect:list";
      }
    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }

}
