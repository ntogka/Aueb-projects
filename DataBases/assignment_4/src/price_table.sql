CREATE TABLE "Price"
  AS (SELECT id as listing_id, price, weekly_price, monthly_price, security_deposit, cleaning_fee,
		guests_included, extra_people, minimum_nights, maximum_nights,
		minimum_minimum_nights, maximum_minimum_nights, minimum_maximum_nights,
		maximum_maximum_nights, minimum_nights_avg_ntm, maximum_nights_avg_ntm FROM "Listings");


UPDATE "Price"
SET 
monthly_price = REPLACE(monthly_price,',',''),
price = REPLACE(price,',',''),
security_deposit = REPLACE(security_deposit,',',''),
weekly_price = REPLACE(weekly_price,',','');


UPDATE "Price"
SET 
price = REPLACE(price,'$',''),
weekly_price = REPLACE(weekly_price,'$',''),
monthly_price = REPLACE(monthly_price,'$',''),
security_deposit = REPLACE(security_deposit,'$',''),
cleaning_fee = REPLACE(cleaning_fee,'$',''),
extra_people = REPLACE(extra_people,'$','');


ALTER TABLE "Price"
ADD CONSTRAINT listing_id FOREIGN KEY (listing_id)
REFERENCES "Listings" (id);


ALTER TABLE "Price" 
alter column price TYPE DECIMAL(10,2) using price::DECIMAL,
alter column weekly_price TYPE DECIMAL(10,2) using weekly_price::DECIMAL,
alter column monthly_price TYPE DECIMAL(10,2) using monthly_price::DECIMAL,
alter column security_deposit TYPE DECIMAL(10,2) using security_deposit::DECIMAL,
alter column cleaning_fee TYPE DECIMAL(10,2) using cleaning_fee::DECIMAL,
alter column guests_included TYPE DECIMAL(10,2) using guests_included::DECIMAL,
alter column extra_people TYPE DECIMAL(10,2) using extra_people::DECIMAL,
alter column minimum_nights TYPE DECIMAL(10,2) using minimum_nights::DECIMAL,
alter column maximum_nights TYPE DECIMAL(10,2) using maximum_nights::DECIMAL,
alter column minimum_minimum_nights TYPE DECIMAL(10,2) using minimum_minimum_nights::DECIMAL,
alter column maximum_minimum_nights TYPE DECIMAL(10,2) using maximum_minimum_nights::DECIMAL,
alter column minimum_maximum_nights TYPE DECIMAL(10,2) using minimum_maximum_nights::DECIMAL,
alter column maximum_maximum_nights TYPE DECIMAL(10,2) using maximum_maximum_nights::DECIMAL,
alter column minimum_nights_avg_ntm TYPE DECIMAL(10,2) using minimum_nights_avg_ntm::DECIMAL,
alter column maximum_nights_avg_ntm TYPE DECIMAL(10,2) using maximum_nights_avg_ntm::DECIMAL;


ALTER TABLE "Listings" 
  DROP COLUMN cleaning_fee,
  DROP COLUMN guests_included,
  DROP COLUMN extra_people,
  DROP COLUMN minimum_nights,
  DROP COLUMN maximum_nights,
  DROP COLUMN minimum_minimum_nights,
  DROP COLUMN maximum_minimum_nights,
  DROP COLUMN minimum_maximum_nights,
  DROP COLUMN maximum_maximum_nights,
  DROP COLUMN minimum_nights_avg_ntm,
  DROP COLUMN maximum_nights_avg_ntm;
  
/* Τα price, weekly_price, monthly_price, security_deposit έχουν ήδη διαγραφεί
από το προηγούμενο ερώτημα */