package by.andrlis.planmytrip.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ContentSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ContentSourceType type;
    @OneToOne
    private User sourceAuthor;
    private String url;
    private String text;
    @Lob
    @Column(length = 1000)
    private byte[] image;
}
