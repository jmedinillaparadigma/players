package com.paradigma.beans.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Character response bean
 * @author jmedinilla
 */

@Data
@ApiModel(value = "Character", description = "Character desription")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Character {
	
	@ApiModelProperty(value = "Character identifier", example="1", required=true)
	private String id;
	
	@ApiModelProperty(value = "Character name", example="character1", required=true)
	private String name;
	
	@ApiModelProperty(value = "Character icon path", example="/iconFile.png", required=true)
	private String iconPath;
	
	@ApiModelProperty(value = "Aggresive available actions for the character", example="3", required=true)
	private Integer aggresiveAvailableActions;
	
	@ApiModelProperty(value = "Defensive available actions for the character", example="3", required=true)
	private Integer defensiveAvailableActions;
}
