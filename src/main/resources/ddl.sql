 CREATE TABLE USERS ( 
  	username VARCHAR(45) UNIQUE NOT NULL,
  	password VARCHAR(45) NOT NULL,
  	email VARCHAR(45) UNIQUE NOT NULL,
  	name VARCHAR(45) NOT NULL
  );
  commit;