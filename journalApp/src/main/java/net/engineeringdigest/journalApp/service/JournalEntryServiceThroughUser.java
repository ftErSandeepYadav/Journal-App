package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServiceThroughUser {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService ;

    public void saveEntry(JournalEntry journalEntry, String userName){
        journalEntry.setDate(LocalDateTime.now());
        Optional<User> user = userService.getByUsername(userName) ;
        JournalEntry saved = journalEntryRepository.save(journalEntry) ;
        if(user.isPresent()){
            user.get().getJournalEntries().add(saved) ;
            userService.saveUser(user);
        }
    }

    public List<JournalEntry> getAll(){
//        System.out.println(journalEntryRepository.findAll().size());
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }



}
// controller -->> service ---> repository