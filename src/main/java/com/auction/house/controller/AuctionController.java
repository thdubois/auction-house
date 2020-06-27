package com.auction.house.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.auction.house.entity.AuctionEntity;
import com.auction.house.entity.AuctionHouseEntity;
import com.auction.house.entity.AuctionStatus;
import com.auction.house.service.AuctionHouseService;
import com.auction.house.service.AuctionService;
import com.auction.house.service.BidService;
import com.auction.house.utils.AuctionUtils;

@RestController
public class AuctionController {
		
	@Autowired
	AuctionHouseService auctionHouseService;
	
	@Autowired
	AuctionService auctionService;
	
	@Autowired
	BidService bidService;
	
	/**
	 * List all auctions for a given auction house
	 * @param auctionHouseId
	 * @return the list of all auctions for a given auction house
	 * @throws ResponseStatusException 404 if auction house doesn't exist
	 */
	@GetMapping("auction_house/{auctionHouseId}/auction")
	public List<AuctionEntity> allByAuctionHouse(@PathVariable Long auctionHouseId) {
		Optional<AuctionHouseEntity> auctionHouse = auctionHouseService.getAuctionHouse(auctionHouseId);
		if (auctionHouse.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction house " + auctionHouseId + " doesn\'t exist");
		}
		
		return auctionService.getAllAuctionByAuctionHouse(auctionHouse.get());
	}
	
	/**
	 * Create an auction
	 * @param auctionHouseId: the auction house id link to the auction
	 * @param auction
	 * @return auction created
	 * @throws ResponseStatusException 404 if auction house doesn't exist
	 */
	@PostMapping("auction_house/{auctionHouseId}/auction")
	AuctionEntity createAuction(@PathVariable Long auctionHouseId, @RequestBody AuctionEntity auction) {
		Optional<AuctionHouseEntity> auctionHouse = auctionHouseService.getAuctionHouse(auctionHouseId);
		if (auctionHouse.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction house " + auctionHouseId + " doesn\'t exist");
		}

		auction.setAuctionHouseEntity(auctionHouse.get());
		return auctionService.saveAuction(auction);
	}
	
	/**
	 * Change the status of a specific auction
	 * @param auctionHouseId
	 * @param auctionId
	 * @param status
	 * @return the modified auction
	 * @throws ResponseStatusException 404 if auctionHouseId or auctionId don't exist else 400 if the status doesn't exist
	 */
	@PutMapping("auction_house/{auctionHouseId}/auction/{auctionId}/{status}")
	AuctionEntity UpdateAuctionStatus(@PathVariable Long auctionHouseId, @PathVariable Long auctionId, @PathVariable AuctionStatus status) {
		Optional<AuctionHouseEntity> auctionHouse = auctionHouseService.getAuctionHouse(auctionHouseId);
		if (auctionHouse.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction house " + auctionHouseId + " doesn\'t exist");
		}
		
		Optional<AuctionEntity> auction = auctionService.getById(auctionId);
		if (auction.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction " + auctionId + " doesn\'t exist");
		}
		
		if (status == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The status " + status + " doesn\'t exist");
		}
	
		auction.get().setAuctionStatus(status);
		return auctionService.saveAuction(auction.get());	
	}
	
	/**
	 * Delete a specific auction
	 * @param auctionId
	 * @throws ResponseStatusException 400 if auction house doesn't exist
	 */
	@DeleteMapping("/auction/{auctionId}")
	void deleteAuctionHouse(@PathVariable Long auctionId) {
		Optional<AuctionEntity> auction = auctionService.getById(auctionId);
		if (auction.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction " + auctionId + " doesn\'t exist");
		}
		
		auctionService.deleteById(auctionId);
	}
	
	/**
	 * Return all auction bases on their status
	 * @param auctionStatus
	 * @return all auction bases on their status. Empty array if auctionStatus doesn't exist or no auction found.
	 */
	@GetMapping("/auction/status/{auctionStatus}")
	public List<AuctionEntity> allByStatus(@PathVariable AuctionStatus auctionStatus) {
		return auctionService.getAllByStatus(auctionStatus);
	}
	
	/**
	 * Get the name of the winner of a terminated auction
	 * @param auctionId
	 * @return the winner or empty if no winner (no bid)
	 * @throws ResponseStatusException 404 if auctionId doesn't exist or 400 if the auction is not terminated
	 */
	@GetMapping("/auction/{auctionId}/winner")
	public String getWinner(@PathVariable Long auctionId) {
		Optional<AuctionEntity> auction = auctionService.getById(auctionId);
		if(auction.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The auction " + auctionId + " doesn\'t exist");
		}
		
		if (!AuctionUtils.isAuctionTerminated(auction.get().getAuctionStatus())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This auction is not terminated.");
		}
		
		return this.bidService.getAuctionWinner(auctionId);
	}

}
