CREATE DATABASE Pokemon;

grant all privileges on Pokemon.* to monUser@localhost identified by 'monPassword';

use Pokemon;

CREATE TABLE OWNER 
   (	
	PRENOM VARCHAR(20),
	POKEMON VARCHAR(20),
	PRIMARY KEY (POKEMON)
   ) ;

insert into OWNER VALUES ("@CloudDeLuna", "@GwenGoupix");
insert into OWNER VALUES ("@cybsip", "@Smogogo13");
insert into OWNER VALUES ("@CloudDeLuna", "@Dracaufeu13");
INSERT INTO OWNER VALUES (NULL, "INCONNU");

/*insert into OWNER VALUES ("@cybsip", "@Dracaufeu13");*/

CREATE TABLE POKEMON
   (    NOM VARCHAR(20),
        RACE VARCHAR(20),
        PV INT,
        NIVEAU INT,
        XP INT,
        ATTS INT,
        DEFS INT,
        ATTSPE INT,
        DEFSPE INT,
        VIT INT,
        PRIMARY KEY (NOM),
        FOREIGN KEY (NOM) REFERENCES OWNER(POKEMON)
   );
 
INSERT INTO POKEMON VALUES ( "@GwenGoupix" , "Goupix" , 100 , 1 , 0 , 0 , 0 , 0 , 0 , 0 );
INSERT INTO POKEMON VALUES ( "@Smogogo13" , "Smogogo" , 100 , 1 , 0 , 0 , 0 , 0 , 0 , 0 );
INSERT INTO POKEMON VALUES ( "@Dracaufeu13" , "Dracaufeu" , 100 , 1 , 0 , 0 , 0 , 0 , 0 , 0 );
INSERT INTO POKEMON VALUES ( "INCONNU", NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


CREATE TABLE ATTACKS
   (
	POKEMON VARCHAR(20),
	ATTACK VARCHAR(20),
	NIVEAU VARCHAR(20),
	PUISSANCE VARCHAR(20),
	PRC VARCHAR(20),
	PP VARCHAR(20),
	PPMAX VARCHAR(20),
	PRIMARY KEY (POKEMON, ATTACK),FOREIGN KEY(POKEMON) REFERENCES OWNER(POKEMON)
   );

INSERT INTO ATTACKS VALUES ("@GwenGoupix", "Feu-Follet", "N.17", "-", "75", "15", "15");
INSERT INTO ATTACKS VALUES ("@GwenGoupix", "Flammeche", "Départ", "40", "100", "25", "25");
INSERT INTO ATTACKS VALUES ("@GwenGoupix", "Vive-Attaque", "N.13", "40", "100", "30", "30");
INSERT INTO ATTACKS VALUES ("@GwenGoupix", "Hurlement", "N.9", "-", "100", "20", "20");

CREATE TABLE COMBAT 
(
	NUM_CB INT,
	POKE_1 VARCHAR(20),
	OWNER_1 VARCHAR(20), 
	POKE_2 VARCHAR(20),
	OWNER_2 VARCHAR(20), 
	PRIMARY KEY(POKE_1,POKE_2),
	FOREIGN KEY (POKE_1) REFERENCES OWNER(POKEMON),
	FOREIGN KEY (POKE_2) REFERENCES OWNER(POKEMON)
);
insert into COMBAT values ( 0 , 'INCONNU' , NULL , 'INCONNU' , NULL );
insert into COMBAT values ( 1 , '@Smogogo13' , '@cybsip' , '@GwenGoupix' ,  '@CloudDeLuna' ) ;

