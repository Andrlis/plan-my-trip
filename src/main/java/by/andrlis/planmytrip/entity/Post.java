package by.andrlis.planmytrip.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    private User author;

    @ManyToMany
    private ArrayList<Location> locations;

    @OneToMany
    private ArrayList<PostContent> postContentItems;

    @OneToMany
    private ArrayList<Comment> comments;
}
