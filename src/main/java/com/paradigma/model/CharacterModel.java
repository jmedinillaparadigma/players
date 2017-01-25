package com.paradigma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Character model bean
 * @author jmedinilla
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CharacterModel {
	
	private String id;
	private String name;
	private String iconPath;
	private Integer aggresiveAvailableActions;
	private Integer defensiveAvailableActions;
}
