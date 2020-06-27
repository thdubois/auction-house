I decided to use spring boot for this challenge. Maven to manage dependencies. I use h2 for the database, an in-memory DB.

The project is divided in several parts:

	- controller: You will find all the endpoints here. I control here input data.
	
	- service: All the logic. Call by the controller or other services.
	
	- entity: POJO store in DB. Sometimes I return those POJO in response. I decided not to use DTO pattern for this project.
	
	- repository: Manage the access to the DB.
	
	- utils: some utility functions.
	
	- exception: Custom exception.
	
You will find the javadoc for controller in the repository "doc" at the root.

Moreover, there is one unit test, in "sr/test". I didn't have the time to implement integreationt test.

** Start the server **
In a terminal, go at the root of the project and run:

./mvnw spring-boot:run
It will start a Tomcat Server.

** Step 1 - Auction House

- Create a auction house with a given name:

POST http://localhost:8080/auction_house

JSON: 
{
	"name": "Auction house"
}

- List all auction houses created:

GET http://localhost:8080/auction_house

- Delete a specific house:

DELETE http://localhost:8080/auction_house/2

** Step 2 - Auction **

Create an auction with some parameters: name, description, starting time, end time, start
price, current price:

POST http://localhost:8080/auction_house/1/auction

JSON: 
{
	"name": "auction",
	"auctionStatus": "NOT_STARTED",
	"description": "The good one",
    	"startDate": "2020-10-1",
    	"endDate": "2021-12-30",
    	"startPrice": 100,
    	"currentPrice": 0
}

- List all auctions for a given auction house:

GET http://localhost:8080/auction_house/1/auction

- Delete a specific auction:

DELETE http://localhost:8080/auction/2

List all auctions based on their status

GET http://localhost:8080/auction/status/not_started

** Step 3 - Bidding in an auction

Let a specific user bid (if only the bidding price is higher that the auction current price and
the auction is started): 

First change the status of the auction to running

PUT http://localhost:8080/auction_house/1/auction/2/running

Then, the user bid:

POST http://localhost:8080/auction/2/bid

JSON:
{
	"userName": "Tom",
	"bid": "200"
}

- List all bidding (with the user name) that has happen until now:

GET http://localhost:8080/bid/tom

- For a terminated auction, show the winner (i.e. the bidder that puts the higher price)

First change the status of the auction to terminated

PUT http://localhost:8080/auction_house/1/auction/2/terminated

Then, show the winner

GET http://localhost:8080/auction/2/winner
