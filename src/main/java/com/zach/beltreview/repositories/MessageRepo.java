package com.zach.beltreview.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.zach.beltreview.models.Message;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long>{
	
}
