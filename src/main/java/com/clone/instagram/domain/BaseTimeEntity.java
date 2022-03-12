package com.clone.instagram.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // 해당 클래스를 JPA Entity 클래스가 상속할 경우 필드를 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class)      // 클래스에 Auditing 기능 포함
public class BaseTimeEntity {

    @CreatedDate        // Entity 가 저장될 때 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate   // Entity 의 s값이 변경될 때 시간 자동 저장
    private LocalDateTime modifiedDate;
}
