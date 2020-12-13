package com.example.tutorial.model.dao;

import java.util.List;

import com.example.tutorial.model.entity.Destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer>  {

	List<Destination> findByUserIdAndDeleteFlagFalse(int userId);

}
