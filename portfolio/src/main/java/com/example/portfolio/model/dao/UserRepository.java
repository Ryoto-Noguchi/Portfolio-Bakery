package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {

	User findByUserNameAndPassword(String userName, String password);

	@Query(value = "SELECT count(user_name) FROM mst_user WHERE user_name = :newUserName", nativeQuery = true)
	int findByUserName(@Param("newUserName") String newUserName);

	@Modifying
	@Query(value = "INSERT INTO mst_user (user_name, family_name, first_name, family_name_kana, first_name_kana, mail_address, password, gender) VALUES (:#{#user.userName}, :#{#user.familyName}, :#{#user.firstName}, :#{#user.familyNameKana}, :#{#user.firstNameKana}, :#{#user.mailAddress}, :#{#user.password}, :#{#user.gender})", nativeQuery = true)
	int insert(User user);

}
