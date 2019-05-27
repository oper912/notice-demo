package com.exam.notice.web.service;

import com.exam.notice.web.dao.NoticeDAO;
import com.exam.notice.web.dao.UserDAO;
import com.exam.notice.web.domain.Notice;
import com.exam.notice.web.domain.User;
import com.exam.notice.web.domain.request.NoticeRequest;
import com.exam.notice.web.domain.request.NoticeCriteria;
import com.exam.notice.web.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class NoticeService {

    private NoticeDAO dao;
    private UserDAO userDAO;

    public NoticeCriteria getList(int page, String searchType, String searchWord) {

        Pageable pageable = PageRequest.of(page - 1, 5, new Sort(Sort.Direction.DESC, "id"));
        NoticeCriteria noticeCriteria = new NoticeCriteria();

        switch(searchType) {
            case "title":
                noticeCriteria.setNotices(dao.findByTitleContains(pageable, searchWord).getContent());
                noticeCriteria.setNoticesSize(dao.findByTitleContains(searchWord).size());
                break;
            case "user":
                break;
            default:
                noticeCriteria.setNotices(dao.findAll(pageable).getContent());
                noticeCriteria.setNoticesSize(dao.findAll().size());
                break;
        }

        return noticeCriteria;
    }

    public Notice doShow(Long id) {
        return dao.findById(id).orElseThrow(() -> new NotFoundException(id, Notice.class));
    }

    @Transactional
    public void doCreate(NoticeRequest noticeRequest) {
        User user = userDAO.findById(noticeRequest.getUserId()).orElseThrow(() -> new NotFoundException(noticeRequest.getUserId(), User.class));
        Notice notice = Notice.builder()
                .content(noticeRequest.getContent())
                .title(noticeRequest.getContent())
                .user(user)
                .build();
        dao.save(notice);
    }

    @Transactional
    public void doUpdate(Long id, NoticeRequest noticeRequest) {
        Notice prevNotice = dao.findById(id).orElseThrow(() -> new NotFoundException(id, Notice.class));

        if (!noticeRequest.isSame(prevNotice)) {
            prevNotice.setContent(noticeRequest.getContent());
            prevNotice.setTitle(noticeRequest.getTitle());
            dao.save(prevNotice);
        } else {
            log.info("안바뀜");
        }
    }

    @Transactional
    public void doDelete(Long id) {
        dao.deleteById(id);
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
