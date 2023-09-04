package com.shankar.com.courseapi.topic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface TopicService {

    public List<Topic> getAllTopics();

    public Topic getTopicById(String id);

    public void addTopic(Topic topic);

    public void updateTopic(String id, Topic topic);

    public void deleteTopic(String id);
}
