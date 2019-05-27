package com.exam.notice.web.Controller;

import com.exam.notice.web.dao.UserDAO;
import com.exam.notice.web.domain.Notice;
import com.exam.notice.web.domain.User;
import com.exam.notice.web.domain.request.NoticeCriteria;
import com.exam.notice.web.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor // 초기화 되지 않은 final 필드와 @NonNull 필드에 대한 생성자 생성
@RequestMapping(path = "/")
public class NoticeController {

    private final UserDAO userDAO;
    private final NoticeService noticeService;
    private User fixedUser;

    @PostConstruct
    public void init() {
        fixedUser = userDAO.findAll().stream().findAny().orElseThrow(() -> new RuntimeException("user 없음"));
    }

    @GetMapping(path = {"/index", "/"})
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "searchType", defaultValue = "") String searchType,
                        @RequestParam(value = "searchWord", defaultValue = "") String searchWord, Model model) {

        NoticeCriteria noticeCriteria = noticeService.getList(page, searchType, searchWord);
        noticeCriteria = noticeService.getNoticeCriteria(noticeCriteria, page);

        model.addAttribute("user", fixedUser);
        model.addAttribute("noticeCriteria", noticeCriteria);
        model.addAttribute("page", page);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchWord", searchWord);

        return "index";
    }

    @GetMapping(path = "show/{id}")
    public String show(@PathVariable Long id, Model model) {
        Notice notice = noticeService.doShow(id);
        model.addAttribute("notice", notice);
        model.addAttribute("user", fixedUser);
        return "/show";
    }

    @GetMapping(path = "insert")
    public String insert(Model model) {
        model.addAttribute("user", fixedUser);
        return "/insert";
    }

    @GetMapping(path = "update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Notice notice = noticeService.doShow(id);
        model.addAttribute("notice", notice);
        model.addAttribute("user", fixedUser);
        return "/update";
    }

//    @PostMapping(path = "update/{id}")
//    public String postUpdate(@PathVariable Long id, @ModelAttribute NoticeRequest requestBody) {
//        noticeService.update(id, requestBody);
//        return "redirect:/show/" + id;
//    }

//    @PostMapping(path = "insert")
//    public String postInsert(@ModelAttribute NoticeRequest requestBody) {
//        noticeService.create(requestBody);
//        return "redirect:/index";
//    }

//    @GetMapping(path = "delete/{id}")
//    public String delete(@PathVariable Long id) {
//        noticeService.delete(id);
//        return "redirect:/index";
//    }
}
