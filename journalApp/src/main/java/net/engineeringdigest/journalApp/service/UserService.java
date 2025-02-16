package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public class UserService {
    @Autowired
    private UserRepository userRepository ;
    public void saveUser(Optional<User> user){
        if(user.isPresent()){
            User user1 = user.get();
            userRepository.save(user1);
        }
    }

    public List<User> getAll(){
        return userRepository.findAll() ;
    }

    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id) ;
    }

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username) ;
    }

}
