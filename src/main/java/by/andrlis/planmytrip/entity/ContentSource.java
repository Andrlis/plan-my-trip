package by.andrlis.planmytrip.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ContentSourceType type;
    @OneToOne
    private User sourceAuthor;
    private String url;
    //@Lob
    @Column(length = 2550)
    private String text;
    //@Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(length = 1000)
    private byte[] image;
}
