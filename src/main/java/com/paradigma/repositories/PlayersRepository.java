package com.paradigma.repositories;

import com.paradigma.model.PlayerModel;

public interface PlayersRepository {

	/**
	 * This method creates a new Player
	 * @param playerModel The input parameters to create the player
	 */
	public void createPlayer(PlayerModel playerModel);
}
