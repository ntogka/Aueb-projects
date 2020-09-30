CREATE TABLE "Amenity" AS
(SELECT distinct unnest(amenities::text[]) as amenity_name from "Room" );
 
ALTER  TABLE "Amenity"
ADD COLUMN amenity_id SERIAL PRIMARY KEY;

CREATE TABLE "Room_Amenity_Connection" AS
(SELECT distinct temp.listing_id , amenity_id from "Amenity",
   (SELECT "Room".listing_id as listing_id , unnest(amenities::text[]) as amenity_name from "Room" ) AS temp
  where temp.amenity_name = amenity.amenity_name);


ALTER TABLE "Room_amenity_connection"
ADD PRIMARY KEY(listing_id, amenity_id);

ALTER TABLE "Room_amenity_connection"
add foreign key(amenity_id) REFERENCES "Amenity"(amenity_id),
add foreign key(listing_id) REFERENCES "Room"(listing_id)

ALTER TABLE "Room"
drop column amenities;