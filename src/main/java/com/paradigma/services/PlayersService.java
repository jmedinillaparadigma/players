package com.paradigma.services;

import java.util.List;

import com.paradigma.model.CharacterModel;

/**
 * Interface for the character operations
 * @author jmedinilla
 */
public interface PlayersService {

	/**
	 * This method retrieves all the available characters stored in the persistence layer
	 * @return The list containing all the available characters
	 */
	public List<CharacterModel> list();
}
