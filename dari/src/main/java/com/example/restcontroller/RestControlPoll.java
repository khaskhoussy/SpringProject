package com.example.restcontroller;


import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Poll;
import com.example.service.PollService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/polls")
public class RestControlPoll {
	
	
	@Autowired
    private PollService pollService;

    @GetMapping()
    public List<Poll> list() {
        return pollService.getAll();
    }

    @GetMapping("/user/{userName}")
    //@Secured("ROLE_USER")
    public List<Poll> getUserPolls(@PathVariable String userName, Principal p) {
        
        if (userName.equals(p.getName())) {
            return pollService.getAllForUser(userName);
        } else {
            return pollService.getAllVisibleForUser(userName);
        }
    }

    @GetMapping("/{id}")
    public Poll get(@PathVariable String id) {
        return pollService.getPollById(Long.parseLong(id));
    }

    @PutMapping("/{id}")
    //@Secured("ROLE_USER")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Poll poll) {
        poll.setId(id);
        Poll updatePoll = pollService.updatePoll(poll);
        return ResponseEntity.ok(updatePoll);

    }

//    @PostMapping("/{userName}")
//    //@Secured("ROLE_USER")
//    //Principal p
//    public String post(@RequestBody Poll poll,@PathVariable("userName") String userName) {
//    	System.err.println(userName);
//     
//        //System.err.println(p.getName());
//        return  pollService.savePoll(poll,userName);
//    }

    //@Secured("ROLE_USER")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Principal p) {
        pollService.deletePollById(id);
        return ResponseEntity.status(204).build();

    }


//    @PostMapping("{id}/vote/{optionId}/{idUser}")
//    public String post(@PathVariable Long id, @PathVariable Long optionId, HttpServletRequest request ,@PathVariable int idUser) throws Exception {
//        
//        return pollService.vote(id, optionId, idUser);
//    }
}
