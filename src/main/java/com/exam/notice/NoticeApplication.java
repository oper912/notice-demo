package com.exam.notice;

import com.exam.notice.web.dao.NoticeDAO;
import com.exam.notice.web.domain.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class NoticeApplication {

    private final NoticeDAO noticeDAO;

    public static void main(String[] args) {
        SpringApplication.run(NoticeApplication.class, args);
    }

    @PostConstruct
    public void initialize() {

        for (int i = 0; i < 45; i++) {
            noticeDAO.save(Notice.builder()
                    .title("공지사항 - " + i + "번 글입니다. 필독")
                    .writer("admin" + i)
                    .content("content -" + i).build());
        }
    }
}
