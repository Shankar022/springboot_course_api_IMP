package com.shankar.com.courseapi.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Topic> getAllTopics() {
        Iterable<Topic> topicIterable = topicRepository.findAll();
        List<Topic> topics = new ArrayList<>();

        // Convert the iterable to a List
        topicIterable.forEach(topics::add);
        return topics;
    }

    @Override
    public ResponseEntity<Topic> getTopicById(String id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);

        // Check if the topic exists, and return it if found, or handle the case where it's not found
        if (optionalTopic.isPresent()) {
            return ResponseEntity.ok(optionalTopic.get());
        } else {
            // Handle the case where the topic with the given ID does not exist
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> addTopic(Topic topic) {
        // You can add validation logic here if needed
        if (topic == null) {
            return ResponseEntity.badRequest().body("Topic cannot be null");
        }

        try {
            topicRepository.save(topic);
            return ResponseEntity.ok("Topic added successfully, ID : " + topic.getId());
        } catch (DataIntegrityViolationException e) {
            // Handle any database integrity violation exceptions (e.g., duplicate key, constraints)
            return ResponseEntity.badRequest().body("Unable to add topic due to data integrity violation.");
        } catch (Exception e) {
            // Handle other exceptions that may occur during the save operation
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the topic.");
        }
    }

    @Override
    public ResponseEntity<String> updateTopic(String id, Topic updatedTopic) {
        // Find the existing topic by ID
        Optional<Topic> optionalExistingTopic = topicRepository.findById(id);

        if (optionalExistingTopic.isPresent()) {
            Topic existingTopic = optionalExistingTopic.get();

            // Update the properties of the existing topic with the new values
            existingTopic.setName(updatedTopic.getName());
            existingTopic.setDescription(updatedTopic.getDescription());

            // Save the updated topic back to the database
            topicRepository.save(existingTopic);

            return ResponseEntity.ok("Topic ID: " + id + " updated successfully");
        } else {
            // Return a response indicating that the topic with the given ID was not found
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    public ResponseEntity<String> deleteTopic(String id) {
        // Check if the topic with the given ID exists
        Optional<Topic> optionalTopic = topicRepository.findById(id);

        if (optionalTopic.isPresent()) {
            // Delete the topic from the database
            topicRepository.delete(optionalTopic.get());

            return ResponseEntity.ok("Topic ID : " + id + " deleted successfully");
        } else {
            // Return a response indicating that the topic with the given ID was not found
            return ResponseEntity.notFound().build();
        }
    }

}
