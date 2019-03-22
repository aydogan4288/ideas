package com.dojo.java_exam_2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.java_exam_2.models.Idea;



@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long>{
	public List<Idea> findAll();
	
	@Query(value="SELECT * FROM exam2 ORDER BY likes DESC;", nativeQuery=true)
	List<Idea> findAllDesc();
}