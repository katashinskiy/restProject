package com.restProject.controllers;


import com.restProject.exaption.NotFoundExaption;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("message")
public class ControllerMessage {

    private int count = 4;

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "First Message");
        }});

        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Second Message");
        }});

        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Third Message");
        }});
    }};


    @GetMapping
    public List<Map<String,String>> allMessages() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String,String> getMessage(@PathVariable String id){

        return getMessgeById(id);
    }

    private Map<String, String> getMessgeById(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundExaption::new);
    }

    @PostMapping
    public Map<String,String> crerate(@RequestBody Map<String,String> message){

        message.put("id", String.valueOf(count++));

        messages.add(message);

        return message;
    }

    @PutMapping("{id}")
    public Map<String,String> updata(@RequestBody Map<String, String> message,
                                     @PathVariable String id){
        Map<String,String> updateMessage = getMessgeById(id);
        updateMessage.putAll(message);
        updateMessage.put("id", id);

        return updateMessage;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Map<String,String> deleteMessage = getMessgeById(id);

        messages.remove(deleteMessage);
    }


}
