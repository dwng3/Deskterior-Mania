package com.example.DTM.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@DynamicInsert
@Getter
@Setter
@Entity
public class Notice extends BaseEntity {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Notice(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void addImage(Image image) {
        images.add(image);
        image.setNotice(this);
    }

    public void removeImage(Image image) {
        images.remove(image);
        image.setNotice(null);
    }

//    public void update(NoticeUpdateDTO dto) {
//        this.title = dto.getTitle();
//        this.content = dto.getContent();
//    }


}
