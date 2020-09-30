/*Βρίσκει για το κάθε Listing πόσες ανέσεις έχει και της εμφανίζει 
και κατατάσει απο το Listing με τα περισσότερα amenities 
προς αυτά με τα λιγότερα και έχουν πάνω από 20 ανέσεις,
συνδυάζοντας τα id σε Listing και Room_amenity_connection

Rows: 7590 */

SELECT count(amenity_id), listing_id  FROM "Room_amenity_connection"
INNER JOIN "Listing" ON "Listing".id = "Room_amenity_connection".listing_id
GROUP BY listing_id
HAVING count (amenity_id) > 20
ORDER BY count(amenity_id) DESC;

----------------------------------------------------------------------------------

/*Δείχνει όσους host έχουν φωτογραφία προφίλ, τα host_id και τα ονόματα
τους συνδυάζοντας τον πινακα Listing_summary
ταξινομώντας τον με το id του Host

Rows:11514 */

SELECT "Host".id, "Listing_summary".host_id, "Host".name
FROM "Host"
INNER JOIN "Listing_summary" ON "Listing_summary".host_id = "Host".id
WHERE "Host".has_profile_pic = 'true'
ORDER BY("Host".id);

----------------------------------------------------------------------------------

/* Εμφανίζει τα listing_id που είναι και στο Room και στο Price
και τα βαζει ώστε να δούμε τις τιμές από το Price όπου επιτρέπει να
εχουν πάνω από 2 εξτρα άτομα 

Rows 6833 */
SELECT "Room".listing_id, "Price".price
FROM "Room"
  LEFT OUTER JOIN "Price"
ON "Room".listing_id = "Price".listing_id
WHERE "Price".extra_people > '2';

----------------------------------------------------------------------------------

/* Εμφανίζει τα listing_id που είναι στο Location και τις γειτονιες
που ταιριάζουν στο Location και στο Geolocation
όπου η τοποθεσία είναι ακριβής 

Rows 9834 */

SELECT "Location".listing_id, "Geolocation".properties_neighbourhood
FROM "Location"
  LEFT OUTER JOIN "Geolocation"
ON "Location".neighbourhood_cleansed = "Geolocation".properties_neighbourhood
WHERE "Location".is_location_exact = 'true';

----------------------------------------------------------------------------------

/*Βρίσκει για το Price το το μέσο όρο τιμής έχει
το εμφανίζει μαζί με το listing_id που έχει το Room και το 
Price και κατατάσει με το listing_id όπου επιτρέπει 
η τιμή να περιλαμβανει πάνω από 1 καλεσμένο

Rows 6837 */

SELECT "Price".listing_id, AVG ("Price".price) AS Average_price
FROM "Price"
INNER JOIN "Room" ON "Room".listing_id = "Price".listing_id
GROUP BY "Price".listing_id
HAVING AVG ("Price".guests_included) > 1
ORDER BY AVG ("Price".price) DESC;








