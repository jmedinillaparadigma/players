package com.paradigma.services;

import com.paradigma.model.PlayerModel;

/**
 * Interface for the character operations
 * @author jmedinilla
 */
public interface PlayersService {

	/**
	 * This method creates a new Player
	 * @param playerModel The input parameters to create the player
	 */
	public void createPlayer(PlayerModel playerModel);
}
