package com.exam.notice;

import com.exam.notice.web.dao.NoticeDAO;
import com.exam.notice.web.dao.UserDAO;
import com.exam.notice.web.domain.Notice;
import com.exam.notice.web.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class NoticeApplication {
    private final UserDAO userDAO;
    private final NoticeDAO noticeDAO;

    public static void main(String[] args) {
        SpringApplication.run(NoticeApplication.class, args);
    }

    @PostConstruct // 해당 스프링빈이 생성되고 나면 해당 액션을 실행하라
    public void initialize() {
        User user = userDAO.save(User.builder()
                .userId("soonho")
                .email("hi@gmail.com")
                .build()
        );
        for (int i = 0; i < 45; i++) {
            noticeDAO.save(Notice.builder()
                    .user(user).title("title-" +i).content("content-"+i).build());
        }
    }
}
