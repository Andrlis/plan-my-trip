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
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type; //Should be changed to Enum
    private String description;
    @ManyToOne
    private GeoPoint geoPoint;
    @OneToMany
    private List<LocationContent> locationContentItems;
}
