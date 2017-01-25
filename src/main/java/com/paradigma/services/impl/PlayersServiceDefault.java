package com.paradigma.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paradigma.model.CharacterModel;
import com.paradigma.repositories.PlayersRepository;
import com.paradigma.services.PlayersService;

/**
 * Default implementation for the Character operations
 * @author jmedinilla
 */
@Service
public class PlayersServiceDefault implements PlayersService {

	@Autowired
	private PlayersRepository repository;
	
	
	/**
	 * This method retrieves all the available characters stored in the persistence layer
	 * @return The list containing all the available characters
	 */
	@Override
	public List<CharacterModel> list() {
		return repository.list();
	}

}
