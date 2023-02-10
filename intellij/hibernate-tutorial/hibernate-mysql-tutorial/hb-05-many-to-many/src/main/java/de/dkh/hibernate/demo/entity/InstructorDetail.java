package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Belongs to {@linkplain OneToOne} unidirectional example related to {@linkplain Instructor}.
 */
@Entity
@Table(name = "instructor_detail")
@NoArgsConstructor
public class InstructorDetail extends PersistentObject {

    public static EntityType ENTITY_TYPE = EntityType.INSTRUCTOR_DETAIL;

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    @Getter
    @Setter
    @Column(name = "youtube_channel")
    private String youtubeChannel;
    @Getter
    @Setter
    @Column(name = "hobby")
    private String hobby;

}
