package com.auction.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.auction.house.entity.AuctionEntity;
import com.auction.house.entity.BidEntity;
import com.auction.house.exception.ClientRequestException;
import com.auction.house.repository.BidRepository;
import com.auction.house.utils.AuctionUtils;
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
		
		if (StringUtils.isEmpty(bid.getUserName())) {
			throw new ClientRequestException("User name must be specified.");
		}
		
		if(!BidUtils.isBidHigherThanStartPrice(bid.getBid(), auctionEntity.getStartPrice())) {
			throw new ClientRequestException("Bid must be higher than the start price.");
		}
		
		if(!BidUtils.isBidHigherThanCurrentPrice(bid.getBid(), auctionEntity.getCurrentPrice())) {
			throw new ClientRequestException("Bid must be higher than the current price.");
		}
		
		if (!AuctionUtils.isAuctionRunning(auctionEntity.getAuctionStatus())) {
			throw new ClientRequestException("Auction must be running");
		}
		
		auctionEntity.setCurrentPrice(bid.getBid());
		auctionService.saveAuction(auctionEntity);
		return this.repository.save(bid);
	}
	
	public List<BidEntity> getAllBidByUserName(String userName) {
		return this.repository.findAllByUserNameIgnoreCase(userName);
	}
	
	public String getAuctionWinner(Long auctionId) {
		return this.repository.findAuctionWinner(auctionId);
	}
	
}
