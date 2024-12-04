package com.zach.beltreview.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.zach.beltreview.models.Event;
import java.util.*;

@Repository
public interface EventRepo extends CrudRepository<Event, Long>{
	List<Event> findAll();
}
