package com.shankar.com.courseapi.topic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicService {
    private final List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic("java", "Java Programming", "Java Programming description"),
            new Topic("spring", "Spring Boot", "Spring Boot description"),
            new Topic("javascript", "Javascript Programming", "Javascript Programming description")
    ));

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopicById(String id) {
        return topics.stream().filter(topic -> topic.getId().equals(id)).findFirst().orElse(null);
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void updateTopic(String id, Topic topic) {
        for (Topic t : topics) {
            if (t.getId().equals(id)) {
                topics.set(topics.indexOf(t), topic);
                return;
            }
        }
    }

    public void deleteTopic(String id) {
        topics.removeIf(topic -> topic.getId().equals(id));
    }
}
