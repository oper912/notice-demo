package com.exam.notice.web.domain.request;

import com.exam.notice.web.domain.Notice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NoticeRequest {
    private final String title;
    private final String content;
    private final Long userId;

    public boolean isSame(Notice notice) {
        return notice.getContent().equals(content) && notice.getTitle().equals(title);
    }
}
