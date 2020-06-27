package com.auction.house.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auction.house.entity.BidEntity;

public interface BidRepository extends JpaRepository<BidEntity, Long> {
	
	List<BidEntity> findAllByUserNameIgnoreCase(String userName);
	
	@Query("SELECT be.userName FROM BidEntity be WHERE bid = (SELECT MAX(be.bid) FROM BidEntity be WHERE auction_id = ?1)")
	String findAuctionWinner(Long auctionId);
	
}