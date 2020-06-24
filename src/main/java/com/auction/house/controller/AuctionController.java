package com.auction.house.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.auction.house.entity.AuctionEntity;
import com.auction.house.entity.AuctionHouseEntity;
import com.auction.house.entity.AuctionStatus;
import com.auction.house.service.AuctionHouseService;
import com.auction.house.service.AuctionService;


@RestController
public class AuctionController {
		
	@Autowired
	AuctionHouseService auctionHouseService;
	
	@Autowired
	AuctionService auctionService;
	
	@GetMapping("auction_house/{auctionHouseId}/auction")
	public List<AuctionEntity> allByAuctionHouse(@PathVariable Long auctionHouseId) {
		Optional<AuctionHouseEntity> auctionHouse = auctionHouseService.getAuctionHouse(auctionHouseId);
		if (auctionHouse.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction house " + auctionHouseId + " doesn\'t exist");
		}
		
		return auctionService.getAllAuctionByAuctionHouse(auctionHouse.get());
	}
	
	@PostMapping("auction_house/{auctionHouseId}/auction")
	AuctionEntity createAuction(@PathVariable Long auctionHouseId, @RequestBody AuctionEntity auction) {
		Optional<AuctionHouseEntity> auctionHouse = auctionHouseService.getAuctionHouse(auctionHouseId);
		if (auctionHouse.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction house " + auctionHouseId + " doesn\'t exist");
		}

		auction.setAuctionHouseEntity(auctionHouse.get());
		return auctionService.saveAuction(auction);
	}
	
	@DeleteMapping("/auction/{id}")
	void deleteAuctionHouse(@PathVariable Long id) {
		auctionService.deleteById(id);
	}
	
	@GetMapping("/auction/status/{auctionStatus}")
	public List<AuctionEntity> allByStatus(@PathVariable("auctionStatus") AuctionStatus auctionStatus) {
		return auctionService.getAllByStatus(auctionStatus);
	}

}
