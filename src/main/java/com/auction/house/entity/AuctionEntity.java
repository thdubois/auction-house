package com.auction.house.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class AuctionEntity {

	private @Id @GeneratedValue Long id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private float startPrice;
	private float currentPrice;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@Enumerated(EnumType.STRING)
	private AuctionStatus auctionStatus;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "auction_house_id", nullable = false)
	private AuctionHouseEntity auctionHouseEntity;
	
	AuctionEntity() {
	}

	public AuctionEntity(
			Long id, 
			String name, 
			String description, 
			Date startDate, 
			Date endDate, 
			float startPrice,
			float currentPrice, 
			AuctionHouseEntity auctionHouseEntity,
			AuctionStatus auctionStatus
		) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startPrice = startPrice;
		this.currentPrice = currentPrice;
		this.auctionHouseEntity = auctionHouseEntity;
		this.auctionStatus = auctionStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(float startPrice) {
		this.startPrice = startPrice;
	}

	public float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public AuctionHouseEntity getAuctionHouseEntity() {
		return auctionHouseEntity;
	}

	public void setAuctionHouseEntity(AuctionHouseEntity auctionHouseEntity) {
		this.auctionHouseEntity = auctionHouseEntity;
	}

	public AuctionStatus getAuctionStatus() {
		return auctionStatus;
	}

	public void setAuctionStatus(AuctionStatus auctionStatus) {
		this.auctionStatus = auctionStatus;
	}
	
}
