-- -----------------------------------------------------------------------------
--             G�n�ration d'une base de donn�es pour
--                      Oracle Version 10g
--                        (20/2/2013 14:04:18)
-- -----------------------------------------------------------------------------
--      Nom de la base : MLR1
--      Projet : 
--      Auteur : salle D
--      Date de derni�re modification : 20/2/2013 14:03:45
-- -----------------------------------------------------------------------------

DROP TABLE CAT�GORIE CASCADE CONSTRAINTS;

DROP TABLE ASSOCI� CASCADE CONSTRAINTS;

DROP TABLE POSSED� CASCADE CONSTRAINTS;

DROP TABLE DATE1 CASCADE CONSTRAINTS;

DROP TABLE SITUATION CASCADE CONSTRAINTS;

DROP TABLE ENFANT CASCADE CONSTRAINTS;

DROP TABLE PRESTATION CASCADE CONSTRAINTS;

DROP TABLE CLIENT CASCADE CONSTRAINTS;

DROP TABLE HOTEL CASCADE CONSTRAINTS;

DROP TABLE CLASSE CASCADE CONSTRAINTS;

DROP TABLE OCCUPANT CASCADE CONSTRAINTS;

DROP TABLE RESERVATION CASCADE CONSTRAINTS;

DROP TABLE CHAMBRE CASCADE CONSTRAINTS;

-- DROP TABLE EQUIPEMENT CASCADE CONSTRAINTS;

DROP TABLE CONSOMMER CASCADE CONSTRAINTS;

DROP TABLE SE_SITUER CASCADE CONSTRAINTS;

DROP TABLE TARIF CASCADE CONSTRAINTS;

DROP TABLE EQUIPER CASCADE CONSTRAINTS;

DROP TABLE OFFRIR CASCADE CONSTRAINTS;

DROP TABLE CONCERNE CASCADE CONSTRAINTS;

DROP TABLE OCCUPER CASCADE CONSTRAINTS;

-- -----------------------------------------------------------------------------
--       CREATION DE LA BASE 
-- -----------------------------------------------------------------------------

CREATE DATABASE MLR1;

-- -----------------------------------------------------------------------------
--       TABLE : CAT�GORIE
-- -----------------------------------------------------------------------------

CREATE TABLE CAT�GORIE
   (    CODE NUMBER(4,0)  NOT NULL,   CONSTRAINT PK_CAT�GORIE PRIMARY KEY (CODE)   ) ;

-- -----------------------------------------------------------------------------
--       TABLE : ASSOCI�
-- -----------------------------------------------------------------------------

CREATE TABLE ASSOCI�
   (
    NUM_HOTEL NUMBER(4,0)  NOT NULL,
    NOM_PROPRIO VARCHAR2(255)  NULL,
    TAUX_CA NUMBER(4,2)  NULL
,   CONSTRAINT PK_ASSOCI� PRIMARY KEY (NUM_HOTEL)  
   ) ;

-- -----------------------------------------------------------------------------
--       TABLE : POSSED�
-- -----------------------------------------------------------------------------

CREATE TABLE POSSED�
   (
    NUM_HOTEL NUMBER(4,0)  NOT NULL,
    G�RANT VARCHAR2(255)  NULL,
    QUALIFICATION VARCHAR2(255)  NULL
,   CONSTRAINT PK_POSSED� PRIMARY KEY (NUM_HOTEL)  
   ) ;

-- -----------------------------------------------------------------------------
--       TABLE : DATE1
-- -----------------------------------------------------------------------------

CREATE TABLE DATE1
   (
    DATE_DEB DATE  NOT NULL
,   CONSTRAINT PK_DATE PRIMARY KEY (DATE_DEB)  
   ) ;

-- -----------------------------------------------------------------------------
--       TABLE : SITUATION
-- -----------------------------------------------------------------------------

CREATE TABLE SITUATION
   (
    VUE_OU_EXPO VARCHAR2(255)  NOT NULL
,   CONSTRAINT PK_SITUATION PRIMARY KEY (VUE_OU_EXPO)  
   ) ;

-- -----------------------------------------------------------------------------
--       TABLE : ENFANT
-- -----------------------------------------------------------------------------

CREATE TABLE ENFANT
   (
    NUM_CLT NUMBER(4,0)  NOT NULL,
    PR�NOM VARCHAR2(255)  NOT NULL,
    AGE NUMBER(4,0)  NULL
,   CONSTRAINT PK_ENFANT PRIMARY KEY (NUM_CLT, PR�NOM)  
   ) ;

-- -----------------------------------------------------------------------------
--       TABLE : PRESTATION
-- -----------------------------------------------------------------------------

CREATE TABLE PRESTATION
   (
    NOM_PREST VARCHAR2(255)  NOT NULL
,   CONSTRAINT PK_PRESTATION PRIMARY KEY (NOM_PREST)  
   ) ;

-- -----------------------------------------------------------------------------
--       TABLE : CLIENT
-- -----------------------------------------------------------------------------

CREATE TABLE CLIENT
   (
    NUM_CLT NUMBER(4,0)  NOT NULL,
    NOM_CLT VARCHAR2(255)  NOT NULL,
    T�L VARCHAR2(32)  NULL
,   CONSTRAINT PK_CLIENT PRIMARY KEY (NUM_CLT)  
   ) ;

-- -----------------------------------------------------------------------------
--       TABLE : HOTEL
-- -----------------------------------------------------------------------------

CREATE TABLE HOTEL
   (
    NUM_HOTEL NUMBER(4,0)  NOT NULL,
    NB_�TOILES NUMBER(1,0)  NOT NULL,
    NOM_HOTEL VARCHAR2(255)  NULL,
    NUM_RUE NUMBER(4,0)  NULL,
    RUE VARCHAR2(255)  NULL,
    VILLE VARCHAR2(255)  NULL,
    CODE_POSTAL NUMBER(5,0)  NULL,
    NUM_TEL_1 VARCHAR2(32)  NULL,
    NUM_TEL_2 VARCHAR2(32)  NULL,
    NUM_TEL_3 VARCHAR2(32)  NULL,
    NUM_TEL_4 VARCHAR2(32)  NULL
,   CONSTRAINT PK_HOTEL PRIMARY KEY (NUM_HOTEL)  
   ) ;

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE HOTEL
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_HOTEL_CLASSE
     ON HOTEL (NB_�TOILES ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : CLASSE
-- -----------------------------------------------------------------------------

CREATE TABLE CLASSE
   (
    NB_�TOILES NUMBER(1,0)  NOT NULL
,   CONSTRAINT PK_CLASSE PRIMARY KEY (NB_�TOILES)  
   ) ;

-- -----------------------------------------------------------------------------
--       TABLE : OCCUPANT
-- -----------------------------------------------------------------------------

CREATE TABLE OCCUPANT
   (
    NUM_CLT NUMBER(4,0)  NOT NULL,
    PR�NOM VARCHAR2(255)  NOT NULL
,   CONSTRAINT PK_OCCUPANT PRIMARY KEY (NUM_CLT, PR�NOM)  
   ) ;

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE OCCUPANT
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_OCCUPANT_CLIENT
     ON OCCUPANT (NUM_CLT ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : RESERVATION
-- -----------------------------------------------------------------------------

CREATE TABLE RESERVATION
   (
    NUM_RES NUMBER(4,0)  NOT NULL,
    NUM_CLT NUMBER(4,0)  NOT NULL,
    DATE_DEMANDE DATE  NULL,
    ARRHES_VERS�ES INTEGER  NULL,
    DATE_DEB_RES DATE  NULL,
    DATE_FIN_RES DATE  NULL
,   CONSTRAINT PK_RESERVATION PRIMARY KEY (NUM_RES)  
   ) ;

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE RESERVATION
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_RESERVATION_CLIENT
     ON RESERVATION (NUM_CLT ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : CHAMBRE
-- -----------------------------------------------------------------------------

CREATE TABLE CHAMBRE
   (
    NUM_HOTEL NUMBER(4,0)  NOT NULL,
    NUM_CHAMBRE NUMBER(4,0)  NOT NULL,
    CODE NUMBER(4,0)  NOT NULL,
    NB_OCCUPANT NUMBER(4,0)  NULL
,   CONSTRAINT PK_CHAMBRE PRIMARY KEY (NUM_HOTEL, NUM_CHAMBRE)  
   ) ;

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE CHAMBRE
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_CHAMBRE_HOTEL
     ON CHAMBRE (NUM_HOTEL ASC)
    ;

CREATE  INDEX I_FK_CHAMBRE_CAT�GORIE
     ON CHAMBRE (CODE ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : EQUIPEMENT
-- -----------------------------------------------------------------------------

-- CREATE TABLE EQUIPEMENT
--  (
--    NOM_EQUIPEMENT VARCHAR2(255)  NOT NULL
-- ,   CONSTRAINT PK_EQUIPEMENT PRIMARY KEY (NOM_EQUIPEMENT)  
--   ) ;

-- -----------------------------------------------------------------------------
--       TABLE : CONSOMMER
-- -----------------------------------------------------------------------------

CREATE TABLE CONSOMMER
   (
    DATE_DEB DATE  NOT NULL,
    NOM_PREST VARCHAR2(255)  NOT NULL,
    NUM_CLT NUMBER(4,0)  NOT NULL,
    PR�NOM VARCHAR2(255)  NOT NULL,
    QUANTIT� NUMBER(4,0)  NULL
,   CONSTRAINT PK_CONSOMMER PRIMARY KEY (DATE_DEB, NOM_PREST, NUM_CLT, PR�NOM)  
   ) ;

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE CONSOMMER
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_CONSOMMER_DATE
     ON CONSOMMER (DATE_DEB ASC)
    ;

CREATE  INDEX I_FK_CONSOMMER_PRESTATION
     ON CONSOMMER (NOM_PREST ASC)
    ;

CREATE  INDEX I_FK_CONSOMMER_OCCUPANT
     ON CONSOMMER (NUM_CLT ASC, PR�NOM ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : SE_SITUER
-- -----------------------------------------------------------------------------

CREATE TABLE SE_SITUER
   (
    NUM_HOTEL NUMBER(4,0)  NOT NULL,
    NUM_CHAMBRE NUMBER(4,0)  NOT NULL,
    VUE_OU_EXPO VARCHAR2(255)  NOT NULL
,   CONSTRAINT PK_SE_SITUER PRIMARY KEY (NUM_HOTEL, NUM_CHAMBRE, VUE_OU_EXPO)  
   ) ;

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE SE_SITUER
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_SE_SITUER_CHAMBRE
     ON SE_SITUER (NUM_HOTEL ASC, NUM_CHAMBRE ASC)
    ;

CREATE  INDEX I_FK_SE_SITUER_SITUATION
     ON SE_SITUER (VUE_OU_EXPO ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : TARIF
-- -----------------------------------------------------------------------------

CREATE TABLE TARIF
   (
    NB_�TOILES NUMBER(1,0)  NOT NULL,
    CODE NUMBER(4,0)  NOT NULL,
    PRIX_NUIT�E NUMBER(4,0)  NULL
,   CONSTRAINT PK_TARIF PRIMARY KEY (NB_�TOILES, CODE)  
   ) ;

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE TARIF
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_TARIF_CLASSE
     ON TARIF (NB_�TOILES ASC)
    ;

CREATE  INDEX I_FK_TARIF_CAT�GORIE
     ON TARIF (CODE ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : EQUIPER
-- -----------------------------------------------------------------------------

CREATE TABLE EQUIPER
   (
    NOM_EQUIPEMENT VARCHAR2(255)  NOT NULL,
    NUM_HOTEL NUMBER(4,0)  NOT NULL,
    NUM_CHAMBRE NUMBER(4,0)  NOT NULL,
    NOMBRE NUMBER(4,0)  NULL
,   CONSTRAINT PK_EQUIPER PRIMARY KEY (NOM_EQUIPEMENT, NUM_HOTEL, NUM_CHAMBRE)  
   ) ;

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE EQUIPER
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_EQUIPER_EQUIPEMENT
     ON EQUIPER (NOM_EQUIPEMENT ASC)
    ;

CREATE  INDEX I_FK_EQUIPER_CHAMBRE
     ON EQUIPER (NUM_HOTEL ASC, NUM_CHAMBRE ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : OFFRIR
-- -----------------------------------------------------------------------------

CREATE TABLE OFFRIR
   (
    NUM_HOTEL NUMBER(4,0)  NOT NULL,
    NOM_PREST VARCHAR2(255)  NOT NULL,
    PRIX_PREST NUMBER(4,1)  NULL
,   CONSTRAINT PK_OFFRIR PRIMARY KEY (NUM_HOTEL, NOM_PREST)  
   ) ;

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE OFFRIR
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_OFFRIR_HOTEL
     ON OFFRIR (NUM_HOTEL ASC)
    ;

CREATE  INDEX I_FK_OFFRIR_PRESTATION
     ON OFFRIR (NOM_PREST ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : CONCERNE
-- -----------------------------------------------------------------------------

CREATE TABLE CONCERNE
   (
    NUM_HOTEL NUMBER(4,0)  NOT NULL,
    NUM_CHAMBRE NUMBER(4,0)  NOT NULL,
    NUM_RES NUMBER(4,0)  NOT NULL,
    NB_PERS NUMBER(4,0)  NULL
,   CONSTRAINT PK_CONCERNE PRIMARY KEY (NUM_HOTEL, NUM_CHAMBRE, NUM_RES)  
   ) ;

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE CONCERNE
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_CONCERNE_CHAMBRE
     ON CONCERNE (NUM_HOTEL ASC, NUM_CHAMBRE ASC)
    ;

CREATE  INDEX I_FK_CONCERNE_RESERVATION
     ON CONCERNE (NUM_RES ASC)
    ;

-- -----------------------------------------------------------------------------
--       TABLE : OCCUPER
-- -----------------------------------------------------------------------------

CREATE TABLE OCCUPER
   (
    DATE_DEB DATE  NOT NULL,
    NUM_CLT NUMBER(4,0)  NOT NULL,
    PR�NOM VARCHAR2(255)  NOT NULL,
    NUM_HOTEL NUMBER(4,0)  NOT NULL,
    NUM_CHAMBRE NUMBER(4,0)  NOT NULL,
    DUREE NUMBER(4,0)  NULL
,   CONSTRAINT PK_OCCUPER PRIMARY KEY (DATE_DEB, NUM_CLT, PR�NOM, NUM_HOTEL, NUM_CHAMBRE)  
   ) ;

-- -----------------------------------------------------------------------------
--       INDEX DE LA TABLE OCCUPER
-- -----------------------------------------------------------------------------

CREATE  INDEX I_FK_OCCUPER_DATE
     ON OCCUPER (DATE_DEB ASC)
    ;

CREATE  INDEX I_FK_OCCUPER_OCCUPANT
     ON OCCUPER (NUM_CLT ASC, PR�NOM ASC)
    ;

CREATE  INDEX I_FK_OCCUPER_CHAMBRE
     ON OCCUPER (NUM_HOTEL ASC, NUM_CHAMBRE ASC)
    ;


-- -----------------------------------------------------------------------------
--       CREATION DES REFERENCES DE TABLE
-- -----------------------------------------------------------------------------


ALTER TABLE ASSOCI� ADD (
     CONSTRAINT FK_ASSOCI�_HOTEL
          FOREIGN KEY (NUM_HOTEL)
               REFERENCES HOTEL (NUM_HOTEL))   ;

ALTER TABLE POSSED� ADD (
     CONSTRAINT FK_POSSED�_HOTEL
          FOREIGN KEY (NUM_HOTEL)
               REFERENCES HOTEL (NUM_HOTEL))   ;

ALTER TABLE ENFANT ADD (
     CONSTRAINT FK_ENFANT_OCCUPANT
          FOREIGN KEY (NUM_CLT, PR�NOM)
               REFERENCES OCCUPANT (NUM_CLT, PR�NOM))   ;

ALTER TABLE HOTEL ADD (
     CONSTRAINT FK_HOTEL_CLASSE
          FOREIGN KEY (NB_�TOILES)
               REFERENCES CLASSE (NB_�TOILES))   ;

ALTER TABLE OCCUPANT ADD (
     CONSTRAINT FK_OCCUPANT_CLIENT
          FOREIGN KEY (NUM_CLT)
               REFERENCES CLIENT (NUM_CLT))   ;

ALTER TABLE RESERVATION ADD (
     CONSTRAINT FK_RESERVATION_CLIENT
          FOREIGN KEY (NUM_CLT)
               REFERENCES CLIENT (NUM_CLT))   ;

ALTER TABLE CHAMBRE ADD (
     CONSTRAINT FK_CHAMBRE_HOTEL
          FOREIGN KEY (NUM_HOTEL)
               REFERENCES HOTEL (NUM_HOTEL))   ;

ALTER TABLE CHAMBRE ADD (
     CONSTRAINT FK_CHAMBRE_CAT�GORIE
          FOREIGN KEY (CODE)
               REFERENCES CAT�GORIE (CODE))   ;

ALTER TABLE CONSOMMER ADD (
     CONSTRAINT FK_CONSOMMER_DATE
          FOREIGN KEY (DATE_DEB)
               REFERENCES DATE1 (DATE_DEB))   ;

ALTER TABLE CONSOMMER ADD (
     CONSTRAINT FK_CONSOMMER_PRESTATION
          FOREIGN KEY (NOM_PREST)
               REFERENCES PRESTATION (NOM_PREST))   ;

ALTER TABLE CONSOMMER ADD (
     CONSTRAINT FK_CONSOMMER_OCCUPANT
          FOREIGN KEY (NUM_CLT, PR�NOM)
               REFERENCES OCCUPANT (NUM_CLT, PR�NOM))   ;

ALTER TABLE SE_SITUER ADD (
     CONSTRAINT FK_SE_SITUER_CHAMBRE
          FOREIGN KEY (NUM_HOTEL, NUM_CHAMBRE)
               REFERENCES CHAMBRE (NUM_HOTEL, NUM_CHAMBRE))   ;

ALTER TABLE SE_SITUER ADD (
     CONSTRAINT FK_SE_SITUER_SITUATION
          FOREIGN KEY (VUE_OU_EXPO)
               REFERENCES SITUATION (VUE_OU_EXPO))   ;

ALTER TABLE TARIF ADD (
     CONSTRAINT FK_TARIF_CLASSE
          FOREIGN KEY (NB_�TOILES)
               REFERENCES CLASSE (NB_�TOILES))   ;

ALTER TABLE TARIF ADD (
     CONSTRAINT FK_TARIF_CAT�GORIE
          FOREIGN KEY (CODE)
               REFERENCES CAT�GORIE (CODE))   ;

-- ALTER TABLE EQUIPER ADD (
--     CONSTRAINT FK_EQUIPER_EQUIPEMENT
--          FOREIGN KEY (NOM_EQUIPEMENT)
--               REFERENCES EQUIPEMENT (NOM_EQUIPEMENT))   ;

ALTER TABLE EQUIPER ADD (
     CONSTRAINT FK_EQUIPER_CHAMBRE
          FOREIGN KEY (NUM_HOTEL, NUM_CHAMBRE)
               REFERENCES CHAMBRE (NUM_HOTEL, NUM_CHAMBRE))   ;

ALTER TABLE OFFRIR ADD (
     CONSTRAINT FK_OFFRIR_HOTEL
          FOREIGN KEY (NUM_HOTEL)
               REFERENCES HOTEL (NUM_HOTEL))   ;

ALTER TABLE OFFRIR ADD (
     CONSTRAINT FK_OFFRIR_PRESTATION
          FOREIGN KEY (NOM_PREST)
               REFERENCES PRESTATION (NOM_PREST))   ;

ALTER TABLE CONCERNE ADD (
     CONSTRAINT FK_CONCERNE_CHAMBRE
          FOREIGN KEY (NUM_HOTEL, NUM_CHAMBRE)
               REFERENCES CHAMBRE (NUM_HOTEL, NUM_CHAMBRE))   ;

ALTER TABLE CONCERNE ADD (
     CONSTRAINT FK_CONCERNE_RESERVATION
          FOREIGN KEY (NUM_RES)
               REFERENCES RESERVATION (NUM_RES))   ;

ALTER TABLE OCCUPER ADD (
     CONSTRAINT FK_OCCUPER_DATE
          FOREIGN KEY (DATE_DEB)
               REFERENCES DATE1 (DATE_DEB))   ;

ALTER TABLE OCCUPER ADD (
     CONSTRAINT FK_OCCUPER_OCCUPANT
          FOREIGN KEY (NUM_CLT, PR�NOM)
               REFERENCES OCCUPANT (NUM_CLT, PR�NOM))   ;

ALTER TABLE OCCUPER ADD (
     CONSTRAINT FK_OCCUPER_CHAMBRE
          FOREIGN KEY (NUM_HOTEL, NUM_CHAMBRE)
               REFERENCES CHAMBRE (NUM_HOTEL, NUM_CHAMBRE))   ;


-- -----------------------------------------------------------------------------
--                FIN DE GENERATION
-- -----------------------------------------------------------------------------
