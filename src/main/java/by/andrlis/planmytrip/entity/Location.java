package by.andrlis.planmytrip.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
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
    private ArrayList<LocationContent> locationContentItems;
}
