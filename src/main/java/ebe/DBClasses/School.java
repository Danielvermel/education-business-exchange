package ebe.DBClasses;

public class School {

    private int SchoolID;
    private String SchoolName;
    private String SchoolAddressCity;
    private String SchoolAddressStreet;
    private String SchoolAddressNumber;
    private String SchoolPostCode;
    private String SchoolEmail;
    private String SchoolPhone;

    public School() {
    }

    public School(int schoolID, String schoolName, String schoolAddressCity, String schoolAddressStreet, String schoolAddressNumber, String schoolPostCode, String schoolEmail, String schoolPhone) {
        SchoolID = schoolID;
        SchoolName = schoolName;
        SchoolAddressCity = schoolAddressCity;
        SchoolAddressStreet = schoolAddressStreet;
        SchoolAddressNumber = schoolAddressNumber;
        SchoolPostCode = schoolPostCode;
        SchoolEmail = schoolEmail;
        SchoolPhone = schoolPhone;
    }

    public School(String schoolName, String schoolAddressCity, String schoolAddressStreet, String schoolAddressNumber, String schoolPostCode, String schoolEmail, String schoolPhone) {
        SchoolName = schoolName;
        SchoolAddressCity = schoolAddressCity;
        SchoolAddressStreet = schoolAddressStreet;
        SchoolAddressNumber = schoolAddressNumber;
        SchoolPostCode = schoolPostCode;
        SchoolEmail = schoolEmail;
        SchoolPhone = schoolPhone;
    }

    //Constructor for locating school by email required for email sender.
    public School(String schoolName, String schoolPostCode){
        SchoolName = schoolName;
        SchoolPostCode = schoolPostCode;
    }

    public int getSchoolID() {
        return SchoolID;
    }

    public void setSchoolID(int schoolID) {
        SchoolID = schoolID;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getSchoolAddressCity() {
        return SchoolAddressCity;
    }

    public void setSchoolAddressCity(String schoolAddressCity) {
        SchoolAddressCity = schoolAddressCity;
    }

    public String getSchoolAddressStreet() {
        return SchoolAddressStreet;
    }

    public void setSchoolAddressStreet(String schoolAddressStreet) {
        SchoolAddressStreet = schoolAddressStreet;
    }

    public String getSchoolAddressNumber() {
        return SchoolAddressNumber;
    }

    public void setSchoolAddressNumber(String schoolAddressNumber) {
        SchoolAddressNumber = schoolAddressNumber;
    }

    public String getSchoolPostCode() {
        return SchoolPostCode;
    }

    public void setSchoolPostCode(String schoolPostCode) {
        SchoolPostCode = schoolPostCode;
    }

    public String getSchoolEmail() {
        return SchoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        SchoolEmail = schoolEmail;
    }

    public String getSchoolPhone() {
        return SchoolPhone;
    }

    public void setSchoolPhone(String schoolPhone) {
        SchoolPhone = schoolPhone;
    }
}
