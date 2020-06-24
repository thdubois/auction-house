package com.auction.house.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auction.house.entity.AuctionEntity;
import com.auction.house.entity.AuctionHouseEntity;
import com.auction.house.entity.AuctionStatus;

public interface AuctionRepository extends JpaRepository<AuctionEntity, Long> {
	
	List<AuctionEntity> findAllByAuctionHouseEntity(AuctionHouseEntity auctionHouseId);
	
	List<AuctionEntity> findByAuctionStatus(AuctionStatus auctionStatus);
}