CREATE TABLE "Room"
  AS (SELECT id as listing_id, accommodates, bathrooms, bedrooms, beds, bed_type,
		amenities, square_feet, price, weekly_price, monthly_price, security_deposit FROM "Listings");
	 
	 
ALTER TABLE "Room"
ADD CONSTRAINT listing_id FOREIGN KEY (listing_id)
REFERENCES "Listings" (id);


ALTER TABLE "Listings" 
  DROP COLUMN accommodates,
  DROP COLUMN bathrooms,
  DROP COLUMN bedrooms,
  DROP COLUMN beds,
  DROP COLUMN bed_type,
  DROP COLUMN amenities,
  DROP COLUMN square_feet,
  DROP COLUMN price,
  DROP COLUMN weekly_price,
  DROP COLUMN monthly_price,
  DROP COLUMN security_deposit;