package com.example.DTM.domain;


import com.example.DTM.dto.PostDTO;
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

    @Column(columnDefinition = "TEXT")
    private String content;

    private String imagePath;

    @ColumnDefault("0")
    private Long viewCount;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Member author;


    @Builder
    public Post(String title, String content, String imagePath) {
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
    }

    @Builder
    public Post(String title, String content, String imagePath, Member author) {
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.author = author;
    }

    public void updatePost(PostDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.imagePath = dto.getImagePath();
    }

    public static Post toEntity(PostDTO dto){
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .imagePath(dto.getImagePath())
                .build();
    }
}
