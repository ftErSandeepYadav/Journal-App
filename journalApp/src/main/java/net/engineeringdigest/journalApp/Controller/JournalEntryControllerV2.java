package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.JournalEntryServiceThroughUser;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryServiceThroughUser journalEntryServiceThroughUser ;
    @Autowired
    private UserService userService ;

    @GetMapping("all/{userName}")
    public ResponseEntity<?> getAllGeneralEntryOfUser(@PathVariable String userName){
        Optional<User> user = userService.getByUsername(userName) ;
        List<JournalEntry> all = new ArrayList<>();
        if(user.isPresent()) all = user.get().getJournalEntries(); ;
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){
        try {
            journalEntryServiceThroughUser.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }
    }
//    @GetMapping("id/{myId}")
//    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
//        Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
//        if(journalEntry.isPresent()){
//            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK) ;
//        }
//        else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @DeleteMapping("id/{myId}")
//    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
//        journalEntryService.deleteById(myId);
//        return  new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
//    }
//
//    @PutMapping("id/{id}")
//    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry ){
//        JournalEntry old = journalEntryService.getById(id).orElse(null);
//        if(old != null ){
//            old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("") ? myEntry.getTitle(): old.getTitle());
//            old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("") ? myEntry.getContent(): old.getContent());
//            journalEntryService.saveEntry(old);
//            return new ResponseEntity<>(old,HttpStatus.OK) ;
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
//    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId ){
        journalEntryServiceThroughUser.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
    }
}
