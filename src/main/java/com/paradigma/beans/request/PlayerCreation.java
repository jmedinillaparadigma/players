package com.paradigma.beans.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Player creation request bean
 * @author jmedinilla
 */

@Data
@ApiModel(value = "Player", description = "Player description")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayerCreation {
	
	@ApiModelProperty(value = "User name", example="john", required=true)
	@NotNull
	private String userName;
	
	@ApiModelProperty(value = "Character slected for the player", example="{Character}", required=true)
	@NotNull
	@Valid
	private CharacterCreation character;
}
