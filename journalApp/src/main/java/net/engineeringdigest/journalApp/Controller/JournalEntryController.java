package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {
    public HashMap<ObjectId, JournalEntry> jouralEntries = new HashMap<>() ;

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(jouralEntries.values()) ;
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        jouralEntries.put(myEntry.getId(),myEntry) ;
        return true ;
    }
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return jouralEntries.get(myId) ;
    }
    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId){
        jouralEntries.remove(myId) ;
        return true ;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry ){
        return jouralEntries.put(id, myEntry) ;
    }
}
