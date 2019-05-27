package com.exam.notice.web.api;

import com.exam.notice.web.domain.Notice;
import com.exam.notice.web.domain.request.NoticeRequest;
import com.exam.notice.web.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class NoticeApi {

    private NoticeService noticeService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/notices/{id}")
    public Notice show(@PathVariable Long id) {
//        try {
//            Notice notice = noticeService.doShow(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return noticeService.doShow(id);
    }

    @PostMapping(path = "/notices")
    public void create(@RequestBody NoticeRequest body) {
        // 밸리데이션 구현 널여부 등등
        System.out.println("-------------------------------------------------------------------");
        System.out.println(body.toString());
//        noticeService.doCreate(body);
    }

    @PutMapping(path = "/notices/{id}")
    public void update(@RequestBody NoticeRequest body) {
        System.out.println("-------------------------------------------------------------------");
        System.out.println(body.toString());
//        noticeService.doUpdate(body);
    }

    @DeleteMapping(path = "/notices/{id}")
    public void delete(@PathVariable Long id) {
        noticeService.doDelete(id);
    }
}
