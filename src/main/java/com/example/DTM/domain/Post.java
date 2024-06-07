package com.example.DTM.domain;


import com.example.DTM.dto.post.PostUpdateDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;


@NoArgsConstructor
@Getter
@Setter
@Entity
@DynamicInsert
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(String title, String content, String imagePath, Member member) {
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.member = member;
    }

    public void update(PostUpdateDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.imagePath = dto.getImagePath();
    }
}
