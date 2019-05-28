package com.exam.notice.web.api;

import com.exam.notice.web.domain.Notice;
import com.exam.notice.web.domain.request.NoticeRequest;
import com.exam.notice.web.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class NoticeApi {

    private NoticeService noticeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/notices")
    public List<Notice> index() {
        return noticeService.getAllNotices();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/notices/{id}")
    public Notice show(@PathVariable Long id) {
        return noticeService.getNotice(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/notices")
    public void create(@RequestBody NoticeRequest body) {
        noticeService.doCreate(body);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/notices/{id}")
    public void update(@PathVariable Long id, @RequestBody NoticeRequest body) {
        noticeService.doUpdate(id, body);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/notices/{id}")
    public void delete(@PathVariable Long id) {
        noticeService.doDelete(id);
    }
}
