package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Belongs to {@linkplain OneToOne} example related to {@linkplain Instructor}.
 */
@Entity
@Table(name = "instructor_detail")
@NoArgsConstructor
@ToString
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @Column(name = "youtube_channel")
    private String youtubeChannel;
    @Getter
    @Setter
    @Column(name = "hobby")
    private String hobby;

}
