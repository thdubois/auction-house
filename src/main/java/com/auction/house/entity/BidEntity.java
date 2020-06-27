package com.auction.house.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class BidEntity {
	private @Id @GeneratedValue Long id;
	private String userName;
	private float bid;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "auction_id", nullable = false)
	private AuctionEntity auctionEntity;
	
	public BidEntity() {}
	
	public BidEntity(Long id, String userName, float bid, AuctionEntity auctionEntity) {
		this.id = id;
		this.userName = userName;
		this.bid = bid;
		this.auctionEntity = auctionEntity;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public AuctionEntity getAuctionEntity() {
		return auctionEntity;
	}

	public void setAuctionEntity(AuctionEntity auctionEntity) {
		this.auctionEntity = auctionEntity;
	}
	
}
