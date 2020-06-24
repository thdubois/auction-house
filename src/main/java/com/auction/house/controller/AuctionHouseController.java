package com.auction.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auction.house.entity.AuctionHouseEntity;
import com.auction.house.service.AuctionHouseService;

@RestController
public class AuctionHouseController {
	
	@Autowired
	AuctionHouseService auctionHouseService;
	
	@GetMapping("/auction_house")
	public List<AuctionHouseEntity> all() {
		return auctionHouseService.getAllAuctionHouse();
	}
	
	@PostMapping("/auction_house")
	AuctionHouseEntity createAuctionHouse(@RequestBody AuctionHouseEntity auctionHouse) {
		return auctionHouseService.createAuctionHouse(auctionHouse);
	}
	
	@DeleteMapping("/auction_house/{id}")
	void deleteAuctionHouse(@PathVariable Long id) {
		auctionHouseService.deleteAuctionHouse(id);
	}

}
