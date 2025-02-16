//package net.engineeringdigest.journalApp.Controller;
//
//import net.engineeringdigest.journalApp.entity.JournalEntry;
//import net.engineeringdigest.journalApp.entity.User;
//import net.engineeringdigest.journalApp.service.JournalEntryService;
//import net.engineeringdigest.journalApp.service.UserService;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/journalUser")
//public class journalEntryUser {
//    @Autowired
//    private JournalEntryService journalEntryService ;
//    @Autowired
//    private UserService userService ;
//
//    @GetMapping("all/{username}")
//    public ResponseEntity<?> getAll(@PathVariable String username ){
//        List<JournalEntry> all =  userService.getByUsername(username).get().getJournalEntries(); ;
//        if(all != null && !all.isEmpty()){
//            return new ResponseEntity<>(all,HttpStatus.OK) ;
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//    @PostMapping("username/{username}")
//    public ResponseEntity<JournalEntry> createEntry(@PathVariable String username, @RequestBody JournalEntry myEntry){
//        try {
//            myEntry.setDate(LocalDateTime.now());
//            journalEntryService.saveEntry(myEntry);
//            Optional<User> user = userService.getByUsername(username);
//            if(user.isPresent()){
//                user.journal
//            }
//            else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
//        }
//    }
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
//}
//
