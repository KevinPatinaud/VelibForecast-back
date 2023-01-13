package com.pic.velib.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;

@Entity(name = "users")
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @javax.persistence.GeneratedValue
    UUID id;

    @Column(name = "username", unique = true)
    String username;

    String password;

    Boolean enabled = true;

    @ManyToMany
    @JoinTable(name = "users_stations",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "station_code"))
    private Set<Station> favoriteStations;


    public User() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Station> getFavoriteStations() {
        return favoriteStations;
    }

    public void setFavoriteStations(Set<Station> favoriteStations) {
        this.favoriteStations = favoriteStations;
    }
}
