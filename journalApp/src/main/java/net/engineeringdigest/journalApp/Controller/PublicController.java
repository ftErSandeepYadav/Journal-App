package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService ;

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            userService.saveUser(Optional.ofNullable(user));
            return new ResponseEntity<>(user, HttpStatus.CREATED) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }
    }
}
