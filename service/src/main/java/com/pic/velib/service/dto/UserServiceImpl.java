package com.pic.velib.service.dto;

import com.pic.velib.entity.Station;
import com.pic.velib.entity.User;
import com.pic.velib.entity.UserFacebook;
import com.pic.velib.entity.UserMail;
import com.pic.velib.repository.StationRepository;
import com.pic.velib.repository.UserFacebookRepository;
import com.pic.velib.repository.UserMailRepository;
import com.pic.velib.repository.UserRepository;
import com.pic.velib.service.dto.exception.UserAlreadyExistException;
import com.pic.velib.service.dto.exception.UserNotExistException;
import com.pic.velib.service.facebook.FacebookLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Service
@ComponentScan(basePackages = {"com.pic.velib.service.facebook"})
public class UserServiceImpl implements UserService {

    FacebookLogin fbLogin;

    UserFacebookRepository userFacebookRepository;

    UserRepository userRepository;
    UserMailRepository userMailRepository;

    StationRepository stationRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    protected UserServiceImpl(UserRepository userRepository, UserFacebookRepository userFacebookRepository, UserMailRepository userMailRepository, FacebookLogin fbLogin, PasswordEncoder passwordEncoder, StationRepository stationRepository) {
        this.userRepository = userRepository;
        this.userFacebookRepository = userFacebookRepository;
        this.userMailRepository = userMailRepository;
        this.fbLogin = fbLogin;
        this.passwordEncoder = passwordEncoder;
        this.stationRepository = stationRepository;
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserFacebook createUserFacebook(String accessToken) throws UserAlreadyExistException {

        String userIdFacebook = fbLogin.confirmToken(accessToken.toString());

        if (findUserByFacebookID(userIdFacebook) != null) {
            throw new UserAlreadyExistException();
        }

        if (userIdFacebook != null) {
            UserFacebook user = new UserFacebook();
            user.setFacebookId(userIdFacebook);
            return userFacebookRepository.save(user);
        }
        return null;
    }

    @Override
    public UserFacebook getUserFacebook(String accessToken) throws UserNotExistException {

        String userIdFacebook = fbLogin.confirmToken(accessToken.toString());

        UserFacebook userFB = findUserByFacebookID(userIdFacebook);
        if (userFB == null) {
            throw new UserNotExistException();
        }

        return userFB;
    }

    @Override
    public UserMail createUserMail(String email, String password) throws UserAlreadyExistException {
        if (getUserMail(email) != null) throw new UserAlreadyExistException();

        UserMail user = new UserMail();
        user.setMail(email);
        user.setUsername(email);
        user.setPassword(passwordEncoder.encode(password));
        return userMailRepository.save(user);
    }


    @Override
    public UserMail getUserMail(String mail) {
        return userMailRepository.findByMail(mail);
    }

    @Override
    public UserFacebook findUserByFacebookID(String facebookId) {
        return userFacebookRepository.findByFacebookid(facebookId);
    }

    @Override
    public void addFavoriteStation(int id_station, UUID id_user) throws UserNotExistException {

        Optional<User> userBDD = userRepository.findById(id_user);

        if (!userBDD.isPresent()) throw new UserNotExistException();

        User user = userBDD.get();

        Set<Station> stations = user.getFavoriteStations();

        stations.add(stationRepository.findByStationCode(id_station));

        user.setFavoriteStations(stations);


        userRepository.save(user);

    }

    @Override
    public void removeFavoriteStation(int id_station, UUID id_user) throws UserNotExistException {
        Optional<User> userBDD = userRepository.findById(id_user);

        if (!userBDD.isPresent()) throw new UserNotExistException();

        User user = userBDD.get();

        Set<Station> stations = user.getFavoriteStations();
        Set<Station> stationsResult = new HashSet<Station>();

        for (Station station : stations)
            if (station.getStationCode() != id_station) stationsResult.add(station);


        user.setFavoriteStations(stationsResult);


        userRepository.save(user);
    }

}
