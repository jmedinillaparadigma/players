package com.paradigma.repositories.mongodb;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.paradigma.model.PlayerModel;
import com.paradigma.repositories.PlayersRepository;
import com.paradigma.repositories.mongodb.model.CharacterMongo;
import com.paradigma.repositories.mongodb.model.PlayerMongo;

/**
 * Implementation of the Character Repository for MongoDB 
 * @author jmedinilla
 */
@Repository
public class PlayersRepositoryMongo implements PlayersRepository {

	@Autowired
	private MongoOperations mongoOperations;

	
	/**
	 * This method creates a new Player
	 * @param playerModel The input parameters to create the player
	 */
	public void createPlayer(PlayerModel playerModel){
		PlayerMongo pm = transformPlayer(playerModel);
		mongoOperations.insert(pm);
		playerModel.setId(pm.getId());
	}

	
	//////////////////////////////
	// Private functions
	//////////////////////////////

	private PlayerMongo transformPlayer(PlayerModel source) {
		PlayerMongo target = new PlayerMongo();
		BeanUtils.copyProperties(source, target);
		target.setCharacter(new CharacterMongo());
		BeanUtils.copyProperties(source.getCharacter(), target.getCharacter());
		return target;
	}

}
