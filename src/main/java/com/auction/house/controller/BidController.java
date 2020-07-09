package com.auction.house.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.auction.house.entity.AuctionEntity;
import com.auction.house.entity.BidEntity;
import com.auction.house.exception.ClientRequestException;
import com.auction.house.service.AuctionService;
import com.auction.house.service.BidService;

import javassist.NotFoundException;

@RestController
public class BidController {
	
	@Autowired
	private BidService bidService;
	
	@Autowired
	private AuctionService auctiondService;
	
	/**
	 * Let a specific user bid
	 * @param auctionId
	 * @param bid
	 * @return
	 */
	@PostMapping("/auction/{auctionId}/bid")
	public BidEntity createBid(@PathVariable Long auctionId, @RequestBody BidEntity bid) {
		Optional<AuctionEntity> auctionEntity = auctiondService.getById(auctionId);
		if (auctionEntity.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction " + auctionId + " doesn\'t exist");
		}
		
		bid.setAuctionEntity(auctionEntity.get());
		try {
			return bidService.saveBid(bid);
		} catch (NotFoundException | ClientRequestException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}			
	}
	
	/**
	 * List all bidding (with the user name) that has happen until now
	 * @param userName
	 * @return all bidding. Empty if no result or userName doesn't exist
	 */
	@GetMapping("/bid/{userName}")
	public List<BidEntity> getAllBidByUserName(@PathVariable String userName) {
		return bidService.getAllBidByUserName(userName);
	}
}
