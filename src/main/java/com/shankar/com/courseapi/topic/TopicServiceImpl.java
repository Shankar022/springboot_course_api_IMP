package com.shankar.com.courseapi.topic;

import com.shankar.com.courseapi.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public Topic getTopicById(String id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);

        // Check if the topic exists, and return it if found, or handle the case where it's not found
        if (optionalTopic.isPresent()) {
            return optionalTopic.get();
        } else {
            // Handle the case where the topic with the given ID does not exist
            throw new NotFoundException("Topic with ID " + id + " not found");
        }
    }

    @Override
    public void addTopic(Topic topic) {
        // You can add validation logic here if needed
        if (topic == null) {
            throw new IllegalArgumentException("Topic cannot be null");
        }

        try {
            topicRepository.save(topic);
        } catch (DataIntegrityViolationException e) {
            // Handle any database integrity violation exceptions (e.g., duplicate key, constraints)
            throw new IllegalArgumentException("Unable to add topic due to data integrity violation.", e);
        } catch (Exception e) {
            // Handle other exceptions that may occur during the save operation
            throw new RuntimeException("An error occurred while adding the topic.", e);
        }
    }

    @Override
    public void updateTopic(String id, Topic updatedTopic) {
        // Find the existing topic by ID
        Optional<Topic> optionalExistingTopic = topicRepository.findById(id);

        if (optionalExistingTopic.isPresent()) {
            Topic existingTopic = optionalExistingTopic.get();

            // Update the properties of the existing topic with the new values
            existingTopic.setName(updatedTopic.getName());
            existingTopic.setDescription(updatedTopic.getDescription());

            // Save the updated topic back to the database
            topicRepository.save(existingTopic);
        } else {
            // Handle the case where the topic with the given ID does not exist
            throw new NotFoundException("Topic with ID " + id + " not found");
        }
    }

    @Override
    public void deleteTopic(String id) {
        // Check if the topic with the given ID exists
        Optional<Topic> optionalTopic = topicRepository.findById(id);

        if (optionalTopic.isPresent()) {
            // Delete the topic from the database
            topicRepository.delete(optionalTopic.get());
        } else {
            // Handle the case where the topic with the given ID does not exist
            throw new NotFoundException("Topic with ID " + id + " not found");
        }
    }
}
