package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Belongs to {@linkplain OneToOne} unidirectional example related to {@linkplain Instructor}.
 */
@Entity
@Table(name = "instructor_detail")
@NoArgsConstructor
public class InstructorDetail extends PersistentObject {
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
    /*
    Here we are extend to bidirectional relation to `Instructor`.
    The difference: we need to reference the relation using existing `instructorDetail``object bound to `Instructor`.
     */
    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Instructor instructor;

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                ", instructor=" + instructor.getFirstName() + " " + instructor.getLastName() +
                '}';
    }

}
