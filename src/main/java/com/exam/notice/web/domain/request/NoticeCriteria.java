package com.exam.notice.web.domain.request;

import com.exam.notice.web.domain.Notice;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NoticeCriteria {

    private List<Notice> notices;
    private int noticesSize;

    private final int listSize = 5;
    private final int pageSize = 3;
    private int pagingCount;
    private int startPage;
    private int endPage;
}
