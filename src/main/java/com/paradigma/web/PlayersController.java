package com.paradigma.web;

import javax.management.InstanceNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.paradigma.model.PlayerModel;
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
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Player> createPlayer(
			@RequestHeader("Authorization") String token,
			@RequestBody(required=true) @Valid PlayerCreation playerRequest) {

		log.info("POST -> /players {}", playerRequest);
		
		PlayerModel playerModel = transformPlayer(playerRequest, token);
		service.createPlayer(playerModel);
		
		return new ResponseEntity<Player>(transformPlayerResult(playerModel), HttpStatus.CREATED);
	}
	
	
	/**
	 * This method gets a player by its id
	 * @return The Player with the provided id
	 * @throws InstanceNotFoundException 
	 */
	@ApiOperation(value = "Retrieves a player by its id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retrieves the player with the provided id", response = Player.class),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "Player not found"),
			@ApiResponse(code = 424, message = "Failed external dependecy"),
			@ApiResponse(code = 500, message = "Server error") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Player> retrievePlayerById(
			@RequestHeader("Authorization") String token,
			@PathVariable(required=true) String id) throws InstanceNotFoundException {

		log.info("GET -> /players/{id} {}", id);
		
		
		PlayerModel playerModel = service.getPlayerById(id);
		
		return ResponseEntity.ok(transformPlayerResult(playerModel));
	}

	
	//////////////////////////////
	// Private functions
	//////////////////////////////
	
	private Player transformPlayerResult(PlayerModel source) {
		Player result = new Player();
		BeanUtils.copyProperties(source, result);
		result.setCharacter(new Character());
		BeanUtils.copyProperties(source.getCharacter(), result.getCharacter());

		return result;
	}
	
	private PlayerModel transformPlayer(PlayerCreation source, String token) {
		PlayerModel target = new PlayerModel();
		target.setUserId(token); // In future releases we'll have to extract the user id (client id) from the JWT token
		target.setUserName(source.getUserName());
		target.setCharacter(new CharacterModel());
		BeanUtils.copyProperties(source.getCharacter(), target.getCharacter());

		return target;
	}

	
}
