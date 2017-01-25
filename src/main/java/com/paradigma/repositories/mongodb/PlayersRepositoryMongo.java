package com.paradigma.repositories.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.paradigma.model.CharacterModel;
import com.paradigma.repositories.PlayersRepository;
import com.paradigma.repositories.mongodb.model.CharacterMongo;

/**
 * Implementation of the Character Repository for MongoDB 
 * @author jmedinilla
 */
@Repository
public class PlayersRepositoryMongo implements PlayersRepository {

	@Autowired
	private MongoOperations mongoOperations;

	
	/**
	 * This method retrieves all the available characters stored in the persistence layer
	 * @return The list containing all the available characters
	 */
	@Override
	public List<CharacterModel> list() {
		List<CharacterMongo> mongoResult = mongoOperations.findAll(CharacterMongo.class);

		List<CharacterModel> result = new ArrayList<>();
		mongoResult.stream().forEach(c -> result.add(transformCharacter(c)));

		return result;
	}

	
	//////////////////////////////
	// Private functions
	//////////////////////////////

	private CharacterModel transformCharacter(CharacterMongo source) {
		CharacterModel target = new CharacterModel();
		BeanUtils.copyProperties(source, target);
		return target;
	}

}
