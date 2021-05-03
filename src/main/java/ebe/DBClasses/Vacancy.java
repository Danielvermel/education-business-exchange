package ebe.DBClasses;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Vacancy {

    private int VacancyID;
    private String VacancyName;
    private int EmployerID;
    private String EmployerName;
    private String VacancySummary;
    private String VacancyLink;
    private int  TypeOfVacancyID;
    private String  TypeOfVacancyName;
    private int StatusOfVacancyID;
    private String StatusOfVacancyName;
    private @DateTimeFormat(pattern = "yyyy-MM-dd") Date StartOfVacancy;
    private @DateTimeFormat(pattern = "yyyy-MM-dd") Date DeadlineForApplication;
    private int OccupationalCodeID;
    private String OccupationalCodeName;
    private int ApplicationMethodID;
    private String ApplicationMethodName;
    private String VacancyPostcode;
    private String logoLink;

    public Vacancy() {
    }

    public Vacancy(int vacancyID, int employerID, String vacancyName, String vacancySummary, String vacancyLink, int typeOfVacancyID, int statusOfVacancyID, Date startOfVacancy, Date deadlineForApplication, int occupationalCodeID, int applicationMethodID, String vacancyPostcode) {
        VacancyID = vacancyID;
        EmployerID = employerID;
        VacancyName = vacancyName;
        VacancySummary = vacancySummary;
        VacancyLink = vacancyLink;
        TypeOfVacancyID = typeOfVacancyID;
        StatusOfVacancyID = statusOfVacancyID;
        StartOfVacancy = startOfVacancy;
        DeadlineForApplication = deadlineForApplication;
        OccupationalCodeID = occupationalCodeID;
        ApplicationMethodID = applicationMethodID;
        VacancyPostcode = vacancyPostcode;
    }

    public Vacancy(int employerID, String vacancyName, String vacancySummary, String vacancyLink, int typeOfVacancyID, int statusOfVacancyID, Date startOfVacancy, Date deadlineForApplication, int occupationalCodeID, int applicationMethodID, String vacancyPostcode) {
        EmployerID = employerID;
        VacancyName = vacancyName;
        VacancySummary = vacancySummary;
        VacancyLink = vacancyLink;
        TypeOfVacancyID = typeOfVacancyID;
        StatusOfVacancyID = statusOfVacancyID;
        StartOfVacancy = startOfVacancy;
        DeadlineForApplication = deadlineForApplication;
        OccupationalCodeID = occupationalCodeID;
        ApplicationMethodID = applicationMethodID;
        VacancyPostcode = vacancyPostcode;
    }

    public int getVacancyID() {
        return VacancyID;
    }

    public void setVacancyID(int vacancyID) {
        VacancyID = vacancyID;
    }

    public int getEmployerID() {
        return EmployerID;
    }

    public void setEmployerID(int employerID) {
        EmployerID = employerID;
    }

    public String getVacancyName() {
        return VacancyName;
    }

    public void setVacancyName(String vacancyName) {
        VacancyName = vacancyName;
    }

    public String getVacancySummary() {
        return VacancySummary;
    }

    public void setVacancySummary(String vacancySummary) {
        VacancySummary = vacancySummary;
    }

    public String getVacancyLink() {
        return VacancyLink;
    }

    public void setVacancyLink(String vacancyLink) {
        VacancyLink = vacancyLink;
    }

    public int getTypeOfVacancyID() {
        return TypeOfVacancyID;
    }

    public void setTypeOfVacancyID(int typeOfVacancyID) {
        TypeOfVacancyID = typeOfVacancyID;
    }

    public String getTypeOfVacancyName() {
        return TypeOfVacancyName;
    }

    public void setTypeOfVacancyName(String typeOfVacancyName) {
        TypeOfVacancyName = typeOfVacancyName;
    }

    public int getStatusOfVacancyID() {
        return StatusOfVacancyID;
    }

    public void setStatusOfVacancyID(int statusOfVacancyID) {
        StatusOfVacancyID = statusOfVacancyID;
    }

    public String getStatusOfVacancyName() {
        return StatusOfVacancyName;
    }

    public void setStatusOfVacancyName(String statusOfVacancyName) {
        StatusOfVacancyName = statusOfVacancyName;
    }

    public Date getStartOfVacancy() {
        return StartOfVacancy;
    }

    public void setStartOfVacancy(Date startOfVacancy) {
        StartOfVacancy = startOfVacancy;
    }

    public Date getDeadlineForApplication() {
        return DeadlineForApplication;
    }

    public void setDeadlineForApplication(Date deadlineForApplication) {
        DeadlineForApplication = deadlineForApplication;
    }

    public int getOccupationalCodeID() {
        return OccupationalCodeID;
    }

    public void setOccupationalCodeID(int occupationalCodeID) {
        OccupationalCodeID = occupationalCodeID;
    }

    public String getOccupationalCodeName() {
        return OccupationalCodeName;
    }

    public void setOccupationalCodeName(String occupationalCodeName) {
        OccupationalCodeName = occupationalCodeName;
    }

    public int getApplicationMethodID() {
        return ApplicationMethodID;
    }

    public void setApplicationMethodID(int applicationMethodID) {
        ApplicationMethodID = applicationMethodID;
    }

    public String getApplicationMethodName() {
        return ApplicationMethodName;
    }

    public void setApplicationMethodName(String applicationMethodName) {
        ApplicationMethodName = applicationMethodName;
    }

    public String getVacancyPostcode() {
        return VacancyPostcode;
    }

    public void setVacancyPostcode(String vacancyPostcode) {
        VacancyPostcode = vacancyPostcode;
    }

    public String getEmployerName() {
        return EmployerName;
    }

    public void setEmployerName(String employerName) {
        EmployerName = employerName;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }
}
