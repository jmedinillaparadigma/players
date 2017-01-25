package com.paradigma.beans.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Character linked to the player creation request bean
 * @author jmedinilla
 */

@Data
@ApiModel(value = "Character", description = "Character desription")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CharacterCreation {
	
	@ApiModelProperty(value = "Character identifier", example="1", required=true)
	@NotNull
	private String id;
	
	@ApiModelProperty(value = "Character name", example="character1", required=true)
	@NotNull
	private String name;
	
	@ApiModelProperty(value = "Character icon path", example="/iconFile.png", required=true)
	@NotNull
	private String iconPath;
	
	@ApiModelProperty(value = "Aggresive available actions for the character", example="3", required=true)
	@NotNull
	private Integer aggresiveAvailableActions;
	
	@ApiModelProperty(value = "Defensive available actions for the character", example="3", required=true)
	@NotNull
	private Integer defensiveAvailableActions;
}
