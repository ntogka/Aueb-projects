create table ratings(
   userId int,
   movieId int,
   rating varchar(10),
   timestamp int
);

create table credits(
   cast_on text,
   crew text,
   id int
);

create table keywords(
   id int,
   keywords text
);

create table links(
   movieId int,
   imdbId int,
   tmdbId int
);

create table "Movies_metadata"(
   adult varchar(10),
   belongs_to_collection varchar(190),
   budget int,
   genres varchar(270),
   homepage varchar(250),
   id int,
   imdb_id varchar(10),
   original_language varchar(10),
   original_title varchar(110),
   overview varchar(1000),
   popularity varchar(10),
   poster_path varchar(40),
   production_companies varchar(1260),
   production_countries varchar(1040),
   release_date date,
   revenue bigint,
   runtime varchar(10),
   spoken_languages varchar(770),
   status varchar(20),
   tagline varchar(300),
   title varchar(110),
   video varchar(10),
   vote_average varchar(10),
   vote_count int
);

-----------------------------------------------------------

ALTER TABLE "Movies_metadata" ADD COLUMN temp SERIAL;
DELETE FROM "Movies_metadata"
WHERE temp IN
(SELECT temp FROM
( SELECT temp ,
ROW_NUMBER() OVER( PARTITION BY id
ORDER BY id DESC) AS row_num
FROM "Movies_metadata") t
WHERE t.row_num >1); 

ALTER TABLE "links" ADD COLUMN temp SERIAL;
DELETE FROM "links"
WHERE temp IN
(SELECT temp FROM
( SELECT temp ,
ROW_NUMBER() OVER( PARTITION BY movieid
ORDER BY movieid DESC) AS row_num
FROM "links") t
WHERE t.row_num >1); 

ALTER TABLE "keywords" ADD COLUMN temp SERIAL;
DELETE FROM "keywords"
WHERE temp IN
(SELECT temp FROM
( SELECT temp ,
ROW_NUMBER() OVER( PARTITION BY id
ORDER BY id DESC) AS row_num
FROM "keywords") t
WHERE t.row_num >1); 

ALTER TABLE "credits" ADD COLUMN temp SERIAL;
DELETE FROM "credits"
WHERE temp IN
(SELECT temp FROM
( SELECT temp ,
ROW_NUMBER() OVER( PARTITION BY id
ORDER BY id DESC) AS row_num
FROM "credits") t
WHERE t.row_num >1); 

--------------------------------------------------

CREATE TABLE "Links" AS
(Select movieid, imdbid, tmdbid
FROM Movies_metadata JOIN links ON 
Movies_metadata.id = links.movieid);

CREATE TABLE "Keywords" AS
(Select keywords.id, keywords
FROM Movies_metadata JOIN keywords ON 
Movies_metadata.id = keywords.id);

CREATE TABLE "Ratings" AS
(Select userid, movieid, rating, "timestamp"
FROM Movies_metadata JOIN ratings ON 
Movies_metadata.id = ratings.movieid);

CREATE TABLE "Credits" AS
(Select cast_on, crew, credits.id
FROM Movies_metadata JOIN credits ON 
Movies_metadata.id = credits.id);

----------------------------------------------------------------

ALTER TABLE "Movies_metadata"
ADD PRIMARY KEY(id);

ALTER TABLE "Credits"
add foreign key(id) REFERENCES "Movies_metadata"(id),
ADD PRIMARY KEY(id);

ALTER TABLE "Keywords"
add foreign key(id) REFERENCES "Movies_metadata"(id),
ADD PRIMARY KEY(id);

ALTER TABLE "Links"
add foreign key(movieid) REFERENCES "Movies_metadata"(id),
ADD PRIMARY KEY(movieid);

ALTER TABLE "Ratings"
add foreign key(movieid) REFERENCES "Movies_metadata"(id);

------------------------------------------------------------------

DROP TABLE ratings;

DROP TABLE links;

DROP TABLE keywords;

DROP TABLE credits;
