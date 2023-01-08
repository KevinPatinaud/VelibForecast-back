package com.pic.velib.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable( name = "T_Users_STATIONS",
            joinColumns = @JoinColumn( name = "id_user" ),
            inverseJoinColumns = @JoinColumn( name = "stationCode" ) )
    private List<Station> favoriteStations;


    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Station> getFavoriteStations() {
        return favoriteStations;
    }

    public void setFavoriteStations(List<Station> favoriteStations) {
        this.favoriteStations = favoriteStations;
    }
}
