package bitcamp.myapp.controller;

import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.service.NcpObjectStorageService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  BoardService boardService;
  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  {
    System.out.println("BoardController 생성됨!");
  }

  @GetMapping("form")
  public void form() {

  }

  @PostMapping("add")
  public String add(
          Board board,
          MultipartFile[] files,
          Model model,
          HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:/auth/form";
    }
    board.setWriter(loginUser);

    try {
      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      for (MultipartFile part : files) {
        if (part.getSize() > 0) {
          String uploadFileUrl = ncpObjectStorageService.uploadFile(
                  "bitcamp-nc7-bucket-13", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);

      boardService.add(board);
      return "redirect:/board/list?category=" + board.getCategory();

    } catch (Exception e) {
      model.addAttribute("message", "게시글 등록 오류!");
      model.addAttribute("refresh", "2;url=/board/list?category=" + board.getCategory());
      throw e;
    }
  }

  @GetMapping("delete")
  public String delete(
          int no,
          int category,
          Model model,
          HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:/auth/form";
    }

    try {
      Board b = boardService.get(no);

      if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        boardService.delete(b.getNo());
        return "redirect:/board/list?category=" + category;
      }

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=/board/list?category=" + category);
      throw e;
    }
  }

  @GetMapping("detail/{category}/{no}")
  public String detail(
          @PathVariable int category,
          @PathVariable int no,
          Model model) throws Exception {

    try {
      Board board = boardService.get(no);

      if (board != null) {
        boardService.increaseViewCount(no);
        model.addAttribute("board", board);
      }
      return "board/detail";

    } catch (Exception e) {
      model.addAttribute("refresh", "5;url=/board/list?category=" + category);
      throw e;
    }
  }

  @GetMapping("list")
  public void list(
          @RequestParam(name = "search", required = false)
          String searchKeyword,
          int category,
          Model model) throws Exception {

    try {
      model.addAttribute("list", boardService.list(category));
      model.addAttribute("searchKeyword", searchKeyword);
    } catch (Exception e) {
      model.addAttribute("refresh", "1;url=/");
      throw e;
    }
  }

  @PostMapping("update")
  public String update(
          Board board,
          MultipartFile[] files,
          Model model,
          HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:/auth/form";
    }

    try {
      Board b = boardService.get(board.getNo());
      if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("게시글이 존재하지 않거나 변경 권한이 없습니다.");
      }

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      for (MultipartFile part : files) {
        if (part.getSize() > 0) {
          String uploadFileUrl = ncpObjectStorageService.uploadFile(
                  "bitcamp-nc7-bucket-13", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);

      boardService.update(board);

      return "redirect:/board/list?category=" + b.getCategory();

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=/board/detail/" + board.getCategory() + "/" + board.getNo());
      throw e;
    }
  }

  @GetMapping("fileDelete/{attachedFile}") // 예) .../fileDelete/fileNo=30
  public String fileDelete(
          @MatrixVariable("no") int no,
          Model model,
          HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:/auth/form";
    }

    Board board = null;
    try {

      AttachedFile attachedFile = boardService.getAttachedFile(no);
      board = boardService.get(attachedFile.getBoardNo());

      if (board.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("게시글 변경 권한이 없습니다!");
      }

      if (boardService.deleteAttachedFile(no) == 0) {
        throw new Exception("해당 번호의 첨부파일이 없다.");
      } else {
        return "redirect:/board/detail/" + board.getCategory() + "/" + board.getNo();
      }

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=/board/detail/" + board.getCategory() + "/" + board.getNo());
      throw e;
    }
  }

}











