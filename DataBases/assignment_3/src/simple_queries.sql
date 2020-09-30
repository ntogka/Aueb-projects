/*Βρίσκει από τον Calendar το id του σχολιαστή του οποίου η 
κριτική έγινε στις 8/1/2017 αλλά έμεινε και σε δωμάτιο τουλάχιστον 3 νύχτες!
Rows 4858 */

SELECT * FROM  "Calendar" 
Inner Join "Reviews"
on "Calendar".listing_id = "Reviews".listing_id 
where "Reviews".date = '8/1/2017' 
AND "Calendar".minimum_nights> 3; 
--------------------------------------------------------------------------------------------------------------


/*Βρίσκει όσες τοποθεσίες υποδοχής ειναι στην Αθήνα απο το Listings και όσους η τιμή είναι 
αναμεσα στα 100 εως 150 και τα εμφανίζει με φθινουσα σειρα ως προς το id του Listings_summary
Rows 205 */

SELECT * FROM  "Listings" 
Inner Join "Listings_summary"
on "Listings".id = "Listings_summary".id 
where "Listings".host_location = 'Athens, Attica, Greece' 
AND "Listings_summary".price BETWEEN 100 AND 150
ORDER BY "Listings".id DESC;
--------------------------------------------------------------------------------------------------------------


/* Εμφανίζει δεδομένα reviews και διεύθυνση URL εικόνας για το listing
Rows 100 (χωρις το limit είναι 414235) */

SELECT "Reviews".*, "Listings".picture_url
FROM "Reviews"
INNER JOIN "Listings" ON "Listings".id= "Reviews".listing_id
LIMIT 100;
--------------------------------------------------------------------------------------------------------------


/* Εμφανίζει τα id που είναι και στο Reviews (εδω το listing_id) και στο Listings
και τα βαζει ώστε να δούμε τα σχόλια από το Reviews και το description από το Listings 
περιλαμβάνει τη συμβολοσειρά close to metro δηλαδή να είναι κοντά στο μετρό
Rows 414235 */

SELECT "Reviews".listing_id, "Listings".description
FROM "Reviews"
  LEFT OUTER JOIN "Listings"
ON "Reviews".listing_id = "Listings".id
AND "Listings".description LIKE '%close to metro%';  
--------------------------------------------------------------------------------------------------------------
  

/* Εμφανίζει τις γειτονιές του Neighbourhoods και του Geolocation (εδω το properties_neighbourhood)
και τα βάζει ωστε να δούμε το geometry_type που έχουν στο Geolocation το καθε ένα από αυτές
και δεν εμφανίζει όσες έχουν Null στο geometry_coordinates_0_0_72_0 του Geolocation
Rows 10 */

SELECT "Neighbourhoods".neighbourhood, "Geolocation".geometry_type
FROM "Neighbourhoods"
  LEFT OUTER JOIN "Geolocation"
ON "Neighbourhoods".neighbourhood = "Geolocation".properties_neighbourhood
WHERE "Neighbourhoods".neighbourhood = "Geolocation".properties_neighbourhood AND
	NOT ("Geolocation".geometry_coordinates_0_0_72_0 is null);
--------------------------------------------------------------------------------------------------------------


/* Εμφανίζει την ελάχιστη τιμή από το Listings για όταν οι μέγιστες νύχτες είναι μικροτερες του 60
και οι ελαχιστες νυχτες είναι μεγαλυτερες του 1 ως μια νέα ιδιώτητα Min_price και ακριβώς το ίδιο
για την μέγιστη τιμή ως μια νέα ιδιώτητα Max_price
Rows 1 */

SELECT
	MIN ("Listings".price) AS "Min_price",
	MAX ("Listings".price) AS "Max_price"
FROM "Listings"
where maximum_nights < '60' and minimum_nights > '1';
--------------------------------------------------------------------------------------------------------------


/* Δείχνει για δυο συγκεκριμένα id του Listings τη στήλη των Reviews και το συνολικό 
αριθμό κριτικών για κάθε Listings (από το Listings)
Rows 48 */

SELECT "Reviews".*, "Listings".number_of_reviews 
FROM "Listings"
INNER JOIN "Reviews" ON "Listings".id = "Reviews".listing_id 
WHERE "Listings".id = 10993 OR "Listings".id = 85539 ;
--------------------------------------------------------------------------------------------------------------


/* Δείχνει το listing_id που συμφωνεί και με του Calendar και με του Reviews εξαλύφωντας τα διπλώτυπα και
όταν η ημερομηνία ειναι μετά τις 2020-05-18 εμφανίζει και τις ημερομηνίες τα εμφανίζει με φθινουσα σειρα 
ως προς το listing_id του Calendar καθώς και του βαλαμε όριο το 1000 στα αποτελέσματα
Rows 1000 */

SELECT DISTINCT "Calendar".listing_id, "Calendar".date
From "Calendar"
  JOIN "Reviews" ON "Calendar".listing_id = "Reviews".listing_id and
"Calendar".date > '2020-05-18'
Order by "Calendar".listing_id Desc
limit 1000;
--------------------------------------------------------------------------------------------------------------


/* Dείχνει ανάλογα το τυπου ακινητου κατα μεσο όρο ποσα κραβάτια έχει για τις γειτονιές που 
βρισκονται στη Πλακα και στη Κυψελη
Rows 19 */

Select property_type, 
	AVG(beds) AS Average_beds
FROM "Listings"
where neighbourhood='Plaka' OR neighbourhood='Kypseli'
GROUP BY property_type;
--------------------------------------------------------------------------------------------------------------


/* Εμφανίζει τους πελάτες οι οποίοι επέλεξαν να μείνουν σε ένα private room αλλα συγχρώνος και με την 
ιδιότητα του να φιλοξενεί πάνω από 5 άτομα καθώς και 2 υπνοδωμάτια,
όπως επίσης εμφανίζεται με τα prices κατα άυξουσα τιμή 
Rows 16 */

SELECT *
FROM "Listings" 
Full Join "Listings_summary" on "Listings".id="Listings_summary".id 
WHERE "Listings".accommodates > 5 AND "Listings_summary".room_type = 'Private room' AND "Listings".bedrooms > 2
AND "Listings".weekly_price is null
ORDER BY "Listings".price DESC;
--------------------------------------------------------------------------------------------------------------


/*Συνδυάζει τους πίνακες Calendar και Listings και τα ταξινομεί σε σχέση με την ημερομηνία, τα υπνοδωμάτια ενω
ταυτοχρονα οι καταχωρίσεις που έχουν μηδενικές τιμές στην τιμή, square_feet δεν λαμβάνονται υπόψη
Rows 35406 */

SELECT "Calendar".*, "Listings".bedrooms, "Listings".square_feet , "Listings".listing_url
FROM "Calendar"
LEFT OUTER JOIN "Listings" ON "Listings".id = "Calendar".listing_id
WHERE not ("Calendar".price is NULL) AND not ("Listings".square_feet is NULL) 
ORDER BY "Calendar".date asc, "Listings".bedrooms desc;
--------------------------------------------------------------------------------------------------------------


/*Για το έυρος ημερών 2020-03-18 εως 2020-05-16 βρισκει ποια κτηρια είναι διαθέσιμα απο το πικανα Calendar 
και οι μεγιστες νυχτες απο τον Listings να ειναι μικροτερες ή ίσες με 55
Rows 2374 */
SELECT "Listings".id, "Listings".listing_url, "Listings".price
FROM "Listings"
INNER JOIN (SELECT DISTINCT "Calendar".listing_id
	FROM "Calendar"
	WHERE "Calendar".available = 'true' AND "Calendar".date between '2020-03-18' AND '2020-05-16') AS NewTable 
ON "Listings".id = NewTable.listing_id
WHERE "Listings".maximum_nights <= 55;
--------------------------------------------------------------------------------------------------------------
