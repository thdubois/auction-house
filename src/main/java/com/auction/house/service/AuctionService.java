package com.auction.house.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.auction.house.entity.AuctionEntity;
import com.auction.house.entity.AuctionHouseEntity;
import com.auction.house.entity.AuctionStatus;
import com.auction.house.repository.AuctionRepository;

@Service
public class AuctionService {
	
	private final AuctionRepository repository;
	
	public AuctionService(AuctionRepository repository) {
		this.repository = repository;
	}


	public List<AuctionEntity> getAllAuctionByAuctionHouse(AuctionHouseEntity auctionHouseId) {
		return repository.findAllByAuctionHouseEntity(auctionHouseId);
	}
	
	public AuctionEntity saveAuction(AuctionEntity auction) {
		return repository.save(auction);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public List<AuctionEntity> getAllByStatus(AuctionStatus auctionStatus) {
		return repository.findByAuctionStatus(auctionStatus);
	}
	
}
