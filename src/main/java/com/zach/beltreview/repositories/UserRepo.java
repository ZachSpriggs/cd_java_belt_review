package com.zach.beltreview.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.zach.beltreview.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	User findByEmail(String email);
}
