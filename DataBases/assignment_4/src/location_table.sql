CREATE TABLE "Location"
  AS (SELECT id as listing_id, street, neighbourhood, neighbourhood_cleansed, city, state,
		zipcode, market, smart_location, country_code, country, latitude, longitude,
		is_location_exact FROM "Listings");
	 

ALTER TABLE "Location"
ADD CONSTRAINT listing_id FOREIGN KEY (listing_id)
REFERENCES "Listings" (id);


ALTER TABLE "Listings"
DROP CONSTRAINT  neighbourhood_cleansed;


ALTER TABLE "Location"
    ADD CONSTRAINT neighbourhood_cleansed FOREIGN KEY (neighbourhood_cleansed)
    REFERENCES "Neighbourhoods" (neighbourhood);


ALTER TABLE "Listings" 
  DROP COLUMN street,
  DROP COLUMN neighbourhood,
  DROP COLUMN neighbourhood_cleansed,
  DROP COLUMN city,
  DROP COLUMN state,
  DROP COLUMN zipcode,
  DROP COLUMN market,
  DROP COLUMN smart_location,
  DROP COLUMN country_code,
  DROP COLUMN country,
  DROP COLUMN latitude,
  DROP COLUMN longitude,
  DROP COLUMN is_location_exact;