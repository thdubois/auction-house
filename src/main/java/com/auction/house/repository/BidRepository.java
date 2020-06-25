package com.auction.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auction.house.entity.BidEntity;

public interface BidRepository extends JpaRepository<BidEntity, Long> {
	
}