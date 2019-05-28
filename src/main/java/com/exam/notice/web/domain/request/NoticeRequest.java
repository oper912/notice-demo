package com.exam.notice.web.domain.request;

import com.exam.notice.web.domain.Notice;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NoticeRequest {

    @NotNull
    private String title;

    @NotNull
    private String writer;

    @NotNull
    private String content;

    public boolean isSame(Notice notice) {
        return notice.getTitle().equals(title)
                && notice.getWriter().equals(writer)
                && notice.getContent().equals(content);
    }
}
