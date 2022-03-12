package com.clone.instagram.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.clone.instagram.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter                 // Getter 메소드 자동 생성
@NoArgsConstructor      // 기본 생성자 자동 추가
@Entity                 // 테이블과 링크될 클래스임을 표시
public class Posts extends BaseTimeEntity {

    @Id     // 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // PK의 생성 규칙
    private Long id;

    // 칼럼에 추가로 변경이 필요한 옵션이 있을 때 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    // 해당 클래스의 빌더 패턴 클래스 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
