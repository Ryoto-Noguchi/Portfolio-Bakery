package com.example.tutorial.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.tutorial.model.dao.DestinationRepository;
import com.example.tutorial.model.entity.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class DestinationService {

  @Autowired
  private DestinationRepository destinationRepos;

	public List<Destination> findDestination(int userId) {
    return destinationRepos.findByUserIdAndDeleteFlagFalse(userId);

	}

	public int deleteDestination(int id) {
		return destinationRepos.logicalDeleteById(id);
	}

	public int insertDestination(Destination destination) {
		return destinationRepos.insert(destination);
	}

	public String findAddressByDestinationId(int destinationId) {
		return destinationRepos.findAddressById(destinationId);
	}

}
