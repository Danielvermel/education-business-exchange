package ebe.DBClasses;

public class Employer {

    private int EmployerID;
    private int StatusOfEmployerID;
    private String StatusOfEmployerName;
    private String EmployerName;
    private String EmployerAddressCity;
    private String EmployerAddressStreet;
    private String EmployerAddressNumber;
    private String EmployerPostcode;
    private String EmployerEmail;
    private String ContactPersonNameSurname;
    private String ContactPersonPosition;
    private String EmployerPhone;
    private String EmployerWebsite;
    private String EmployerTwitter;
    private String EmployerFB;
    private int NumberOfEmployeesID;
    private String NumberOfEmployeesName;
    private String CompanySummary;
    private String Notes;
    private String LogoLink;

    //Extra
    private Boolean GivesSiteExperience;
    private Boolean GivesSiteVisits;
    private Boolean GivesWorkshops;
    private Boolean GivesPresentations;
    private Boolean AttendsCareerFairs;
    private Boolean GivesWebinars;
    private Boolean WorksWithPrimaryPupils;
    private Boolean UseOfModernForeignLanguage;
    private Boolean RunsBusinessInWelsh;
    private Boolean CanDeliverToSchoolsInWelsh;
    private Boolean HasApprenticeshipProgramme;

    private int EmployerLanguageID;
    private String EmployerLanguageName;
    private int EmployerLocalAuthorityID;
    private String EmployerLocalAuthorityName;
    private int EmployerAreaOfCurriculumID;
    private String EmployAreaOfCurriculumName;
    private int EmployerIndustrySectorID;
    private String EmployerIndustrySectorName;
    private int EmployerCooperationTypeID;
    private String EmployerCooperationTypeName;
    private int EmployerPreferencesID;
    private String EmployerPreferencesName;
    private int EmployerAlumniID;
    private String EmployerAlumniName;
    private int EmployerAlumniSchoolID;
    private String EmployerAlumniSchoolName;

    public Employer() {
    }

    public Employer(int employerID, int statusOfEmployerID, String employerName, String employerAddressCity, String employerAddressStreet, String employerAddressNumber, String employerPostcode, String employerEmail, String contactPersonNameSurname, String contactPersonPosition, String employerPhone, String employerWebsite, String employerTwitter, String employerFB, int numberOfEmployeesID, String companySummary, String notes, String logoLink) {
        EmployerID = employerID;
        StatusOfEmployerID = statusOfEmployerID;
        EmployerName = employerName;
        EmployerAddressCity = employerAddressCity;
        EmployerAddressStreet = employerAddressStreet;
        EmployerAddressNumber = employerAddressNumber;
        EmployerPostcode = employerPostcode;
        EmployerEmail = employerEmail;
        ContactPersonNameSurname = contactPersonNameSurname;
        ContactPersonPosition = contactPersonPosition;
        EmployerPhone = employerPhone;
        EmployerWebsite = employerWebsite;
        EmployerTwitter = employerTwitter;
        EmployerFB = employerFB;
        NumberOfEmployeesID = numberOfEmployeesID;
        CompanySummary = companySummary;
        Notes = notes;
        LogoLink = logoLink;
    }

    public Employer(int employerID, int statusOfEmployerID, String employerName, String employerAddressCity, String employerAddressStreet, String employerAddressNumber, String employerPostcode, String employerEmail, String contactPersonNameSurname, String contactPersonPosition, String employerPhone, String employerWebsite, String employerTwitter, String employerFB, int numberOfEmployeesID, String companySummary, String notes, String logoLink, Boolean givesSiteExperience, Boolean givesSiteVisits, Boolean givesWorkshops, Boolean givesPresentations, Boolean attendsCareerFairs, Boolean givesWebinars, Boolean worksWithPrimaryPupils, Boolean useOfModernForeignLanguage, Boolean runsBusinessInWelsh, Boolean canDeliverToSchoolsInWelsh, Boolean hasApprenticeshipProgramme) {
        EmployerID = employerID;
        StatusOfEmployerID = statusOfEmployerID;
        EmployerName = employerName;
        EmployerAddressCity = employerAddressCity;
        EmployerAddressStreet = employerAddressStreet;
        EmployerAddressNumber = employerAddressNumber;
        EmployerPostcode = employerPostcode;
        EmployerEmail = employerEmail;
        ContactPersonNameSurname = contactPersonNameSurname;
        ContactPersonPosition = contactPersonPosition;
        EmployerPhone = employerPhone;
        EmployerWebsite = employerWebsite;
        EmployerTwitter = employerTwitter;
        EmployerFB = employerFB;
        NumberOfEmployeesID = numberOfEmployeesID;
        CompanySummary = companySummary;
        Notes = notes;
        LogoLink = logoLink;
        GivesSiteExperience = givesSiteExperience;
        GivesSiteVisits = givesSiteVisits;
        GivesWorkshops = givesWorkshops;
        GivesPresentations = givesPresentations;
        AttendsCareerFairs = attendsCareerFairs;
        GivesWebinars = givesWebinars;
        WorksWithPrimaryPupils = worksWithPrimaryPupils;
        UseOfModernForeignLanguage = useOfModernForeignLanguage;
        RunsBusinessInWelsh = runsBusinessInWelsh;
        CanDeliverToSchoolsInWelsh = canDeliverToSchoolsInWelsh;
        HasApprenticeshipProgramme = hasApprenticeshipProgramme;
    }

    public Employer(int statusOfEmployerID, String employerName, String employerAddressCity, String employerAddressStreet, String employerAddressNumber, String employerPostcode, String employerEmail, String contactPersonNameSurname, String contactPersonPosition, String employerPhone, String employerWebsite, String employerTwitter, String employerFB, int numberOfEmployeesID, String companySummary, String notes, String logoLink, Boolean givesSiteExperience, Boolean givesSiteVisits, Boolean givesWorkshops, Boolean givesPresentations, Boolean attendsCareerFairs, Boolean givesWebinars, Boolean worksWithPrimaryPupils, Boolean useOfModernForeignLanguage, Boolean runsBusinessInWelsh, Boolean canDeliverToSchoolsInWelsh, Boolean hasApprenticeshipProgramme) {
        StatusOfEmployerID = statusOfEmployerID;
        EmployerName = employerName;
        EmployerAddressCity = employerAddressCity;
        EmployerAddressStreet = employerAddressStreet;
        EmployerAddressNumber = employerAddressNumber;
        EmployerPostcode = employerPostcode;
        EmployerEmail = employerEmail;
        ContactPersonNameSurname = contactPersonNameSurname;
        ContactPersonPosition = contactPersonPosition;
        EmployerPhone = employerPhone;
        EmployerWebsite = employerWebsite;
        EmployerTwitter = employerTwitter;
        EmployerFB = employerFB;
        NumberOfEmployeesID = numberOfEmployeesID;
        CompanySummary = companySummary;
        Notes = notes;
        LogoLink = logoLink;
        GivesSiteExperience = givesSiteExperience;
        GivesSiteVisits = givesSiteVisits;
        GivesWorkshops = givesWorkshops;
        GivesPresentations = givesPresentations;
        AttendsCareerFairs = attendsCareerFairs;
        GivesWebinars = givesWebinars;
        WorksWithPrimaryPupils = worksWithPrimaryPupils;
        UseOfModernForeignLanguage = useOfModernForeignLanguage;
        RunsBusinessInWelsh = runsBusinessInWelsh;
        CanDeliverToSchoolsInWelsh = canDeliverToSchoolsInWelsh;
        HasApprenticeshipProgramme = hasApprenticeshipProgramme;
    }

    public int getEmployerID() {
        return EmployerID;
    }

    public void setEmployerID(int employerID) {
        EmployerID = employerID;
    }

    public int getStatusOfEmployerID() {
        return StatusOfEmployerID;
    }

    public void setStatusOfEmployerID(int statusOfEmployerID) {
        StatusOfEmployerID = statusOfEmployerID;
    }

    public String getStatusOfEmployerName() {
        return StatusOfEmployerName;
    }

    public void setStatusOfEmployerName(String statusOfEmployerName) {
        StatusOfEmployerName = statusOfEmployerName;
    }

    public String getEmployerName() {
        return EmployerName;
    }

    public void setEmployerName(String employerName) {
        EmployerName = employerName;
    }

    public String getEmployerAddressCity() {
        return EmployerAddressCity;
    }

    public void setEmployerAddressCity(String employerAddressCity) {
        EmployerAddressCity = employerAddressCity;
    }

    public String getEmployerAddressStreet() {
        return EmployerAddressStreet;
    }

    public void setEmployerAddressStreet(String employerAddressStreet) {
        EmployerAddressStreet = employerAddressStreet;
    }

    public String getEmployerAddressNumber() {
        return EmployerAddressNumber;
    }

    public void setEmployerAddressNumber(String employerAddressNumber) {
        EmployerAddressNumber = employerAddressNumber;
    }

    public String getEmployerPostcode() {
        return EmployerPostcode;
    }

    public void setEmployerPostcode(String employerPostcode) {
        EmployerPostcode = employerPostcode;
    }

    public String getEmployerEmail() {
        return EmployerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        EmployerEmail = employerEmail;
    }

    public String getContactPersonNameSurname() {
        return ContactPersonNameSurname;
    }

    public void setContactPersonNameSurname(String contactPersonNameSurname) {
        ContactPersonNameSurname = contactPersonNameSurname;
    }

    public String getContactPersonPosition() {
        return ContactPersonPosition;
    }

    public void setContactPersonPosition(String contactPersonPosition) {
        ContactPersonPosition = contactPersonPosition;
    }

    public String getEmployerPhone() {
        return EmployerPhone;
    }

    public void setEmployerPhone(String employerPhone) {
        EmployerPhone = employerPhone;
    }

    public String getEmployerWebsite() {
        return EmployerWebsite;
    }

    public void setEmployerWebsite(String employerWebsite) {
        EmployerWebsite = employerWebsite;
    }

    public String getEmployerTwitter() {
        return EmployerTwitter;
    }

    public void setEmployerTwitter(String employerTwitter) {
        EmployerTwitter = employerTwitter;
    }

    public String getEmployerFB() {
        return EmployerFB;
    }

    public void setEmployerFB(String employerFB) {
        EmployerFB = employerFB;
    }

    public int getNumberOfEmployeesID() {
        return NumberOfEmployeesID;
    }

    public void setNumberOfEmployeesID(int numberOfEmployeesID) {
        NumberOfEmployeesID = numberOfEmployeesID;
    }

    public String getNumberOfEmployeesName() {
        return NumberOfEmployeesName;
    }

    public void setNumberOfEmployeesName(String numberOfEmployeesName) {
        NumberOfEmployeesName = numberOfEmployeesName;
    }

    public String getCompanySummary() {
        return CompanySummary;
    }

    public void setCompanySummary(String companySummary) {
        CompanySummary = companySummary;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getLogoLink() {
        return LogoLink;
    }

    public void setLogoLink(String logoLink) {
        LogoLink = logoLink;
    }

    public Boolean getGivesSiteExperience() {
        return GivesSiteExperience;
    }

    public void setGivesSiteExperience(Boolean givesSiteExperience) {
        GivesSiteExperience = givesSiteExperience;
    }

    public Boolean getGivesSiteVisits() {
        return GivesSiteVisits;
    }

    public void setGivesSiteVisits(Boolean givesSiteVisits) {
        GivesSiteVisits = givesSiteVisits;
    }

    public Boolean getGivesWorkshops() {
        return GivesWorkshops;
    }

    public void setGivesWorkshops(Boolean givesWorkshops) {
        GivesWorkshops = givesWorkshops;
    }

    public Boolean getGivesPresentations() {
        return GivesPresentations;
    }

    public void setGivesPresentations(Boolean givesPresentations) {
        GivesPresentations = givesPresentations;
    }

    public Boolean getAttendsCareerFairs() {
        return AttendsCareerFairs;
    }

    public void setAttendsCareerFairs(Boolean attendsCareerFairs) {
        AttendsCareerFairs = attendsCareerFairs;
    }

    public Boolean getGivesWebinars() {
        return GivesWebinars;
    }

    public void setGivesWebinars(Boolean givesWebinars) {
        GivesWebinars = givesWebinars;
    }

    public Boolean getWorksWithPrimaryPupils() {
        return WorksWithPrimaryPupils;
    }

    public void setWorksWithPrimaryPupils(Boolean worksWithPrimaryPupils) {
        WorksWithPrimaryPupils = worksWithPrimaryPupils;
    }

    public Boolean getUseOfModernForeignLanguage() {
        return UseOfModernForeignLanguage;
    }

    public void setUseOfModernForeignLanguage(Boolean useOfModernForeignLanguage) {
        UseOfModernForeignLanguage = useOfModernForeignLanguage;
    }

    public Boolean getRunsBusinessInWelsh() {
        return RunsBusinessInWelsh;
    }

    public void setRunsBusinessInWelsh(Boolean runsBusinessInWelsh) {
        RunsBusinessInWelsh = runsBusinessInWelsh;
    }

    public Boolean getCanDeliverToSchoolsInWelsh() {
        return CanDeliverToSchoolsInWelsh;
    }

    public void setCanDeliverToSchoolsInWelsh(Boolean canDeliverToSchoolsInWelsh) {
        CanDeliverToSchoolsInWelsh = canDeliverToSchoolsInWelsh;
    }

    public Boolean getHasApprenticeshipProgramme() {
        return HasApprenticeshipProgramme;
    }

    public void setHasApprenticeshipProgramme(Boolean hasApprenticeshipProgramme) {
        HasApprenticeshipProgramme = hasApprenticeshipProgramme;
    }


    public int getEmployerLanguageID() {
        return EmployerLanguageID;
    }

    public void setEmployerLanguageID(int employerLanguageID) {
        EmployerLanguageID = employerLanguageID;
    }

    public String getEmployerLanguageName() {
        return EmployerLanguageName;
    }

    public void setEmployerLanguageName(String employerLanguageName) {
        EmployerLanguageName = employerLanguageName;
    }


    public int getEmployerLocalAuthorityID() {
        return EmployerLocalAuthorityID;
    }

    public void setEmployerLocalAuthorityID(int employerLocalAuthorityID) {
        EmployerLocalAuthorityID = employerLocalAuthorityID;
    }

    public String getEmployerLocalAuthorityName() {
        return EmployerLocalAuthorityName;
    }

    public void setEmployerLocalAuthorityName(String employerLocalAuthorityName) {
        EmployerLocalAuthorityName = employerLocalAuthorityName;
    }

    public int getEmployerAreaOfCurriculumID() {
        return EmployerAreaOfCurriculumID;
    }

    public void setEmployerAreaOfCurriculumID(int employerAreaOfCurriculumID) {
        EmployerAreaOfCurriculumID = employerAreaOfCurriculumID;
    }

    public String getEmployAreaOfCurriculumName() {
        return EmployAreaOfCurriculumName;
    }

    public void setEmployAreaOfCurriculumName(String employAreaOfCurriculumName) {
        EmployAreaOfCurriculumName = employAreaOfCurriculumName;
    }

    public int getEmployerIndustrySectorID() {
        return EmployerIndustrySectorID;
    }

    public void setEmployerIndustrySectorID(int employerIndustrySectorID) {
        EmployerIndustrySectorID = employerIndustrySectorID;
    }

    public String getEmployerIndustrySectorName() {
        return EmployerIndustrySectorName;
    }

    public void setEmployerIndustrySectorName(String employerIndustrySectorName) {
        EmployerIndustrySectorName = employerIndustrySectorName;
    }

    public int getEmployerCooperationTypeID() {
        return EmployerCooperationTypeID;
    }

    public void setEmployerCooperationTypeID(int employerCooperationTypeID) {
        EmployerCooperationTypeID = employerCooperationTypeID;
    }

    public String getEmployerCooperationTypeName() {
        return EmployerCooperationTypeName;
    }

    public void setEmployerCooperationTypeName(String employerCooperationTypeName) {
        EmployerCooperationTypeName = employerCooperationTypeName;
    }


    public int getEmployerPreferencesID() {
        return EmployerPreferencesID;
    }

    public void setEmployerPreferencesID(int employerPreferencesID) {
        EmployerPreferencesID = employerPreferencesID;
    }

    public String getEmployerPreferencesName() {
        return EmployerPreferencesName;
    }

    public void setEmployerPreferencesName(String employerPreferencesName) {
        EmployerPreferencesName = employerPreferencesName;
    }

    public int getEmployerAlumniID() {
        return EmployerAlumniID;
    }

    public void setEmployerAlumniID(int employerAlumniID) {
        EmployerAlumniID = employerAlumniID;
    }

    public String getEmployerAlumniName() {
        return EmployerAlumniName;
    }

    public void setEmployerAlumniName(String employerAlumniName) {
        EmployerAlumniName = employerAlumniName;
    }

    public int getEmployerAlumniSchoolID() {
        return EmployerAlumniSchoolID;
    }

    public void setEmployerAlumniSchoolID(int employerAlumniSchoolID) {
        EmployerAlumniSchoolID = employerAlumniSchoolID;
    }
}


