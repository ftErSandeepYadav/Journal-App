package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.entity.User;

import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService ;

    @GetMapping("all")
    public ResponseEntity<?> getAll(){
        List<User> all = userService.getAll() ;
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            userService.saveUser(Optional.ofNullable(user));
            return new ResponseEntity<>(user, HttpStatus.CREATED) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }
    }
    @GetMapping("id/{myId}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId myId){
        Optional<User> user = userService.getById(myId);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK) ;
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        Optional<User> user = userService.getByUsername(username) ;
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK) ;
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable ObjectId id, @RequestBody User user ){
        User old = userService.getById(id).orElse(null);
        if(old != null ){
            old.setId(user.getId()!=null && !user.getId().equals("") ? user.getId(): old.getId());
            old.setPassword(user.getPassword()!=null && !user.getPassword().equals("") ? user.getPassword(): old.getPassword());
            userService.saveUser(Optional.of(old));
            return new ResponseEntity<>(old,HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }

    @PutMapping("username/{username}")
    public ResponseEntity<User> updateUserById(@PathVariable String username, @RequestBody User user ){
        User old = userService.getByUsername(username).orElse(null);
        if(old != null ){
            old.setId(user.getId()!=null && !user.getId().equals("") ? user.getId(): old.getId());
            old.setPassword(user.getPassword()!=null && !user.getPassword().equals("") ? user.getPassword(): old.getPassword());
            userService.saveUser(Optional.of(old));
            return new ResponseEntity<>(old,HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }

//    @DeleteMapping("id/{myId}")
//    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
//        journalEntryService.deleteById(myId);
//        return  new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
//    }
}
