package com.paradigma.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paradigma.model.PlayerModel;
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
	 * This method creates a new Player
	 * @param playerModel The input parameters to create the player
	 */
	@Override
	public void createPlayer(PlayerModel playerModel) {
		repository.createPlayer(playerModel);
	}
	
	
	

}
