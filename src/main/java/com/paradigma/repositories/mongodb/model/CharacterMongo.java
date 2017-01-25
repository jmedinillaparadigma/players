package com.paradigma.repositories.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * This class represents a Character stored in mongodb
 * @author jmedinilla
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection="characters")
public class CharacterMongo {

	@Id
	private String id;
	private String name;
	private String iconPath;
	private Integer aggresiveAvailableActions;
	private Integer defensiveAvailableActions;
}