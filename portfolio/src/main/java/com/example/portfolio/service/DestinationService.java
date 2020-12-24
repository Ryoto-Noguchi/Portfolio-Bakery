package com.example.portfolio.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.portfolio.model.dao.DestinationRepository;
import com.example.portfolio.model.entity.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class DestinationService {

  @Autowired
  private DestinationRepository destinationRepos;

	/**
	 * ユーザIDを条件に宛先取得
	 * @param userId
	 * @return
	 */
	public List<Destination> findDestination(int userId) {
    return destinationRepos.findByUserIdAndDeleteFlagFalse(userId);

	}

	/**
	 * 宛先削除
	 * @param id
	 * @return
	 */
	public int deleteDestination(int id) {
		return destinationRepos.logicalDeleteById(id);
	}

	/**
	 * 宛先追加
	 * @param destination
	 * @return
	 */
	public int insertDestination(Destination destination) {
		return destinationRepos.insert(destination);
	}

	/**
	 * 宛先IDを条件に住所取得
	 * @param destinationId
	 * @return
	 */
	public String findAddressByDestinationId(int destinationId) {
		return destinationRepos.findAddressById(destinationId);
	}

}
