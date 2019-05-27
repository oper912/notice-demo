package com.exam.notice.web.domain;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @OneToMany
    private List<Notice> notices;

    @Column(unique = true)
    private String userId;
    private String email;
    private String password;
}
