Create DATABASE finalproject;
USE finalproject;

/*
Created: 12/9/2017
Modified: 12/10/2017
Model: MySQL 5.7
Database: MySQL 5.7
*/


-- Create tables section -------------------------------------------------

-- Table PetOwner

CREATE TABLE `PetOwner`
(
  `PetOwnerID` Int NOT NULL AUTO_INCREMENT,
  `FirstName` Varchar(50) NOT NULL,
  `LastName` Char(50) NOT NULL,
  `PhoneNumber` Char(20) NOT NULL,
  PRIMARY KEY (`PetOwnerID`)
)
;

-- Table Pet

CREATE TABLE `Pet`
(
  `PetID` Int NOT NULL AUTO_INCREMENT,
  `PetName` Varchar(50) NOT NULL,
  `PetAge` Int,
  `AnimalTypeID` Int NOT NULL,
  `PetOwnerID` Int NOT NULL,
  `Gender` Enum('Male', 'Female'),
  `Color` Varchar(20),
  PRIMARY KEY (`PetID`)
)
;

CREATE INDEX `IX_Relationship6` ON `Pet` (`PetOwnerID`)
;

CREATE INDEX `IX_Relationship7` ON `Pet` (`AnimalTypeID`)
;

-- Table AnimalType

CREATE TABLE `AnimalType`
(
  `TypeID` Int NOT NULL AUTO_INCREMENT,
  `TypeName` Varchar(20) NOT NULL,
  PRIMARY KEY (`TypeID`)
)
;

-- Table Doctor

CREATE TABLE `Doctor`
(
  `EmployeeID` Int NOT NULL AUTO_INCREMENT,
  `FirstName` Varchar(50) NOT NULL,
  `LastName` Varchar(50) NOT NULL,
  `WorkPhone` Varchar(20) NOT NULL,
  `Email` Varchar(50) NOT NULL,
  `ClinicID` Int NOT NULL,
  PRIMARY KEY (`EmployeeID`)
)
;

CREATE INDEX `IX_Relationship9` ON `Doctor` (`ClinicID`)
;

-- Table OrganizationStaff

CREATE TABLE `OrganizationStaff`
(
  `EmployeeID` Int NOT NULL AUTO_INCREMENT,
  `FirstName` Varchar(50) NOT NULL,
  `LastName` Varchar(50) NOT NULL,
  `OrganizationID` Int NOT NULL,
  PRIMARY KEY (`EmployeeID`)
)
;

CREATE INDEX `IX_Relationship29` ON `OrganizationStaff` (`OrganizationID`)
;

-- Table AdoptionList

CREATE TABLE `AdoptionList`
(
  `ID` Int NOT NULL AUTO_INCREMENT,
  `AnimalType` Int NOT NULL,
  `AddDate` Date NOT NULL,
  `ReadyForAdoption` Bool NOT NULL,
  `Description` Varchar(20),
  PRIMARY KEY (`ID`)
)
;

CREATE INDEX `IX_Relationship30` ON `AdoptionList` (`AnimalType`)
;

-- Table HomelessReport

CREATE TABLE `HomelessReport`
(
  `ReportID` Int NOT NULL AUTO_INCREMENT,
  `ReportBy` Int NOT NULL,
  `ReportDate` Date NOT NULL,
  `Status` Enum('Pending', 'Processing', 'Finished') NOT NULL DEFAULT 'Pending',
  `Priority` Enum('High', 'Normal', 'Low') DEFAULT ‘Normal’,
  `Message` Varchar(255),
  `AnimalType` Int NOT NULL,
  `HealthCondition` Enum('Good', 'Fair', 'Critical', 'Unknown') NOT NULL,
  `Latitude` Float,
  `Longitude` Float,
  `Location` Varchar(255) NOT NULL,
  `SendTo` Int NOT NULL,
  `AreaCode` Int NOT NULL,
  PRIMARY KEY (`ReportID`)
)
;

CREATE INDEX `IX_Relationship27` ON `HomelessReport` (`ReportBy`)
;

CREATE INDEX `IX_Relationship28` ON `HomelessReport` (`SendTo`)
;

CREATE INDEX `IX_Relationship49` ON `HomelessReport` (`AreaCode`)
;

-- Table PetHealthEvaluationRequest

CREATE TABLE `PetHealthEvaluationRequest`
(
  `RequestID` Int NOT NULL AUTO_INCREMENT,
  `RequestBy` Int NOT NULL,
  `SnedTo` Int NOT NULL,
  `RequestDate` Date NOT NULL,
  `Message` Varchar(255),
  `SickDate` Date NOT NULL,
  `PetID` Int NOT NULL,
  `ClinicID` Int NOT NULL,
  `Status` Enum('Pending', 'Answered') NOT NULL DEFAULT 'Pending',
  PRIMARY KEY (`RequestID`)
)
;

CREATE INDEX `IX_Relationship10` ON `PetHealthEvaluationRequest` (`PetID`)
;

CREATE INDEX `IX_Relationship11` ON `PetHealthEvaluationRequest` (`RequestBy`)
;

CREATE INDEX `IX_Relationship13` ON `PetHealthEvaluationRequest` (`ClinicID`)
;

-- Table AdoptionRequest

CREATE TABLE `AdoptionRequest`
(
  `RequestID` Int NOT NULL AUTO_INCREMENT,
  `RequestBy` Int,
  `AnimalID` Int,
  `SendTo` Int NOT NULL,
  `Status` Enum('Pending', 'Approved', 'Rejected') NOT NULL DEFAULT 'Pending',
  `RequestDate` Date NOT NULL,
  PRIMARY KEY (`RequestID`)
)
;

CREATE INDEX `IX_Relationship35` ON `AdoptionRequest` (`RequestBy`)
;

CREATE INDEX `IX_Relationship36` ON `AdoptionRequest` (`SendTo`)
;

CREATE INDEX `IX_Relationship37` ON `AdoptionRequest` (`AnimalID`)
;

-- Table Clinic

CREATE TABLE `Clinic`
(
  `ClinicID` Int NOT NULL AUTO_INCREMENT,
  `Address1` Varchar(255) NOT NULL,
  `Address2` Varchar(255),
  `ContactNumber` Varchar(20) NOT NULL,
  `ClinicName` Varchar(50) NOT NULL,
  PRIMARY KEY (`ClinicID`)
)
;

-- Table AnimalHealthCareOrganization

CREATE TABLE `AnimalHealthCareOrganization`
(
  `ID` Int NOT NULL AUTO_INCREMENT,
  `OrganizationName` Varchar(50) NOT NULL,
  `Address1` Varchar(255) NOT NULL,
  `Address2` Varchar(255),
  `ContactPhone` Varchar(20) NOT NULL,
  `Email` Varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
)
;

-- Table ClinicAppointment

CREATE TABLE `ClinicAppointment`
(
  `AppointmentID` Int NOT NULL AUTO_INCREMENT,
  `RequestBy` Int NOT NULL,
  `SendTo` Int NOT NULL,
  `Status` Enum('Pending', 'Approved', 'Rejected') NOT NULL DEFAULT 'Pending',
  `RequestDate` Date NOT NULL,
  `Message` Varchar(255) NOT NULL,
  `DesireTime` Datetime NOT NULL,
  PRIMARY KEY (`AppointmentID`)
)
;

CREATE INDEX `IX_Relationship18` ON `ClinicAppointment` (`SendTo`)
;

CREATE INDEX `IX_Relationship19` ON `ClinicAppointment` (`RequestBy`)
;

-- Table PetHealthHistory

CREATE TABLE `PetHealthHistory`
(
  `RecordID` Int NOT NULL AUTO_INCREMENT,
  `BloodPressure` Double,
  `GlucoseeLevel` Int,
  `Pulse` Int NOT NULL,
  `RecordDate` Date NOT NULL,
  `PetID` Int NOT NULL,
  `Note` Varchar(255),
  PRIMARY KEY (`RecordID`)
)
;

CREATE INDEX `IX_Relationship8` ON `PetHealthHistory` (`PetID`)
;

-- Table AnsweredEvaluation

CREATE TABLE `AnsweredEvaluation`
(
  `RequestID` Int NOT NULL,
  `AnsweredBy` Int,
  `Message` Varchar(255),
  `AnsweredDate` Date NOT NULL
)
;

CREATE INDEX `IX_Relationship14` ON `AnsweredEvaluation` (`RequestID`)
;

CREATE INDEX `IX_Relationship15` ON `AnsweredEvaluation` (`AnsweredBy`)
;

ALTER TABLE `AnsweredEvaluation` ADD PRIMARY KEY (`RequestID`)
;

-- Table AppointmentApproved

CREATE TABLE `AppointmentApproved`
(
  `AppointmentID` Int NOT NULL,
  `AssignedDoctor` Int DEFAULT NULL,
  `Message` Varchar(255),
  `MeetingDate` Datetime,
  `ApprovedDate` Date NOT NULL
)
;

CREATE INDEX `IX_Relationship22` ON `AppointmentApproved` (`AssignedDoctor`)
;

ALTER TABLE `AppointmentApproved` ADD PRIMARY KEY (`AppointmentID`)
;

-- Table ReportProcessing

CREATE TABLE `ReportProcessing`
(
  `ReportID` Int NOT NULL,
  `ProcessedBy` Int DEFAULT NULL,
  `Note` Varchar(255),
  `ProcessDate` Date NOT NULL
)
;

CREATE INDEX `IX_Relationship31` ON `ReportProcessing` (`ReportID`)
;

CREATE INDEX `IX_Relationship32` ON `ReportProcessing` (`ProcessedBy`)
;

ALTER TABLE `ReportProcessing` ADD PRIMARY KEY (`ReportID`)
;

-- Table ReportFinished

CREATE TABLE `ReportFinished`
(
  `ReportID` Int NOT NULL,
  `FinishedBy` Int DEFAULT NULL,
  `Note` Varchar(255),
  `FinishDate` Date NOT NULL
)
;

CREATE INDEX `IX_Relationship33` ON `ReportFinished` (`ReportID`)
;

CREATE INDEX `IX_Relationship34` ON `ReportFinished` (`FinishedBy`)
;

ALTER TABLE `ReportFinished` ADD PRIMARY KEY (`ReportID`)
;

-- Table AdoptionApproved

CREATE TABLE `AdoptionApproved`
(
  `RequestID` Int NOT NULL,
  `ApprovedBy` Int DEFAULT NULL,
  `ApprovedDate` Date NOT NULL,
  `Message` Varchar(255)
)
;

CREATE INDEX `IX_Relationship38` ON `AdoptionApproved` (`RequestID`)
;

CREATE INDEX `IX_Relationship39` ON `AdoptionApproved` (`ApprovedBy`)
;

ALTER TABLE `AdoptionApproved` ADD PRIMARY KEY (`RequestID`)
;

-- Table AdoptionRejected

CREATE TABLE `AdoptionRejected`
(
  `RequestID` Int NOT NULL,
  `RejectedBy` Int DEFAULT NULL,
  `Reason` Varchar(255),
  `RejectDate` Date NOT NULL
)
;

CREATE INDEX `IX_Relationship40` ON `AdoptionRejected` (`RequestID`)
;

CREATE INDEX `IX_Relationship41` ON `AdoptionRejected` (`RejectedBy`)
;

ALTER TABLE `AdoptionRejected` ADD PRIMARY KEY (`RequestID`)
;

-- Table Area

CREATE TABLE `Area`
(
  `AreaCode` Int NOT NULL AUTO_INCREMENT,
  `AreaName` Varchar(50) NOT NULL,
  PRIMARY KEY (`AreaCode`)
)
;

-- Create foreign keys (relationships) section ------------------------------------------------- 


ALTER TABLE `Pet` ADD CONSTRAINT `Owner_Pet_FK` FOREIGN KEY (`PetOwnerID`) REFERENCES `PetOwner` (`PetOwnerID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `Pet` ADD CONSTRAINT `Type_Pet_FK` FOREIGN KEY (`AnimalTypeID`) REFERENCES `AnimalType` (`TypeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `PetHealthHistory` ADD CONSTRAINT `Pet_History_FK` FOREIGN KEY (`PetID`) REFERENCES `Pet` (`PetID`) ON DELETE SET NULL ON UPDATE CASCADE
;


ALTER TABLE `Doctor` ADD CONSTRAINT `Clinic_Doctor_FK` FOREIGN KEY (`ClinicID`) REFERENCES `Clinic` (`ClinicID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `PetHealthEvaluationRequest` ADD CONSTRAINT `Pet_Evaluation_FK` FOREIGN KEY (`PetID`) REFERENCES `Pet` (`PetID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `PetHealthEvaluationRequest` ADD CONSTRAINT `Owner_Evaluation_FK` FOREIGN KEY (`RequestBy`) REFERENCES `PetOwner` (`PetOwnerID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `PetHealthEvaluationRequest` ADD CONSTRAINT `Clinic_Evaluation_FK` FOREIGN KEY (`ClinicID`) REFERENCES `Clinic` (`ClinicID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AnsweredEvaluation` ADD CONSTRAINT `Evaluation_Answered_FK` FOREIGN KEY (`RequestID`) REFERENCES `PetHealthEvaluationRequest` (`RequestID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AnsweredEvaluation` ADD CONSTRAINT `Doctor_Answered_FK` FOREIGN KEY (`AnsweredBy`) REFERENCES `Doctor` (`EmployeeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `ClinicAppointment` ADD CONSTRAINT `Clinc_Appointment_FK` FOREIGN KEY (`SendTo`) REFERENCES `Clinic` (`ClinicID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `ClinicAppointment` ADD CONSTRAINT `Owner_Appointment_FK` FOREIGN KEY (`RequestBy`) REFERENCES `PetOwner` (`PetOwnerID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AppointmentApproved` ADD CONSTRAINT `Appointment_Approved_FK` FOREIGN KEY (`AppointmentID`) REFERENCES `ClinicAppointment` (`AppointmentID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AppointmentApproved` ADD CONSTRAINT `Doctor_Approved_FK` FOREIGN KEY (`AssignedDoctor`) REFERENCES `Doctor` (`EmployeeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `HomelessReport` ADD CONSTRAINT `Owner_Report_FK` FOREIGN KEY (`ReportBy`) REFERENCES `PetOwner` (`PetOwnerID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `HomelessReport` ADD CONSTRAINT `AHCO_Report_FK` FOREIGN KEY (`SendTo`) REFERENCES `AnimalHealthCareOrganization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `OrganizationStaff` ADD CONSTRAINT `AHCO_Staff_FK` FOREIGN KEY (`OrganizationID`) REFERENCES `AnimalHealthCareOrganization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AdoptionList` ADD CONSTRAINT `Animal_Adoption_FK` FOREIGN KEY (`AnimalType`) REFERENCES `AnimalType` (`TypeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `ReportProcessing` ADD CONSTRAINT `Report_Process_FK` FOREIGN KEY (`ReportID`) REFERENCES `HomelessReport` (`ReportID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `ReportProcessing` ADD CONSTRAINT `Staff_Process_FK` FOREIGN KEY (`ProcessedBy`) REFERENCES `OrganizationStaff` (`EmployeeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `ReportFinished` ADD CONSTRAINT `Report_Finish_FK` FOREIGN KEY (`ReportID`) REFERENCES `HomelessReport` (`ReportID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `ReportFinished` ADD CONSTRAINT `Staff_Finish_FK` FOREIGN KEY (`FinishedBy`) REFERENCES `OrganizationStaff` (`EmployeeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AdoptionRequest` ADD CONSTRAINT `Owner_ARequest_FK` FOREIGN KEY (`RequestBy`) REFERENCES `PetOwner` (`PetOwnerID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AdoptionRequest` ADD CONSTRAINT `AHCO_Request_FK` FOREIGN KEY (`SendTo`) REFERENCES `AnimalHealthCareOrganization` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AdoptionRequest` ADD CONSTRAINT `List_Request_FK` FOREIGN KEY (`AnimalID`) REFERENCES `AdoptionList` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AdoptionApproved` ADD CONSTRAINT `Request_Approved_FK` FOREIGN KEY (`RequestID`) REFERENCES `AdoptionRequest` (`RequestID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AdoptionApproved` ADD CONSTRAINT `Staff_Approved_FK` FOREIGN KEY (`ApprovedBy`) REFERENCES `OrganizationStaff` (`EmployeeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AdoptionRejected` ADD CONSTRAINT `Request_Rejected_FK` FOREIGN KEY (`RequestID`) REFERENCES `AdoptionRequest` (`RequestID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `AdoptionRejected` ADD CONSTRAINT `Staff_Rejected_FK` FOREIGN KEY (`RejectedBy`) REFERENCES `OrganizationStaff` (`EmployeeID`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `HomelessReport` ADD CONSTRAINT `Area_Report_FK` FOREIGN KEY (`AreaCode`) REFERENCES `Area` (`AreaCode`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


