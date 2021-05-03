package ebe.API;

import ebe.DBMethods.EmployerQueries;
import ebe.DBMethods.EventQueries;
import ebe.DBMethods.SchoolQueries;
import ebe.DBMethods.VacancyQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@RestController
public class EmployerAPI {

    private EmployerQueries EmployerQrys;
    private EventQueries EventQrys;
    private SchoolQueries SchoolQrys;
    private VacancyQueries VacancyQrys;

    @Autowired
    public EmployerAPI(EmployerQueries em, EventQueries ev, SchoolQueries sc, VacancyQueries va){
        EmployerQrys = em;
        EventQrys = ev;
        SchoolQrys = sc;
        VacancyQrys = va;
    }

    //1. Create Employer
    @GetMapping(value="/ebe/api/create/employer")
    public boolean createEmployer(
            @RequestParam(name="StatusOfEmployerID", required = true) int statusOfEmployerID,
            @RequestParam(name="EmployerName", required = true) String employerName,
            @RequestParam(name="EmployerSummary", required = true) String companySummary,
            @RequestParam(name="EmployerAddressCity", required = true) String employerAddressCity,
            @RequestParam(name="EmployerAddressStreet", required = true) String employerAddressStreet,
            @RequestParam(name="EmployerAddressNumber", required = true) String employerAddressNumber,
            @RequestParam(name="EmployerPostcode", required = true) String employerPostcode,
            @RequestParam(name="EmployerEmail", required = true) String employerEmail,
            @RequestParam(name="ContactPersonNameSurname", required = true) String contactPersonNameSurname,
            @RequestParam(name="ContactPersonPosition", required = true) String contactPersonPosition,
            @RequestParam(name="EmployerPhone", required = true) String employerPhone,
            @RequestParam(name="EmployerWebsite", required = false) String  employerWebsite,
            @RequestParam(name="EmployerTwitter", required = false) String employerTwitter,
            @RequestParam(name="EmployerFB", required = false) String employerFB,
            @RequestParam(name="NumberOfEmployeesID", required = true) int numberOfEmployeesID,
            @RequestParam(name="EmployerNotes", required = false) String employerNotes,
//            @RequestParam(name="LogoLink") String logoLink,
            @RequestParam(name="EmployerSectorIndustry", required = true) String employerSectorIndustry,
            @RequestParam(name="EmployerCooperationType", required = true) String employerCooperationType,
            @RequestParam(name="EmployerCurriculumAreas", required = true) String employerCurriculumAreas,
            @RequestParam(name="EmployerPreferences", required = true) String employerPreferences,
            @RequestParam(name="EmployerLanguage", required = false) String employerLanguage,
            @RequestParam(name="SchoolPreferences", required = true) String schoolPreferences,
            @RequestParam(name="LocalAuthorities", required = true) String localAuthorities) throws ParseException {

        // ---------------------------
        //1. Create the ArrayList that are going to be used to populate the database
        ArrayList<Integer> employerCooperationTypeList = new ArrayList<>();
        ArrayList<Integer> employerIndustrySectorList = new ArrayList<>();
        ArrayList<Integer> employerPreferencesList = new ArrayList<>();
        ArrayList<Integer> employerSupportCurriculumAreaList = new ArrayList<>();
        ArrayList<Integer> employerLanguageUsedList = new ArrayList<>();
        ArrayList<Integer> employerLocalAuthorityList = new ArrayList<>();
        ArrayList<Integer> employerSchoolPreferencesList = new ArrayList<>();

        // ---------------------------
        //2. Populate the ArrayList
        if(employerCooperationType.length() != 0){
            for (String employerID : employerCooperationType.split(",")) {
                employerCooperationTypeList.add(Integer.parseInt(employerID));
            }
        }
        if(employerSectorIndustry.length() != 0){
            for (String employerID : employerSectorIndustry.split(",")) {
                employerIndustrySectorList.add(Integer.parseInt(employerID));
            }
        }
        if(employerPreferences.length() != 0){
            for (String employerID : employerPreferences.split(",")) {
                employerPreferencesList.add(Integer.parseInt(employerID));
            }
        }

        if(employerCurriculumAreas.length() != 0){
            for (String employerID : employerCurriculumAreas.split(",")) {
                employerSupportCurriculumAreaList.add(Integer.parseInt(employerID));
            }
        }

        if(employerLanguage.length() != 0) {
            for (String empID : employerLanguage.split(",")) {
                employerLanguageUsedList.add(Integer.parseInt(empID));
            }
        }

        if(schoolPreferences.length() != 0){
            for (String empID : schoolPreferences.split(",")) {
                employerSchoolPreferencesList.add(Integer.parseInt(empID));
            }
        }

        if(localAuthorities.length() != 0){
            for (String empID : localAuthorities.split(",")) {
                employerLocalAuthorityList.add(Integer.parseInt(empID));
            }
        }

        // ---------------------------
        //3. Create the Employer
        int createVal = EmployerQrys.createEmployer(statusOfEmployerID,employerName,employerAddressCity,employerAddressStreet,employerAddressNumber,
                employerPostcode,employerEmail,contactPersonNameSurname,contactPersonPosition,employerPhone,employerWebsite,
                employerTwitter, employerFB,numberOfEmployeesID,companySummary,employerNotes);

        // ---------------------------
        //4. Get Employer Created Id
        int employerID = EmployerQrys.getLastEmployerCreated(employerName);

        // ---------------------------
        //5. Fill Intersections

        //     5.1. Intersection Table - Employer / Cooperation Type
        if(employerCooperationType.length() != 0) {
            EmployerQrys.createEmployerCooperationIntersection(employerID, employerCooperationTypeList);
        }

        //     5.2. Intersection Table - Employer / Industry Sector
        if(employerSectorIndustry.length() != 0) {
            EmployerQrys.createEmployerIndustrySectorIntersection(employerID, employerIndustrySectorList);
        }

        //     5.3. Intersection Table - Employer / Preferences
        if(employerPreferences.length() != 0) {
            EmployerQrys.createEmployerPreferencesIntersection(employerID, employerPreferencesList);
        }

        //     5.4. Intersection Table - Employer / School Preferences
        if(schoolPreferences.length() != 0) {
            EmployerQrys.createSchoolEmployerSchoolPreferencesIntersection(employerID, employerSchoolPreferencesList);
        }

        //     5.5. Intersection Table - Employer / Support of Area of Curriculum
        if(employerCurriculumAreas.length() != 0) {
            EmployerQrys.createEmployerEmployerCurriculumAreaIntersection(employerID, employerSupportCurriculumAreaList);
        }

        //     5.6. Intersection Table - Employer / Language Used
        if(employerLanguage.length() != 0) {
            EmployerQrys.createSchoolEmployerLanguageIntersection(employerID, employerLanguageUsedList);
        }

        //     5.7. Intersection Table - Employer / Local Authority
        if(localAuthorities.length() != 0) {
            EmployerQrys.createSchoolEmployerLocalAuthoritiesIntersection(employerID, employerLocalAuthorityList);
        }
        return createVal == 1;
    }


    //2. Update Employer
    @GetMapping(value="/ebe/api/update/employer")
    public boolean updateEmployer(
            @RequestParam(name="EmployerID", required = true) int employerID,
            @RequestParam(name="StatusOfEmployerID", required = true) int statusOfEmployerID,
            @RequestParam(name="EmployerName", required = true) String employerName,
            @RequestParam(name="EmployerSummary", required = true) String companySummary,
            @RequestParam(name="EmployerAddressCity", required = true) String employerAddressCity,
            @RequestParam(name="EmployerAddressStreet", required = true) String employerAddressStreet,
            @RequestParam(name="EmployerAddressNumber", required = true) String employerAddressNumber,
            @RequestParam(name="EmployerPostcode", required = true) String employerPostcode,
            @RequestParam(name="EmployerEmail", required = true) String employerEmail,
            @RequestParam(name="ContactPersonNameSurname", required = true) String contactPersonNameSurname,
            @RequestParam(name="ContactPersonPosition", required = true) String contactPersonPosition,
            @RequestParam(name="EmployerPhone", required = true) String employerPhone,
            @RequestParam(name="EmployerWebsite") String  employerWebsite,
            @RequestParam(name="EmployerTwitter") String employerTwitter,
            @RequestParam(name="EmployerFB") String employerFB,
            @RequestParam(name="NumberOfEmployeesID", required = true) int numberOfEmployeesID,
            @RequestParam(name="EmployerNotes", required = false) String employerNotes,
//            @RequestParam(name="LogoLink") String LogoLink,
            @RequestParam(name="EmployerSectorIndustry", required = true) String employerSectorIndustry,
            @RequestParam(name="EmployerCooperationType", required = true) String employerCooperationType,
            @RequestParam(name="EmployerCurriculumAreas", required = true) String employerCurriculumAreas,
            @RequestParam(name="EmployerPreferences", required = true) String employerPreferences,
            @RequestParam(name="EmployerLanguage", required = false) String employerLanguage,
            @RequestParam(name="SchoolPreferences", required = true) String schoolPreferences,
            @RequestParam(name="LocalAuthorities", required = true) String localAuthorities,
            @RequestParam(name="CreateEmployerAlumniName", required = false) String createEmployerAlumniName,
            @RequestParam(name="CreateEmployerAlumniSchoolID", required = false) String createEmployerAlumniSchoolID,
            @RequestParam(name="UpdateEmployerAlumniID", required = false) String updateEmployerAlumniID,
            @RequestParam(name="UpdateEmployerAlumniName", required = false) String updateEmployerAlumniName,
            @RequestParam(name="UpdateEmployerAlumniSchoolID", required = false) String updateEmployerAlumniSchoolID,
            @RequestParam(name="VideoLink", required = false) String VideoLink
            ) throws ParseException {

        // ---------------------------
        //1. Create the ArrayList that are going to be used to populate the database
        ArrayList<Integer> employerCooperationTypeList = new ArrayList<>();
        ArrayList<Integer> employerIndustrySectorList = new ArrayList<>();
        ArrayList<Integer> employerPreferencesList = new ArrayList<>();
        ArrayList<Integer> employerSupportCurriculumAreaList = new ArrayList<>();
        ArrayList<Integer> employerLanguageUsedList = new ArrayList<>();
        ArrayList<Integer> employerLocalAuthorityList = new ArrayList<>();
        ArrayList<Integer> employerSchoolPreferencesList = new ArrayList<>();
        ArrayList<String> createEmployerAlumniNameList = new ArrayList<>();
        ArrayList<Integer> createEmployerAlumniSchoolIDList = new ArrayList<>();
        ArrayList<Integer> updateEmployerAlumniIDList = new ArrayList<>();
        ArrayList<String> updateEmployerAlumniNameList = new ArrayList<>();
        ArrayList<Integer> updateEmployerAlumniSchoolIDList = new ArrayList<>();

        // ---------------------------
        //2. Populate the ArrayList
         if(employerCooperationType.length() != 0){
            for (String empID : employerCooperationType.split(",")) {
                employerCooperationTypeList.add(Integer.parseInt(empID));
            }
        }
        if(employerSectorIndustry.length() != 0){
            for (String empID : employerSectorIndustry.split(",")) {
                employerIndustrySectorList.add(Integer.parseInt(empID));
            }
        }
        if(employerPreferences.length() != 0){
            for (String empID : employerPreferences.split(",")) {
            employerPreferencesList.add(Integer.parseInt(empID));
            }
        }

        if(employerCurriculumAreas.length() != 0){
            for (String empID : employerCurriculumAreas.split(",")) {
                employerSupportCurriculumAreaList.add(Integer.parseInt(empID));
            }
        }

        if(schoolPreferences.length() != 0){
            for (String empID : schoolPreferences.split(",")) {
                employerSchoolPreferencesList.add(Integer.parseInt(empID));
            }
        }

        if(employerLanguage.length() != 0) {
            for (String empID : employerLanguage.split(",")) {
                employerLanguageUsedList.add(Integer.parseInt(empID));
            }
        }
        if(localAuthorities.length() != 0){
            for (String empID : localAuthorities.split(",")) {
                employerLocalAuthorityList.add(Integer.parseInt(empID));
            }
        }

        if(createEmployerAlumniName.length() != 0) {
            for (String empID : createEmployerAlumniName.split(",")) {
                createEmployerAlumniNameList.add(empID);
            }
        }
        if(!createEmployerAlumniSchoolID.equals("")){
            for (String empID : createEmployerAlumniSchoolID.split(",")) {
                createEmployerAlumniSchoolIDList.add(Integer.parseInt(empID));
            }
        }

        if(!updateEmployerAlumniID.equals("")) {
            for (String empID : updateEmployerAlumniID.split(",")) {
                updateEmployerAlumniIDList.add(Integer.parseInt(empID));
            }
        }

        if(!updateEmployerAlumniName.equals("")) {
            for (String empID : updateEmployerAlumniName.split(",")) {
                updateEmployerAlumniNameList.add(empID);
            }
        }

        if(!updateEmployerAlumniSchoolID.equals("")){
            for (String empID : updateEmployerAlumniSchoolID.split(",")) {
                updateEmployerAlumniSchoolIDList.add(Integer.parseInt(empID));
            }
        }

        // ---------------------------
        //3. Update the Employer
        int updateVal = EmployerQrys.updateEmployer(employerID,statusOfEmployerID,employerName,employerAddressCity,employerAddressStreet,employerAddressNumber,
                employerPostcode,employerEmail,contactPersonNameSurname,contactPersonPosition,employerPhone,employerWebsite,
                employerTwitter, employerFB,numberOfEmployeesID,companySummary,employerNotes);

        // ---------------------------
        //4. Fill Intersections

        //     4.1. Intersection Table - Employer / Cooperation Type
        if(employerCooperationType.length() != 0) {
            EmployerQrys.updateEmployerCooperationIntersection(employerID, employerCooperationTypeList);
        }

        //     4.2. Intersection Table - Employer / Industry Sector
        if(employerSectorIndustry.length() != 0) {
            EmployerQrys.updateEmployerIndustrySectorIntersection(employerID, employerIndustrySectorList);
        }

        //     4.3. Intersection Table - Employer / Preferences
        if(employerPreferences.length() != 0) {
            EmployerQrys.updateEmployerPreferencesIntersection(employerID, employerPreferencesList);
        }

        //     4.4. Intersection Table - Employer / School Preferences
        if(schoolPreferences.length() != 0) {
            EmployerQrys.updateSchoolEmployerSchoolPreferencesIntersection(employerID, employerSchoolPreferencesList);
        }

        //     4.5. Intersection Table - Employer / Support of Area of Curriculum
        if(employerCurriculumAreas.length() != 0) {
            EmployerQrys.updateEmployerEmployerCurriculumAreaIntersection(employerID, employerSupportCurriculumAreaList);
        }

        //     4.6. Intersection Table - Employer / Language Used
        if(employerLanguage.length() != 0) {
            EmployerQrys.updateSchoolEmployerLanguageIntersection(employerID, employerLanguageUsedList);
        }

         //     4.7. Intersection Table - Employer / Local Authority
        if(localAuthorities.length() != 0) {
            EmployerQrys.updateSchoolEmployerLocalAuthoritiesIntersection(employerID, employerLocalAuthorityList);
        }

        //      4.8.  Update Alumni
        if(!updateEmployerAlumniIDList.isEmpty()) {
            EmployerQrys.updateAlumni(updateEmployerAlumniNameList, updateEmployerAlumniSchoolIDList, updateEmployerAlumniIDList);
        }

        if(createEmployerAlumniName.length()!=0) {
            //      4.9.  Create Alumni
            EmployerQrys.createAlumni(createEmployerAlumniNameList, createEmployerAlumniSchoolIDList);
            //      4.10.  Get Alumni IDs
            List<Integer> alumniIdList = EmployerQrys.getAllAlumniIDFromEmployer(createEmployerAlumniNameList, createEmployerAlumniSchoolIDList);
            //      4.11.  Create Intersection Table - Employer / Alumni
            EmployerQrys.createEmployerAlumniIntersection(employerID, alumniIdList);
        }

        // 4.12 Upload Video Link
        if(!VideoLink.isEmpty()){
            EmployerQrys.insertVideoLink(Integer.valueOf(employerID), VideoLink);
        }

        return updateVal == 1;
    }

    //3. Delete Employer
    @DeleteMapping("/ebe/api/delete/employer")
    public boolean deleteEmployers(@RequestParam(value="employerId") Integer employerId){
        if (EmployerQrys.deleteEmployer(employerId) == 1) {
            return true;
        } else {
            return false;
        }
    }

    //4. Delete Alumni
    @DeleteMapping("/ebe/api/delete/employer/alumni")
    public boolean deleteAlumni(@RequestParam(value="alumniID") Integer alumniID){
        if (EmployerQrys.deleteAlumni(alumniID) == 1) {
            return true;
        } else {
            return false;
        }
    }

    ///////////////////////    FILTER     ////////////////////////////////
    @RequestMapping("/ebe/api/filter/employer")
    //5. Filter Employers
    public List<Integer> filterEmployers(@RequestParam(name="EmployerSectorIndustry") String EmployerSectorIndustry,
                                         @RequestParam(name="EmployerCooperationType") String EmployerCooperationType,
                                         @RequestParam(name="EmployerCurriculumAreas") String EmployerCurriculumAreas,
                                         @RequestParam(name="EmployerPreferences") String EmployerPreferences,
                                         @RequestParam(name="EmployerLanguage") String EmployerLanguage,
                                         @RequestParam(name="SchoolPreferences") String SchoolPreferences,
                                         @RequestParam(name="LocalAuthorities") String LocalAuthorities){

        List<Integer> filteredEmployerIDs = new ArrayList<Integer>();

        List<Integer> employerCooperationTypeList = new ArrayList<Integer>();
        List<Integer> employerIndustrySectorList = new ArrayList<Integer>();
        List<Integer> employerPreferencesList = new ArrayList<Integer>();
        List<Integer> employerSupportCurriculumAreaList = new ArrayList<Integer>();
        List<Integer> employerLanguageUsedList = new ArrayList<Integer>();
        List<Integer> employerLocalAuthorityList = new ArrayList<Integer>();
        List<Integer> employerSchoolPreferencesList = new ArrayList<Integer>();

        if (!EmployerCooperationType.equals("")) {
            for (String cooperationTypeID : EmployerCooperationType.split(",")) {
                employerCooperationTypeList.add(Integer.parseInt(cooperationTypeID));
            }
        }  else{
            employerCooperationTypeList = Arrays.asList();
        }

        if (!EmployerSectorIndustry.equals("")) {
            for (String sectorIndustryID : EmployerSectorIndustry.split(",")) {
                employerIndustrySectorList.add(Integer.parseInt(sectorIndustryID));
            }
        } else {
            employerIndustrySectorList = Arrays.asList();
        }

        if (!EmployerPreferences.equals("")) {
            for (String employerPreferenceID : EmployerPreferences.split(",")) {
                employerPreferencesList.add(Integer.parseInt(employerPreferenceID));
            }
        } else {
            employerPreferencesList = Arrays.asList();
        }

        if (!EmployerCurriculumAreas.equals("")) {
            for (String areaOfCurriculumID : EmployerCurriculumAreas.split(",")) {
                employerSupportCurriculumAreaList.add(Integer.parseInt(areaOfCurriculumID));
            }
        } else {
            employerSupportCurriculumAreaList = Arrays.asList();
        }

        if (!EmployerLanguage.equals("")) {
            for (String languageID : EmployerLanguage.split(",")) {
                employerLanguageUsedList.add(Integer.parseInt(languageID));
            }
        } else {
            employerLanguageUsedList = Arrays.asList();
        }

        if (!LocalAuthorities.equals("")) {
            for (String localAuthorityID : LocalAuthorities.split(",")) {
                employerLocalAuthorityList.add(Integer.parseInt(localAuthorityID));
            }
        } else {
            employerLocalAuthorityList = Arrays.asList();
        }

        if (!SchoolPreferences.equals("")) {
            for (String schoolID : SchoolPreferences.split(",")) {
                employerSchoolPreferencesList.add(Integer.parseInt(schoolID));
            }
        } else {
            employerSchoolPreferencesList = Arrays.asList();
        }

        // There should be AlumniList as well, once implemented. In function below I call empty Arrays.asList() to fill this place.
        filteredEmployerIDs = EmployerQrys.getFilteredEmployersIds(employerIndustrySectorList, employerLocalAuthorityList,
                employerSupportCurriculumAreaList, employerLanguageUsedList,
                employerSchoolPreferencesList, employerPreferencesList, employerCooperationTypeList, Arrays.asList());

        return filteredEmployerIDs;
    }

    ///////////////////////    SORT BY     ////////////////////////////////
    @GetMapping("/ebe/api/employer/sortBy")
    public List<Integer> SortBy(@RequestParam(value="sortBy") String sortBy,
                                @RequestParam(value="orderBy") String orderBy){

        List<Integer> orderEmployerIds = new ArrayList<Integer>();

        if(sortBy.equals("Name")){
            orderEmployerIds = EmployerQrys.sortByEmployerByName(orderBy);
        }

        return orderEmployerIds;
    }

    /////////////////////    DELETE LINK    ////////////////////////////////
    @DeleteMapping("/ebe/api/delete/video-link")
    //5. Filter Employers
    public void addEmployerVideoLink(@RequestParam("employerID") String employerID,
                                     @RequestParam("link") String link){

        EmployerQrys.deleteVideoLink(Integer.valueOf(employerID), link);

    }



}


