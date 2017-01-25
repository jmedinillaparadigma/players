package com.paradigma.web;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paradigma.beans.request.PlayerCreation;
import com.paradigma.beans.response.Character;
import com.paradigma.beans.response.Player;
import com.paradigma.model.CharacterModel;
import com.paradigma.services.PlayersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller to handle the http request to this service
 * @author jmedinilla
 */
@Api(value = "Players management service")
@RequestMapping("/players")
@RestController
@Slf4j
public class PlayersController {

	@Autowired
	private PlayersService service;
	
	
	/**
	 * This method creates a new Player
	 * @return The list containing all the available characters
	 */
	@ApiOperation(value = "Creates a new player")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Creates a new player successfuly", response = Player.class),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 424, message = "Failed external dependecy"),
			@ApiResponse(code = 500, message = "Server error") })
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Player> retrieveAvailableCharacters(
			@RequestHeader("Authorization") String token,
			@RequestBody(required=true) @Valid PlayerCreation playerRequest) {

		log.info("POST -> /players {}", playerRequest);
		//List<CharacterModel> serviceResult = service.list();
		
//		List<Character> result = new ArrayList<>();
//		serviceResult.stream().forEach(c -> result.add(transformCharacter(c)));
		
		return new ResponseEntity<Player>(new Player(), HttpStatus.CREATED);
	}

	//////////////////////////////
	// Private functions
	//////////////////////////////
	
	private Character transformCharacter(CharacterModel characterModel) {
		Character character = new Character();
		BeanUtils.copyProperties(characterModel, character);
		return character;
	}

	
}
