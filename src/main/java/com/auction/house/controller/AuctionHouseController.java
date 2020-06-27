package com.auction.house.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.auction.house.entity.AuctionHouseEntity;
import com.auction.house.service.AuctionHouseService;

@RestController
public class AuctionHouseController {
	
	@Autowired
	AuctionHouseService auctionHouseService;
	
	/**
	 * List all auction houses created
	 * @return The list of all auction houses created
	 */
	@GetMapping("/auction_house")
	public List<AuctionHouseEntity> all() {
		return auctionHouseService.getAllAuctionHouse();
	}
	
	/**
	 * Create an auction house
	 * @param auctionHouse
	 * @return The auction house created if input is ok
	 * @throws ResponseStatusException 400 if name is missing
	 */
	@PostMapping("/auction_house")
	AuctionHouseEntity createAuctionHouse(@RequestBody AuctionHouseEntity auctionHouse) {
		if (auctionHouse == null || StringUtils.isEmpty(auctionHouse.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is missing");
		}
		
		return auctionHouseService.createAuctionHouse(auctionHouse);
	}
	
	/**
	 * Delete an auction house
	 * @param id of the aucion house to delete
	 * @throws ResponseStatusException 404 if auction house id doesn't exist
	 */
	@DeleteMapping("/auction_house/{auctionHouseId}")
	void deleteAuctionHouse(@PathVariable Long auctionHouseId) {
		Optional<AuctionHouseEntity> auctionHouse = auctionHouseService.getAuctionHouse(auctionHouseId);
		if (auctionHouse.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction house " + auctionHouseId + " doesn\'t exist");
		}
		
		auctionHouseService.deleteAuctionHouse(auctionHouseId);
	}

}
