package by.andrlis.planmytrip.entity;

import javax.persistence.*;

@Entity
public class LocationContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Location post;

    @OneToOne
    private ContentSource contentSource;

    private Integer orderNumber;
}
