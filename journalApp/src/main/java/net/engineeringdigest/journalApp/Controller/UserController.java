package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.entity.User;

import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService ;

    @PutMapping
        public ResponseEntity<User> updateUser( @RequestBody User user ){
        System.out.println("Hello");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
        String username = authentication.getName() ;
        User old = userService.getByUsername(username).orElse(null);
        if(old != null ){
            old.setId(user.getId()!=null && !user.getId().equals("") ? user.getId(): old.getId());
            old.setPassword(user.getPassword()!=null && !user.getPassword().equals("") ? user.getPassword(): old.getPassword());
            userService.saveUser(Optional.of(old));
            return new ResponseEntity<>(old,HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }

}
