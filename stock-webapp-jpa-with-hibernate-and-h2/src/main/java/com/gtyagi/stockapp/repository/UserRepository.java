package com.gtyagi.stockapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gtyagi.stockapp.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
	User findByUserName(String userName);
	List<User> findAll();

}
