package com.shankar.com.courseapi.topic;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicController {

    @RequestMapping("/topics")
    public List<Topic> getAllTopics() {
        return Arrays.asList(
                new Topic("Java", "Java Programming", "Java Programming description"),
                new Topic("Spring", "Spring Boot", "Spring Boot description"),
                new Topic("Javascript", "Javascript Programming", "Javascript Programming description")
                );
    }
}
