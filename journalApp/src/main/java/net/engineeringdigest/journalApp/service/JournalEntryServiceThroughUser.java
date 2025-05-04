package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServiceThroughUser {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService ;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        journalEntry.setDate(LocalDateTime.now());
        Optional<User> user = userService.getByUsername(userName) ;
        JournalEntry saved = journalEntryRepository.save(journalEntry) ;
        if(user.isPresent()){
            user.get().getJournalEntries().add(saved) ;
            userService.saveUser(user);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry) ;
    }

    public List<JournalEntry> getAll(){
//        System.out.println(journalEntryRepository.findAll().size());
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        Optional<User> user = userService.getByUsername(userName) ;
        if(user.isPresent()){
            user.get().getJournalEntries().removeIf(x->x.getId().equals(id) );
            userService.saveUser(user);
        }
        journalEntryRepository.deleteById(id);
    }



}
// controller -->> service ---> repository