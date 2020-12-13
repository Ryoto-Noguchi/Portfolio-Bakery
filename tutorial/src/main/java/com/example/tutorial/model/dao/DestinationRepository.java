package com.example.tutorial.model.dao;

import java.util.List;

import com.example.tutorial.model.entity.Destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer>  {

	List<Destination> findByUserIdAndDeleteFlagFalse(int userId);

	@Modifying
	@Query(value = "UPDATE mst_destination SET delete_flag = TRUE WHERE id = :id", nativeQuery = true)
	int logicalDeleteById(int id);

}
