package com.auction.house.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.auction.house.entity.AuctionHouseEntity;
import com.auction.house.repository.AuctionHouseRepository;

@Service
public class AuctionHouseService {
	
	private final AuctionHouseRepository repository;
	
	public AuctionHouseService(AuctionHouseRepository repository) {
		this.repository = repository;
	}


	public Optional<AuctionHouseEntity> getAuctionHouse(Long id) {
		return repository.findById(id);
	}
	
	
	public List<AuctionHouseEntity> getAllAuctionHouse() {
		return repository.findAll();
	}
	
	public AuctionHouseEntity createAuctionHouse(AuctionHouseEntity auctionHouse) {
		return repository.save(auctionHouse);
	}
	
	public void deleteAuctionHouse(Long id) {
		repository.deleteById(id);
	}
}
