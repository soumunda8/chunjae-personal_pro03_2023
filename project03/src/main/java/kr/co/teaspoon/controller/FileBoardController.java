package kr.co.teaspoon.controller;

import kr.co.teaspoon.dto.Board;
import kr.co.teaspoon.dto.BoardVo;
import kr.co.teaspoon.dto.FileBoard;
import kr.co.teaspoon.service.BoardService;
import kr.co.teaspoon.service.FileBoardService;
import kr.co.teaspoon.util.BoardPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/fileBoard/**")
public class FileBoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private FileBoardService fileBoardService;

    @Autowired
    HttpSession session;

    private final String boardNm = "fileBoard";

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public String fileBoardList(HttpServletRequest request, Model model) throws Exception {

        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        BoardPage page = new BoardPage();
        page.setSearchType(type);
        page.setSearchKeyword(keyword);
        page.setBoardNm(boardNm);
        int total = boardService.boardListCount(page);

        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);

        List<BoardVo> fileBoardList = boardService.boardList(page);

        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("curPage", curPage);
        model.addAttribute("fileBoardList", fileBoardList);

        return "/fileBoard/fileBoardList";
    }

    @RequestMapping(value = "detail.do", method = RequestMethod.GET)
    public String fileBoardDetail(@RequestParam int no, Model model) throws Exception {

        String sid = session.getAttribute("sid") != null ? (String) session.getAttribute("sid") : "";

        if(no != 0) {
            BoardVo fileBoard = boardService.boardDetail(no);
            if(!sid.equals("admin")) {
                boardService.boardUpdateVisited(no);
                fileBoard.setVisited(fileBoard.getVisited() + 1);
            }

            FileBoard fileDTO = new FileBoard();
            fileDTO.setSeq(no);
            fileDTO.setBoardNm("fileBoard");
            List<FileBoard> fileList = fileBoardService.fileListByPar(fileDTO);
            if(fileList != null) {
                model.addAttribute("fileList", fileList);
            }

            model.addAttribute("fileBoard", fileBoard);
            return "/fileBoard/fileBoardDetail";
        } else {
            return "redirect:/fileBoard/list.do";
        }

    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public String fileBoardInsert(HttpServletRequest request, Model model, List<MultipartFile> uploadFiles) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(sid != null && sid.equals("admin")) {
            Board fileBoard = new Board();
            fileBoard.setTitle(request.getParameter("title"));
            fileBoard.setContent(request.getParameter("content"));
            fileBoard.setBoardNm(boardNm);
            fileBoard.setAuthor(sid);
            boardService.boardInsert(fileBoard);

            BoardVo boardVo = boardService.boardDetailLast();

            if (uploadFiles != null) {
                ServletContext application = request.getSession().getServletContext();
                String realPath = application.getRealPath("/resources/upload");                                        // 운영 서버
                //String realPath = "D:\\project\\team\\project4\\team44\\src\\main\\webapp\\resources\\upload";	      // 개발 서버

                SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
                Date date = new Date();
                String dateFolder = sdf.format(date);

                File uploadPath = new File(realPath, dateFolder);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                for (MultipartFile multipartFile : uploadFiles) {
                    if (multipartFile.isEmpty()) {
                        continue;
                    }

                    String originalFilename = multipartFile.getOriginalFilename();
                    UUID uuid = UUID.randomUUID();
                    String uploadFilename = uuid.toString() + "_" + originalFilename;

                    FileBoard fileDTO = new FileBoard();
                    fileDTO.setSeq(boardVo.getSeq());
                    fileDTO.setSaveFolder(dateFolder);
                    fileDTO.setOriginNm(originalFilename);
                    fileDTO.setSaveNm(uploadFilename);
                    fileDTO.setBoardNm(boardNm);

                    multipartFile.transferTo(new File(uploadPath, uploadFilename));     // 서버에 파일 업로드 수행
                    fileBoardService.filesInsert(fileDTO);                              // DB 등록
                }

            }

            return "redirect:/admin/fileBoardList.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "edit.do", method = RequestMethod.POST)
    public String fileBoardUpload(HttpServletRequest request, Model model, List<MultipartFile> uploadFiles) throws Exception {

        String sid = (String) session.getAttribute("sid");

        int seq = Integer.parseInt(request.getParameter("no"));

        if(sid != null && sid.equals("admin")) {
            Board fileBoard = new Board();
            fileBoard.setTitle(request.getParameter("title"));
            fileBoard.setContent(request.getParameter("content"));
            fileBoard.setSeq(seq);
            boardService.boardUpdate(fileBoard);

            if (uploadFiles != null) {
                System.out.println("파일 있음!!");

                ServletContext application = request.getSession().getServletContext();
                String realPath = application.getRealPath("/resources/upload");                                        // 운영 서버
                //String realPath = "D:\\project\\team\\project4\\team44\\src\\main\\webapp\\resources\\upload";	      // 개발 서버

                SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
                Date date = new Date();
                String dateFolder = sdf.format(date);

                File uploadPath = new File(realPath, dateFolder);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                for (MultipartFile multipartFile : uploadFiles) {
                    if (multipartFile.isEmpty()) {
                        continue;
                    }

                    String originalFilename = multipartFile.getOriginalFilename();
                    UUID uuid = UUID.randomUUID();
                    String uploadFilename = uuid.toString() + "_" + originalFilename;

                    FileBoard fileDTO = new FileBoard();
                    fileDTO.setSeq(seq);
                    fileDTO.setSaveFolder(dateFolder);
                    fileDTO.setOriginNm(originalFilename);
                    fileDTO.setSaveNm(uploadFilename);
                    fileDTO.setBoardNm(boardNm);

                    multipartFile.transferTo(new File(uploadPath, uploadFilename));     // 서버에 파일 업로드 수행
                    fileBoardService.filesInsert(fileDTO);                              // DB 등록
                }

            }

            System.out.println("파일 없음");

            return "redirect:/admin/fileBoardList.do";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "delPro.do", method = RequestMethod.GET)
    public String fileBoardDelete(@RequestParam int no, HttpServletRequest request,Model model) throws Exception {

        String sid = (String) session.getAttribute("sid");

        if(no != 0 && sid != null && sid.equals("admin")) {

            ServletContext application = request.getSession().getServletContext();
            String realPath = application.getRealPath("/resources/upload/");

            FileBoard fileDTO = new FileBoard();
            fileDTO.setSeq(no);
            fileDTO.setBoardNm(boardNm);
            List<FileBoard> fileList = fileBoardService.fileListByPar(fileDTO);
            for(FileBoard files : fileList) {
                File file = new File(realPath + files.getSaveFolder() + File.separator + files.getSaveNm());
                if (file.exists()) {
                    file.delete();
                }
            }
            fileBoardService.filesDeleteAll(no);
            boardService.boardDelete(no);
            return "redirect:/admin/fileBoardList.do";
        } else {
            return "redirect:/";
        }

    }


}