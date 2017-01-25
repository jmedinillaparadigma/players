package com.paradigma.repositories.mongodb;

import javax.management.InstanceNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.paradigma.model.CharacterModel;
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
		PlayerMongo pm = transformPlayerMongoToModel(playerModel);
		mongoOperations.insert(pm);
		playerModel.setId(pm.getId());
	}

	/**
	 * This method gets a player by its id
	 * @return The Player with the provided id
	 * @throws InstanceNotFoundException 
	 */	
	@Override
	public PlayerModel getPlayerById(String id) throws InstanceNotFoundException {
		PlayerMongo pm = mongoOperations.findById(id, PlayerMongo.class);
		if(pm==null){
			throw new InstanceNotFoundException("The player with the provided id does not exist in our systems");
		}
		return transformPlayerModelToMongo(pm);
	}
	
	
	
	
	
	//////////////////////////////
	// Private functions
	//////////////////////////////

	private PlayerMongo transformPlayerMongoToModel(PlayerModel source) {
		PlayerMongo target = new PlayerMongo();
		BeanUtils.copyProperties(source, target);
		target.setCharacter(new CharacterMongo());
		BeanUtils.copyProperties(source.getCharacter(), target.getCharacter());
		return target;
	}
	
	private PlayerModel transformPlayerModelToMongo(PlayerMongo source) {
		PlayerModel target = new PlayerModel();
		BeanUtils.copyProperties(source, target);
		target.setCharacter(new CharacterModel());
		BeanUtils.copyProperties(source.getCharacter(), target.getCharacter());
		return target;
	}



}
