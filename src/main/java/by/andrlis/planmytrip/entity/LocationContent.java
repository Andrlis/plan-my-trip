package by.andrlis.planmytrip.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
