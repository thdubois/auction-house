package com.auction.house.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.house.entity.AuctionEntity;
import com.auction.house.entity.BidEntity;
import com.auction.house.exception.ClientRequestException;
import com.auction.house.repository.BidRepository;
import com.auction.house.utils.BidUtils;

import javassist.NotFoundException;

@Service
public class BidService {
	
	@Autowired
	private AuctionService auctionService;
	
	private final BidRepository repository;
	
	
	public BidService(BidRepository repository) {
		this.repository = repository;
	}
	
	public BidEntity saveBid(BidEntity bid) throws NotFoundException, ClientRequestException {
		AuctionEntity auctionEntity = bid.getAuctionEntity();
		if (auctionEntity == null) {
			throw new NotFoundException("This auction doesn/'t exist: ");
		}
		
		if(!BidUtils.isBidHigherThanStartPrice(bid.getBid(), auctionEntity.getStartPrice())) {
			throw new ClientRequestException("Bid must be higher than the start price.");
		}
		
		if(!BidUtils.isBidHigherThanCurrentPrice(bid.getBid(), auctionEntity.getCurrentPrice())) {
			throw new ClientRequestException("Bid must be higher than the current price.");
		}
		
		if (!BidUtils.isAuctionRunning(auctionEntity.getAuctionStatus())) {
			throw new ClientRequestException("Auction must be running");
		}
		
		auctionEntity.setCurrentPrice(bid.getBid());
		auctionService.saveAuction(auctionEntity);
		return repository.save(bid);
	}
	
}
