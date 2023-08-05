package by.andrlis.planmytrip.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    private User author;

    @ManyToMany
    private List<Location> locations;

    @OneToMany
    private List<PostContent> postContentItems;

    @OneToMany
    private List<Comment> comments;
}
