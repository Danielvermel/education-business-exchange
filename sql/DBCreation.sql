-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ebedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ebedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ebedb` DEFAULT CHARACTER SET latin1 ;
USE `ebedb` ;

-- -----------------------------------------------------
-- Table `ebedb`.`School`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`School` (
  `SchoolID` INT(11) NOT NULL AUTO_INCREMENT,
  `SchoolName` MEDIUMTEXT NULL DEFAULT NULL,
  `SchoolAddressCity` MEDIUMTEXT NULL DEFAULT NULL,
  `SchoolAddressStreet` MEDIUMTEXT NULL DEFAULT NULL,
  `SchoolAddressNumber` MEDIUMTEXT NULL DEFAULT NULL,
  `SchoolPostcode` MEDIUMTEXT NULL DEFAULT NULL,
  `SchoolEmail` MEDIUMTEXT NULL DEFAULT NULL,
  `SchoolPhone` MEDIUMTEXT NULL DEFAULT NULL,
  `SchoolNumberOfRequest` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`SchoolID`))
ENGINE = InnoDB
AUTO_INCREMENT = 301
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`Alumni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`Alumni` (
  `AlumniID` INT(11) NOT NULL AUTO_INCREMENT,
  `AlumniNameAndSurname` VARCHAR(255) NULL DEFAULT NULL,
  `AlumniSchoolID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`AlumniID`),
  INDEX `AlumniSchool_idx` (`AlumniSchoolID` ASC),
  CONSTRAINT `AlumniSchool`
    FOREIGN KEY (`AlumniSchoolID`)
    REFERENCES `ebedb`.`School` (`SchoolID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1001
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`ApplicationMethodList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`ApplicationMethodList` (
  `ApplicationMethodID` INT(11) NOT NULL AUTO_INCREMENT,
  `ApplicationMethodName` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`ApplicationMethodID`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`AreaOfCurriculumList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`AreaOfCurriculumList` (
  `AreaOfCurriculumID` INT(11) NOT NULL AUTO_INCREMENT,
  `AreaOfCurriculumName` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`AreaOfCurriculumID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`CooperationTypeList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`CooperationTypeList` (
  `CooperationTypeID` INT(11) NOT NULL AUTO_INCREMENT,
  `CooperationTypeName` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`CooperationTypeID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`EmployeesRangeList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`EmployeesRangeList` (
  `EmployeesRangeID` INT(11) NOT NULL AUTO_INCREMENT,
  `EmployeesRangeName` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`EmployeesRangeID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`StatusOfEmployerList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`StatusOfEmployerList` (
  `StatusOfEmployerID` INT(11) NOT NULL AUTO_INCREMENT,
  `StatusOfEmployerName` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`StatusOfEmployerID`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`Employer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`Employer` (
  `EmployerID` INT(11) NOT NULL AUTO_INCREMENT,
  `StatusOfEmployerID` INT(11) NULL DEFAULT NULL,
  `EmployerName` MEDIUMTEXT NULL DEFAULT NULL,
  `EmployerAddressCity` MEDIUMTEXT NULL DEFAULT NULL,
  `EmployerAddressStreet` MEDIUMTEXT NULL DEFAULT NULL,
  `EmployerAddressNumber` MEDIUMTEXT NULL DEFAULT NULL,
  `EmployerPostcode` MEDIUMTEXT NULL DEFAULT NULL,
  `EmployerEmail` LONGTEXT NULL DEFAULT NULL,
  `ContactPersonNameSurname` MEDIUMTEXT NULL DEFAULT NULL,
  `ContactPersonPosition` MEDIUMTEXT NULL DEFAULT NULL,
  `EmployerPhone` MEDIUMTEXT NULL DEFAULT NULL,
  `EmployerWebsite` MEDIUMTEXT NULL DEFAULT NULL,
  `EmployerTwitter` MEDIUMTEXT NULL DEFAULT NULL,
  `EmployerFB` MEDIUMTEXT NULL DEFAULT NULL,
  `NumberOfEmployeesID` INT(11) NULL DEFAULT NULL,
  `CompanySummary` MEDIUMTEXT NULL DEFAULT NULL,
  `Notes` MEDIUMTEXT NULL DEFAULT NULL,
  `LogoLink` MEDIUMTEXT NULL DEFAULT NULL,
  `GivesSiteExperience` TINYINT(4) NULL DEFAULT NULL,
  `GivesSiteVisits` TINYINT(4) NULL DEFAULT NULL,
  `GivesWorkshops` TINYINT(4) NULL DEFAULT NULL,
  `GivesPresentations` TINYINT(4) NULL DEFAULT NULL,
  `AttendsCareerFairs` TINYINT(4) NULL DEFAULT NULL,
  `GivesWebinars` TINYINT(4) NULL DEFAULT NULL,
  `WorksWithPrimaryPupils` TINYINT(4) NULL DEFAULT NULL,
  `UseOfModernForeignLanguage` TINYINT(4) NULL DEFAULT NULL,
  `RunsBusinessInWelsh` TINYINT(4) NULL DEFAULT NULL,
  `CanDeliverToSchoolsInWelsh` TINYINT(4) NULL DEFAULT NULL,
  `HasApprenticeshipProgramme` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`EmployerID`),
  INDEX `EmployerStatus_idx` (`StatusOfEmployerID` ASC),
  INDEX `EmployeesRange_idx` (`NumberOfEmployeesID` ASC),
  CONSTRAINT `EmployeesRange`
    FOREIGN KEY (`NumberOfEmployeesID`)
    REFERENCES `ebedb`.`EmployeesRangeList` (`EmployeesRangeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `EmployerStatus`
    FOREIGN KEY (`StatusOfEmployerID`)
    REFERENCES `ebedb`.`StatusOfEmployerList` (`StatusOfEmployerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1001
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`EmployerDocumentLinks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`EmployerDocumentLinks` (
  `DocumentLinkID` INT(11) NOT NULL AUTO_INCREMENT,
  `EmployerID` INT(11) NULL DEFAULT NULL,
  `Link` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`DocumentLinkID`),
  INDEX `EmployerDocumentLink_idx` (`EmployerID` ASC),
  CONSTRAINT `EmployerDocumentLink`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 201
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`TypeOfEventList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`TypeOfEventList` (
  `TypeOfEventID` INT(11) NOT NULL AUTO_INCREMENT,
  `TypeOfEventName` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`TypeOfEventID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`Event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`Event` (
  `EventID` INT(11) NOT NULL AUTO_INCREMENT,
  `EventName` MEDIUMTEXT NULL DEFAULT NULL,
  `TypeOfEventID` INT(11) NULL DEFAULT NULL,
  `EventDateAndTime` DATETIME NULL DEFAULT NULL,
  `EventVenueName` MEDIUMTEXT NULL DEFAULT NULL,
  `EventAddressCity` MEDIUMTEXT NULL DEFAULT NULL,
  `EventAddressStreet` MEDIUMTEXT NULL DEFAULT NULL,
  `EventAddressNumber` MEDIUMTEXT NULL DEFAULT NULL,
  `EventVenuePostcode` MEDIUMTEXT NULL DEFAULT NULL,
  `EventSummary` MEDIUMTEXT NULL DEFAULT NULL,
  `IsPublic` TINYINT(4) NULL DEFAULT NULL,
  `IsCancelled` TINYINT(4) NULL DEFAULT NULL,
  `NameOfAdviser` MEDIUMTEXT NULL DEFAULT NULL,
  `NumberOfAttendees` INT(11) NULL DEFAULT NULL,
  `PromotesApprenticeships` TINYINT(4) NULL DEFAULT NULL,
  `PromotesWelshLanguage` TINYINT(4) NULL DEFAULT NULL,
  `ChallengesGenderStereotypes` TINYINT(4) NULL DEFAULT NULL,
  `IsFeatured` TINYINT NULL,
  PRIMARY KEY (`EventID`),
  INDEX `EventTypeOfEvent_idx` (`TypeOfEventID` ASC),
  CONSTRAINT `EventTypeOfEvent`
    FOREIGN KEY (`TypeOfEventID`)
    REFERENCES `ebedb`.`TypeOfEventList` (`TypeOfEventID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 101
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`INT_AlumniWorkingForEmployer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`INT_AlumniWorkingForEmployer` (
  `EmployerID` INT(11) NOT NULL,
  `AlumniID` INT(11) NOT NULL,
  PRIMARY KEY (`EmployerID`, `AlumniID`),
  INDEX `AlumniEmployer_idx` (`AlumniID` ASC),
  CONSTRAINT `AlumniEmployer`
    FOREIGN KEY (`AlumniID`)
    REFERENCES `ebedb`.`Alumni` (`AlumniID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `EmployerAlumni`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`INT_AttendingEmployerOnEvent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`INT_AttendingEmployerOnEvent` (
  `EventID` INT(11) NOT NULL,
  `EmployerID` INT(11) NOT NULL,
  PRIMARY KEY (`EventID`, `EmployerID`),
  INDEX `EmployerEvent_idx` (`EmployerID` ASC),
  CONSTRAINT `EmployerEvent`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `EventEmployer`
    FOREIGN KEY (`EventID`)
    REFERENCES `ebedb`.`Event` (`EventID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`INT_AttendingSchoolOnEvent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`INT_AttendingSchoolOnEvent` (
  `EventID` INT(11) NOT NULL,
  `SchoolID` INT(11) NOT NULL,
  PRIMARY KEY (`EventID`, `SchoolID`),
  INDEX `SchoolEvent_idx` (`SchoolID` ASC),
  CONSTRAINT `EventSchool`
    FOREIGN KEY (`EventID`)
    REFERENCES `ebedb`.`Event` (`EventID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `SchoolEvent`
    FOREIGN KEY (`SchoolID`)
    REFERENCES `ebedb`.`School` (`SchoolID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`INT_EmployerCooperationType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`INT_EmployerCooperationType` (
  `EmployerID` INT(11) NOT NULL,
  `CooperationTypeID` INT(11) NOT NULL,
  PRIMARY KEY (`EmployerID`, `CooperationTypeID`),
  INDEX `CooperationEmployer_idx` (`CooperationTypeID` ASC),
  CONSTRAINT `CooperationEmployer`
    FOREIGN KEY (`CooperationTypeID`)
    REFERENCES `ebedb`.`CooperationTypeList` (`CooperationTypeID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `EmployerCooperation`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`IndustrySectorList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`IndustrySectorList` (
  `IndustrySectorID` INT(11) NOT NULL AUTO_INCREMENT,
  `IndustrySectorName` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`IndustrySectorID`))
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`INT_EmployerIndustrySector`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`INT_EmployerIndustrySector` (
  `EmployerID` INT(11) NOT NULL,
  `IndustrySectorID` INT(11) NOT NULL,
  PRIMARY KEY (`EmployerID`, `IndustrySectorID`),
  INDEX `IndustryEmployer_idx` (`IndustrySectorID` ASC),
  CONSTRAINT `EmployerIndustry`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `IndustryEmployer`
    FOREIGN KEY (`IndustrySectorID`)
    REFERENCES `ebedb`.`IndustrySectorList` (`IndustrySectorID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`PreferenceList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`PreferenceList` (
  `PreferenceID` INT(11) NOT NULL AUTO_INCREMENT,
  `PreferenceName` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`PreferenceID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`INT_EmployerPreference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`INT_EmployerPreference` (
  `EmployerID` INT(11) NOT NULL,
  `PreferenceID` INT(11) NOT NULL,
  PRIMARY KEY (`EmployerID`, `PreferenceID`),
  INDEX `PreferenceEmployer_idx` (`PreferenceID` ASC),
  CONSTRAINT `EmployerPreference`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `PreferenceEmployer`
    FOREIGN KEY (`PreferenceID`)
    REFERENCES `ebedb`.`PreferenceList` (`PreferenceID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`INT_EmployerSchoolPreference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`INT_EmployerSchoolPreference` (
  `EmployerID` INT(11) NOT NULL,
  `SchoolID` INT(11) NOT NULL,
  PRIMARY KEY (`EmployerID`, `SchoolID`),
  INDEX `SchoolEmployer_idx` (`SchoolID` ASC),
  CONSTRAINT `EmployerSchool`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `SchoolEmployer`
    FOREIGN KEY (`SchoolID`)
    REFERENCES `ebedb`.`School` (`SchoolID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`INT_EmployerSupportOfAreaOfCurriculum`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`INT_EmployerSupportOfAreaOfCurriculum` (
  `EmployerID` INT(11) NOT NULL,
  `AreaOfCurriculumID` INT(11) NOT NULL,
  PRIMARY KEY (`EmployerID`, `AreaOfCurriculumID`),
  INDEX `AreasOfCurriculumEmployer_idx` (`AreaOfCurriculumID` ASC),
  CONSTRAINT `AreasOfCurriculumEmployer`
    FOREIGN KEY (`AreaOfCurriculumID`)
    REFERENCES `ebedb`.`AreaOfCurriculumList` (`AreaOfCurriculumID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `EmployerAreasOfCurriculum`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`LanguageList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`LanguageList` (
  `LanguageID` INT(11) NOT NULL AUTO_INCREMENT,
  `LanguageName` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`LanguageID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`INT_LanguageUsedByEmployer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`INT_LanguageUsedByEmployer` (
  `EmployerID` INT(11) NOT NULL,
  `LanguageID` INT(11) NOT NULL,
  PRIMARY KEY (`EmployerID`, `LanguageID`),
  INDEX `LanguageEmployer_idx` (`LanguageID` ASC),
  CONSTRAINT `EmployerLanguage`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `LanguageEmployer`
    FOREIGN KEY (`LanguageID`)
    REFERENCES `ebedb`.`LanguageList` (`LanguageID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`LocalAuthorityList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`LocalAuthorityList` (
  `LocalAuthorityID` INT(11) NOT NULL AUTO_INCREMENT,
  `LocalAuthorityName` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`LocalAuthorityID`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`INT_LocalAuthorityEmployerCanWorkWith`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`INT_LocalAuthorityEmployerCanWorkWith` (
  `EmployerID` INT(11) NOT NULL,
  `LocalAuthorityID` INT(11) NOT NULL,
  PRIMARY KEY (`EmployerID`, `LocalAuthorityID`),
  INDEX `LocalAuthorityEmployer_idx` (`LocalAuthorityID` ASC),
  CONSTRAINT `EmployerLocalAuthority`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `LocalAuthorityEmployer`
    FOREIGN KEY (`LocalAuthorityID`)
    REFERENCES `ebedb`.`LocalAuthorityList` (`LocalAuthorityID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`OccupationalCodeList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`OccupationalCodeList` (
  `OccupationalCodeID` INT(11) NOT NULL AUTO_INCREMENT,
  `OccupationalCodeName` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`OccupationalCodeID`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`PostcodeList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`PostcodeList` (
  `PostcodeID` INT(11) NOT NULL AUTO_INCREMENT,
  `PostcodeName` MEDIUMTEXT NULL DEFAULT NULL,
  `LocalAuthorityID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`PostcodeID`),
  INDEX `LocalAuthorityPostCode_idx` (`LocalAuthorityID` ASC),
  CONSTRAINT `LocalAuthorityPostCode`
    FOREIGN KEY (`LocalAuthorityID`)
    REFERENCES `ebedb`.`LocalAuthorityList` (`LocalAuthorityID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 232
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`StatusOfVacancyList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`StatusOfVacancyList` (
  `StatusOfVacancyID` INT(11) NOT NULL AUTO_INCREMENT,
  `StatusOfVacancyName` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`StatusOfVacancyID`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`TypeOfVacancyList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`TypeOfVacancyList` (
  `TypeOfVacancyID` INT(11) NOT NULL AUTO_INCREMENT,
  `TypeOfVacancyName` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`TypeOfVacancyID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`Vacancy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`Vacancy` (
  `VacancyID` INT(11) NOT NULL AUTO_INCREMENT,
  `EmployerID` INT(11) NULL DEFAULT NULL,
  `VacancyName` MEDIUMTEXT NULL DEFAULT NULL,
  `VacancySummary` MEDIUMTEXT NULL DEFAULT NULL,
  `VacancyLink` MEDIUMTEXT NULL DEFAULT NULL,
  `TypeOfVacancyID` INT(11) NULL DEFAULT NULL,
  `StatusOfVacancyID` INT(11) NULL DEFAULT NULL,
  `StartOfVacancy` DATETIME NULL DEFAULT NULL,
  `DeadlineForApplication` DATETIME NULL DEFAULT NULL,
  `OccupationalCodeID` INT(11) NULL DEFAULT NULL,
  `ApplicationMethodID` INT(11) NULL DEFAULT NULL,
  `VacancyPostcode` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`VacancyID`),
  INDEX `VacancyOccupatonalCode_idx` (`OccupationalCodeID` ASC),
  INDEX `VacanctyType_idx` (`TypeOfVacancyID` ASC),
  INDEX `VacancyStatus_idx` (`StatusOfVacancyID` ASC),
  INDEX `VacancyEmployer_idx` (`EmployerID` ASC),
  INDEX `VacancyApplicationMethod_idx` (`ApplicationMethodID` ASC),
  CONSTRAINT `VacancyApplicationMethod`
    FOREIGN KEY (`ApplicationMethodID`)
    REFERENCES `ebedb`.`ApplicationMethodList` (`ApplicationMethodID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `VacancyEmployer`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `VacancyOccupatonalCode`
    FOREIGN KEY (`OccupationalCodeID`)
    REFERENCES `ebedb`.`OccupationalCodeList` (`OccupationalCodeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `VacancyStatus`
    FOREIGN KEY (`StatusOfVacancyID`)
    REFERENCES `ebedb`.`StatusOfVacancyList` (`StatusOfVacancyID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `VacancyType`
    FOREIGN KEY (`TypeOfVacancyID`)
    REFERENCES `ebedb`.`TypeOfVacancyList` (`TypeOfVacancyID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 101
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `ebedb`.`CWSMemberList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`CWSMemberList` (
  `CWSMemberID` INT NOT NULL AUTO_INCREMENT,
  `CWSNameSurname` MEDIUMTEXT NULL,
  `CWSEmail` MEDIUMTEXT NULL,
  PRIMARY KEY (`CWSMemberID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebedb`.`EmployerVideoLinks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`EmployerVideoLinks` (
  `VideoLinkID` INT NOT NULL AUTO_INCREMENT,
  `EmployerID` INT NULL,
  `Link` MEDIUMTEXT NULL,
  PRIMARY KEY (`VideoLinkID`),
  INDEX `EmployerDocumentLink_idx` (`EmployerID` ASC),
  CONSTRAINT `EmployerVideoLink`
    FOREIGN KEY (`EmployerID`)
    REFERENCES `ebedb`.`Employer` (`EmployerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebedb`.`VacancyDocumentLinks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`VacancyDocumentLinks` (
  `DocumentLinkID` INT NOT NULL AUTO_INCREMENT,
  `VacancyID` INT NULL,
  `Link` MEDIUMTEXT NULL,
  PRIMARY KEY (`DocumentLinkID`),
  INDEX `VacancyDocumentLink_idx` (`VacancyID` ASC),
  CONSTRAINT `VacancyDocumentLink`
    FOREIGN KEY (`VacancyID`)
    REFERENCES `ebedb`.`Vacancy` (`VacancyID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ebedb`.`EventDocumentLink`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ebedb`.`EventDocumentLink` (
  `DocumentLinkID` INT NOT NULL AUTO_INCREMENT,
  `EventID` INT NULL,
  `Link` MEDIUMTEXT NULL,
  PRIMARY KEY (`DocumentLinkID`),
  INDEX `EventDocument_idx` (`EventID` ASC),
  CONSTRAINT `EventDocumentLink`
    FOREIGN KEY (`EventID`)
    REFERENCES `ebedb`.`Event` (`EventID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
