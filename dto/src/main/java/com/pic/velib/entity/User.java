package com.pic.velib.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable( name = "users_stations",
            joinColumns = @JoinColumn( name = "id_user" ),
            inverseJoinColumns = @JoinColumn( name = "station_code" ) )
    private Set<Station> favoriteStations;


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

    public Set<Station> getFavoriteStations() {
        return favoriteStations;
    }

    public void setFavoriteStations(Set<Station> favoriteStations) {
        this.favoriteStations = favoriteStations;
    }
}
