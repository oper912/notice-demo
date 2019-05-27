package com.exam.notice.web.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate // jpa 는 기본적으로 모두 업데이트 치는데 변경이 생긴 컬럼만 업데이트 쿼리 날림
public class Notice extends BaseEntity {

    @Column(nullable = false)
    private String title;

    private String content;

    @ManyToOne
    private User user;

}
