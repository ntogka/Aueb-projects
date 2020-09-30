CREATE FUNCTION public.add_sign_up()
   RETURNS trigger AS
    
BEGIN
 
   UPDATE "Host" 
   SET listings_count=listings_count + 1 
   WHERE id = NEW.host_id;
   RETURN NEW;
 
END;


CREATE FUNCTION public.delete_sign_up()
    RETURNS trigger AS

BEGIN

    UPDATE "Host"
   SET listings_count=listings_count - 1
   WHERE id = OLD.host_id;
   RETURN OLD;
   
 
END;



CREATE TRIGGER addsignup  AFTER INSERT ON "Listing"
FOR EACH ROW 
EXECUTE PROCEDURE add_sign_up();


CREATE TRIGGER deletesignup AFTER DELETE ON "Listing"
FOR EACH ROW 
EXECUTE PROCEDURE delete_sign_up();


-----------------------------------------------------------
/* OUR TRIGGER */

/* Κάθε φορά που ο Host κάνει κάποια αλλαγή (INSERT,DELETE) στο table Calendar 
,στο πεδίο last_scraped του Listing αλλάζει η ώρα οπου έγινε.Δηλαδή γίνεται update στο column
του last_scraped με την εκάστοτε ημερομηνία.
*/

BEGIN

   IF TG_OP = 'DELETE' OR TG_OP = 'INSERT' THEN
     UPDATE "Listing"
	 SET calendar_last_scraped = now()
	 WHERE id = NEW.listing_id;
	 RETURN NEW ;
   END IF;

END;
  



CREATE TRIGGER updatelisting AFTER DELETE OR INSERT
ON "Calendar"
FOR EACH ROW
EXECUTE PROCEDURE calendarupdate ();




