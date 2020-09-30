ALTER TABLE "Calendar"
    ADD CONSTRAINT listing_id FOREIGN KEY (listing_id)
    REFERENCES "Listings" (id);
	
ALTER TABLE "Listings"
    ADD CONSTRAINT neighbourhood_cleansed FOREIGN KEY (neighbourhood_cleansed)
    REFERENCES "Neighbourhoods" (neighbourhood) ;
	
	ALTER TABLE "Geolocation"
    ADD CONSTRAINT properties_neighbourhood FOREIGN KEY (properties_neighbourhood)
    REFERENCES "Neighbourhoods" (neighbourhood) ;

ALTER TABLE "Listings_Summary"
    ADD CONSTRAINT id FOREIGN KEY (id)
    REFERENCES "Listings" (id);
	
ALTER TABLE "Reviews"
    ADD CONSTRAINT listing_id FOREIGN KEY (listing_id)
    REFERENCES "Listings" (id);
	
ALTER TABLE "Reviews_Summary"
    ADD CONSTRAINT listing_id FOREIGN KEY (listing_id)
    REFERENCES "Listings" (id);
	