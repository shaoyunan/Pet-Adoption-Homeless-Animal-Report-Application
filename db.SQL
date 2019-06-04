Create DATABASE petfinderdb;
USE petfinderdb;


-- Create tables section -------------------------------------------------

-- Table PetOwner

CREATE TABLE `PetOwner`
(
  `petownerid` Int NOT NULL AUTO_INCREMENT,
  `firstname` Varchar(50),
  `lastname` Varchar(50),
  `phonenumber` Varchar(20),
  `username` Varchar(50),
  `password` Varchar(50),
  `status` Int,
  `email` Varchar(50),
  PRIMARY KEY (`petownerid`)
)
;

-- Table Pet

CREATE TABLE `Pet`
(
  `petid` Int NOT NULL AUTO_INCREMENT,
  `petname` Varchar(50),
  `petage` Int,
  `animaltype` Varchar(50),
  `petownerid` Int,
  `gender` Varchar(50),
  `color` Varchar(20),
  `description` Varchar(500),
  `breed` Varchar(50),
  `location` Varchar(255),
  `listed` Bool,
  PRIMARY KEY (`petid`)
)
;

CREATE INDEX `IX_Relationship6` ON `Pet` (`petownerid`)
;

-- Table shelteranimal

CREATE TABLE `shelteranimal`
(
  `animalid` Int NOT NULL AUTO_INCREMENT,
  `name` Varchar(50),
  `age` Int,
  `type` Varchar(50),
  `gender` Varchar(50),
  `color` Varchar(20),
  `description` Varchar(500),
  `breed` Char(20),
  PRIMARY KEY (`animalid`)
)
;

-- Table ShelterEmployee

CREATE TABLE `ShelterEmployee`
(
  `employeeid` Int NOT NULL AUTO_INCREMENT,
  `firstname` Varchar(50),
  `lastname` Varchar(50),
  `username` Varchar(50),
  `password` Varchar(50),
  PRIMARY KEY (`employeeid`)
)
;

-- Table HomelessReport

CREATE TABLE `HomelessReport`
(
  `reportid` Int NOT NULL AUTO_INCREMENT,
  `contact` Varchar(50),
  `reportdate` Date,
  `status` INT,
  `priority` INT,
  `message` Varchar(255),
  `animaltype` Varchar(50),
  `healthcondition` Varchar(50),
  `location` Varchar(255),
  `aggressive` bool,
  `indanger` bool,
  PRIMARY KEY (`reportid`)
)
;

-- Table IndividualAdoptionRequest

CREATE TABLE `IndividualAdoptionRequest`
(
  `requestid` Int NOT NULL AUTO_INCREMENT,
  `requestby` Int,
  `petid` Int,
  `status` Varchar(50),
  `requestdate` Date,
  `message` Varchar(255),
  PRIMARY KEY (`requestid`)
)
;

CREATE INDEX `IX_Relationship2` ON `IndividualAdoptionRequest` (`requestby`)
;

CREATE INDEX `IX_Relationship1` ON `IndividualAdoptionRequest` (`petid`)
;

-- Table ReportFinished

CREATE TABLE `ReportFinished`
(
  `reportid` Int NOT NULL,
  `finishedby` Int,
  `note` Varchar(255),
  `finishdate` Date
)
;

CREATE INDEX `IX_Relationship33` ON `ReportFinished` (`reportid`)
;

CREATE INDEX `IX_Relationship34` ON `ReportFinished` (`finishedby`)
;

ALTER TABLE `ReportFinished` ADD PRIMARY KEY (`reportid`)
;

-- Create foreign keys (relationships) section ------------------------------------------------- 


ALTER TABLE `Pet` ADD CONSTRAINT `Owner_Pet_FK` FOREIGN KEY (`petownerid`) REFERENCES `PetOwner` (`petownerid`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `ReportFinished` ADD CONSTRAINT `Report_Finish_FK` FOREIGN KEY (`reportid`) REFERENCES `HomelessReport` (`reportid`) ON DELETE CASCADE ON UPDATE CASCADE
;


ALTER TABLE `ReportFinished` ADD CONSTRAINT `Staff_Finish_FK` FOREIGN KEY (`finishedby`) REFERENCES `ShelterEmployee` (`employeeid`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `IndividualAdoptionRequest` ADD CONSTRAINT `PetOwner_RequestBy_FK` FOREIGN KEY (`requestby`) REFERENCES `PetOwner` (`petownerid`) ON DELETE CASCADE ON UPDATE CASCADE
;


ALTER TABLE `IndividualAdoptionRequest` ADD CONSTRAINT `Pet_Request_FK` FOREIGN KEY (`petid`) REFERENCES `Pet` (`petid`) ON DELETE CASCADE ON UPDATE CASCADE
;


