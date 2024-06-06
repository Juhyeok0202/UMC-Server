package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseTimeEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class ReviewImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Lob
    private String imageUrl;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}
