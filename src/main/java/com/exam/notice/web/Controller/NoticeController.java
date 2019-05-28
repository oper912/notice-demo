package com.exam.notice.web.Controller;

import com.exam.notice.web.domain.request.NoticeCriteria;
import com.exam.notice.web.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor // 초기화 되지 않은 final 필드와 @NonNull 필드에 대한 생성자 생성
@RequestMapping(path = "/")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping(path = {"/index", "/"})
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "searchType", defaultValue = "") String searchType,
                        @RequestParam(value = "searchWord", defaultValue = "") String searchWord, Model model) {

        NoticeCriteria noticeCriteria = noticeService.getNotices(page, searchType, searchWord);
        noticeCriteria = noticeService.getNoticeCriteria(noticeCriteria, page);

        model.addAttribute("noticeCriteria", noticeCriteria);
        model.addAttribute("page", page);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchWord", searchWord);

        return "index";
    }

    @GetMapping(path = "show/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("noticeId", id);
        return "/show";
    }

    @GetMapping(path = "create")
    public String create() {
        return "create";
    }

    @GetMapping(path = "update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("noticeId", id);
        return "/update";
    }
}
