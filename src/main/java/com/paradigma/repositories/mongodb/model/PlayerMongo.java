package com.paradigma.repositories.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * This class represents a Player stored in mongodb
 * @author jmedinilla
 */

@Data
@ApiModel(value = "Player", description = "Player description")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection="players")
public class PlayerMongo {
	
	private String id;
	private String userId;
	private String userName;
	private CharacterMongo character;
}
