package com.dojo.java_exam_2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.dojo.java_exam_2.models.Idea;
import com.dojo.java_exam_2.repositories.IdeaRepository;

@Service
public class IdeaService {
	
	private final IdeaRepository ideaRepository;

	public IdeaService(IdeaRepository ideaRepository) {
		this.ideaRepository = ideaRepository;
	}

//	<<---------------Create--------------->>
	public Idea createIdea(Idea i) {
		return ideaRepository.save(i);
	}
	
//	<<---------------Read--------------->>
	public List<Idea> findAllIdeas() {
		return ideaRepository.findAll();
	}
	
	public Idea findIdeaById(Long id) {
		return ideaRepository.findById(id).get();
	}
	
//	<<---------------Update--------------->>

	
	public Idea updateIdea(Long id, String name, String description) {
    	Optional<Idea> optionalIdea = ideaRepository.findById(id); 
         if(optionalIdea.isPresent()) {
        	 Idea idea = optionalIdea.get();
        	 idea.setName(name);
        	 idea.setDescription(description);
             return ideaRepository.save(idea);
         } else {
             return null;
         }
    }
	
//	<<---------------Destroy--------------->>
	public void deleteIdea(Idea i) {
		ideaRepository.delete(i);
	}
}
