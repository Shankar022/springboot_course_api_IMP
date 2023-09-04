package com.shankar.com.courseapi.topic;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TopicService {

    List<Topic> getAllTopics();

    ResponseEntity<Topic> getTopicById(String id);

    ResponseEntity<String> addTopic(Topic topic);

    ResponseEntity<String> updateTopic(String id, Topic topic);

    ResponseEntity<String> deleteTopic(String id);
}
