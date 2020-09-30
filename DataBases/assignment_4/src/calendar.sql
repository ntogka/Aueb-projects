UPDATE "Calendar"
SET 
price = REPLACE(price,'$',''),
adjusted_price = REPLACE(adjusted_price,'$','');


UPDATE "Calendar"
SET 
price = REPLACE(price,',',''),
adjusted_price = REPLACE(adjusted_price,',','');


ALTER TABLE "Calendar"
alter column price TYPE DECIMAL(10,2) using price::DECIMAL,
alter column adjusted_price TYPE DECIMAL(10,2) using adjusted_price::DECIMAL;


ALTER TABLE "Calendar" 
ALTER available TYPE boolean
USING CASE available WHEN 'true' THEN TRUE 
					 WHEN 'false'  THEN FALSE 
END;