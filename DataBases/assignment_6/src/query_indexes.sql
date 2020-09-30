QUERY 7
CREATE INDEX price_and_avg_index ON "Price"(price)
DROP INDEX price_and_avg_index; 
-----------
QUERY 6
CREATE INDEX location_index ON "Location"(listing_id)
DROP INDEX location_index;
-----------
QUERY 5
CREATE INDEX room_index ON "Room"(listing_id)
DROP INDEX room_index;
------------
QUERY 4
CREATE INDEX Host_haspicture_index ON "Host"(id,name)
DROP INDEX Host_haspicture_index;
------------
QUERY 3
CREATE INDEX roomconnection_index  ON "Room_amenity_connection"(listing_id)
DROP INDEX roomconnection_index;
------------
QUERY 2
CREATE INDEX  price_n_guests_index  ON "Price"(guests_included)
DROP INDEX price_n_guests_index;
------------
QUERY 1
CREATE INDEX host_id_index ON  "Listing"(host_id)
DROP INDEX host_id_index;
