package com.exam.notice.web.service;

import com.exam.notice.web.dao.NoticeDAO;
import com.exam.notice.web.domain.Notice;
import com.exam.notice.web.domain.request.NoticeCriteria;
import com.exam.notice.web.domain.request.NoticeRequest;
import com.exam.notice.web.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class NoticeService {

    private NoticeDAO noticeDAO;

    public NoticeCriteria getNotices(int page, String searchType, String searchWord) {

        Pageable pageable = PageRequest.of(page - 1, 5, new Sort(Sort.Direction.DESC, "id"));
        NoticeCriteria noticeCriteria = new NoticeCriteria();

        switch(searchType) {
            case "title":
                noticeCriteria.setNotices(noticeDAO.findByTitleContains(pageable, searchWord).getContent());
                noticeCriteria.setNoticesSize(noticeDAO.findByTitleContains(searchWord).size());
                break;
            case "writer":
                noticeCriteria.setNotices(noticeDAO.findByWriterContains(pageable, searchWord).getContent());
                noticeCriteria.setNoticesSize(noticeDAO.findByWriterContains(searchWord).size());
                break;
            default:
                noticeCriteria.setNotices(noticeDAO.findAll(pageable).getContent());
                noticeCriteria.setNoticesSize(noticeDAO.findAll().size());
                break;
        }

        return noticeCriteria;
    }

    public List<Notice> getAllNotices() {
        return noticeDAO.findAll();
    }

    public Notice getNotice(Long id) {
        return noticeDAO.findById(id).orElseThrow(() -> new NotFoundException(id, Notice.class));
    }

    @Transactional
    public void doCreate(NoticeRequest noticeRequest) {
        Notice notice = Notice.builder()
                .title(noticeRequest.getTitle())
                .writer(noticeRequest.getWriter())
                .content(noticeRequest.getContent())
                .build();
        noticeDAO.save(notice);
    }

    @Transactional
    public void doUpdate(Long id, NoticeRequest noticeRequest) {
        Notice prevNotice = noticeDAO.findById(id).orElseThrow(() -> new NotFoundException(id, Notice.class));

        if (!noticeRequest.isSame(prevNotice)) {
            prevNotice.setContent(noticeRequest.getContent());
            prevNotice.setTitle(noticeRequest.getTitle());
            noticeDAO.save(prevNotice);
        } else {
            log.info("have not changed");
        }
    }

    @Transactional
    public void doDelete(Long id) {
        noticeDAO.deleteById(id);
    }

    public NoticeCriteria getNoticeCriteria(NoticeCriteria noticeCriteria, int page) {
        noticeCriteria.setPagingCount((int)Math.ceil(noticeCriteria.getNoticesSize() / noticeCriteria.getListSize()));
        noticeCriteria.setStartPage((int)Math.floor(((page - 1) / noticeCriteria.getPageSize())) * noticeCriteria.getPageSize() + 1);
        noticeCriteria.setEndPage(noticeCriteria.getStartPage()  + noticeCriteria.getPageSize() - 1);

        if (noticeCriteria.getEndPage() > noticeCriteria.getPagingCount()) {
            noticeCriteria.setEndPage(noticeCriteria.getPagingCount());
        }

        return noticeCriteria;
    }
}
