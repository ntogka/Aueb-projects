/* Αριθμός ταινιών ανά χρόνο */
/* rows:136 */

Select count ("Movies_metadata".id),
	SPLIT_PART("Movies_metadata".release_date::VARCHAR,'-', 1) y
from "Movies_metadata"
group by y
order by y;


/* Αριθμος ταινιων ανα ειδος*/ 
/* rows:20 */

UPDATE "Movies_metadata"
SET genres=REPLACE(genres,E'\'', E'\"') ;
	/* αντικαθιστω τα μονά εισαγωγικά σε διπλά */

ALTER TABLE "Movies_metadata"
ALTER COLUMN genres TYPE json using genres::json;
   /* μετατρέπω το column genre σε json */
 
SELECT y.x->'name' "genres", COUNT(id) as number_of_movies 
FROM "Movies_metadata"
CROSS JOIN LATERAL (SELECT jsonb_array_elements("Movies_metadata".genres::jsonb) x) y
GROUP BY y.x;


/* Αριθμος ταινιων ανα ειδος και ανα χρονο*/ 
/* rows:2036 */
 
SELECT y.x->'name' "genres", COUNT(id) as number_of_movies, 
extract(year from release_date) as year
FROM "Movies_metadata"
CROSS JOIN LATERAL (SELECT jsonb_array_elements("Movies_metadata".genres::jsonb) x) y
GROUP BY y.x , year
ORDER BY year;

 
/* Μέση βαθμολογία (rating) ανά είδος (ταινίας)*/
/* rows:20 */

SELECT y.x->'name' "genres", AVG (rating::numeric) as average_rating 
FROM "Movies_metadata"
JOIN "Ratings" ON "Ratings".movieid= "Movies_metadata".id
CROSS JOIN LATERAL (SELECT jsonb_array_elements("Movies_metadata".genres::jsonb) x) y
GROUP BY y.x
ORDER BY average_rating;


/* Αριθμός από ratings ανά χρήστη*/
/* rows:265928 */

SELECT COUNT (rating) AS number_of_ratings, userid
FROM "Ratings"
GROUP BY userid 
ORDER BY userid 


/* Μέση βαθμολογία (rating) ανά χρήστη*/
/* rows:265928 */

SELECT AVG (rating::numeric) AS average_rating, userid
FROM "Ratings"
GROUP BY userid 
ORDER BY userid



CREATE VIEW view_table AS(
SELECT COUNT (rating) AS number_of_ratings, AVG(rating) as average_rating , userid
FROM "Ratings'
GROUP BY userid 
ORDER BY userid)
/* Δημιουργουμε ενα view με ονομα view table και βαζουμε μεσα  για κάθε χρήστη τον αριθμό των ratings
που έχει κάνει καθώς και τη μέση βαθμολογία που έχει βάλει*/
/* INSIGHT: Παρατηρουμε οτι υπαρχει μια αυξηση ή μείωση της μέσης βαθμολογίας ανάλογα με συνολικό αριθμό των ratings που έχει κάνει ο κάθε χρήστης. Ενδεχομένως
έτσι να μπορούμε σε κάποιο βαθμό να δούμε την αυστηρότητα κάποιου χρήστη.'Ομως αυτό δεν είναι απόλυτο γιατί μπορεί κάποια ταινία να έχει πολλές αλλά χαμηλόβαθμες κριτικές
ή και το ανάποδο */












