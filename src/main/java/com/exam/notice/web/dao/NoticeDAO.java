package com.exam.notice.web.dao;

import com.exam.notice.web.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDAO extends JpaRepository<Notice, Long> {

    Page<Notice> findAll(Pageable pageable);

    Page<Notice> findByTitleContains(Pageable pageable, String title);
    List<Notice> findByTitleContains(String title);

    Page<Notice> findByWriterContains(Pageable pageable, String title);
    List<Notice> findByWriterContains(String title);

}
