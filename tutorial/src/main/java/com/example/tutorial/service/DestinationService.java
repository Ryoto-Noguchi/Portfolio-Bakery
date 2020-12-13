package com.example.tutorial.service;

import java.util.List;

import com.example.tutorial.model.dao.DestinationRepository;
import com.example.tutorial.model.entity.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinationService {

  @Autowired
  private DestinationRepository destinationRepos;

	public List<Destination> findDestination(int userId) {
    return destinationRepos.findByUserIdAndDeleteFlagFalse(userId);

	}

}
