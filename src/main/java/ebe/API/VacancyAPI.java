package ebe.API;

import ebe.DBMethods.EmployerQueries;
import ebe.DBMethods.EventQueries;
import ebe.DBMethods.SchoolQueries;
import ebe.DBMethods.VacancyQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class VacancyAPI {
    private EmployerQueries EmployerQrys;
    private EventQueries EventQrys;
    private SchoolQueries SchoolQrys;
    private VacancyQueries VacancyQrys;

    @Autowired
    public VacancyAPI(EmployerQueries em, EventQueries ev, SchoolQueries sc, VacancyQueries va){
        EmployerQrys = em;
        EventQrys = ev;
        SchoolQrys = sc;
        VacancyQrys = va;
    }

    ///////////////////////    CREATE     ////////////////////////////////
    //1. Create Vacancies
    @GetMapping(value="/ebe/api/create/vacancy")
    public boolean createVacancy(
            @RequestParam(name="EmployerID", required = true) int employerId,
            @RequestParam(name="VacancyName", required = true) String vacancyName,
            @RequestParam(name="VacancyLink", required = false) String vacancyLink,
            @RequestParam(name="VacancySummary", required = true) String vacancySummary,
            @RequestParam(name="TypeOfVacancyID", required = true) int typeOfVacancyID,
            @RequestParam(name="StatusOfVacancyID", required = true) int StatusOfVacancyID,
            @RequestParam(name="StartOfVacancy", required = true) String startOfVacancy,
            @RequestParam(name="DeadlineForApplication", required = true) String deadlineForApplication,
            @RequestParam(name="OccupationalCodeID", required = true) int occupationalCodeID,
            @RequestParam(name="ApplicationMethodID", required = true) String applicationMethodID,
            @RequestParam(name="VacancyPostcode", required = true) String vacancyPostCode) throws ParseException {

        return 1 == VacancyQrys.createVacancy(employerId,vacancyName,vacancySummary,vacancyLink,typeOfVacancyID,
                StatusOfVacancyID, startOfVacancy,deadlineForApplication,occupationalCodeID,applicationMethodID,
                vacancyPostCode);

    }

    ///////////////////////    Update     ////////////////////////////////
    //2. Update Vacancies
    @GetMapping(value="/ebe/api/update/vacancy")
    public boolean updateVacancy(
            @RequestParam(name="EmployerID", required = true) int employerId,
            @RequestParam(name="VacancyName", required = true) String vacancyName,
            @RequestParam(name="VacancyOldName", required = true) String vacancyOldName,
            @RequestParam(name="VacancyLink", required = false) String vacancyLink,
            @RequestParam(name="VacancySummary", required = true) String vacancySummary,
            @RequestParam(name="TypeOfVacancyID", required = true) int typeOfVacancyID,
            @RequestParam(name="StatusOfVacancyID", required = true) int StatusOfVacancyID,
            @RequestParam(name="StartOfVacancy", required = true) String startOfVacancy,
            @RequestParam(name="DeadlineForApplication", required = true) String deadlineForApplication,
            @RequestParam(name="OccupationalCodeID", required = true) int occupationalCodeID,
            @RequestParam(name="ApplicationMethodID", required = true) String applicationMethodID,
            @RequestParam(name="VacancyPostcode", required = true) String vacancyPostCode,
            @RequestParam(name="VacancyOldPostcode", required = true) String vacancyOldPostCode) throws ParseException {

        int vacancyId = VacancyQrys.getVacancyIdByNameAndPostCode(vacancyOldName,vacancyOldPostCode);
        return 1 == VacancyQrys.updateVacancy(vacancyId,employerId,vacancyName,vacancySummary,vacancyLink,typeOfVacancyID,
                StatusOfVacancyID, startOfVacancy,deadlineForApplication,occupationalCodeID,applicationMethodID,
                vacancyPostCode);
    }

    ///////////////////////    DELETE     ////////////////////////////////
    //3. Delete Vacancy
    @DeleteMapping("/ebe/api/delete/vacancy")
    public boolean deleteVacancies(@RequestParam(value="vacancyId") Integer vacancyId){
        if (VacancyQrys.deleteVacancy(vacancyId) == 1) {
            return true;
        } else {
            return false;
        }
    }

    ///////////////////////    FILTER     ////////////////////////////////
    //4. Filter Vacancy
    @RequestMapping("/ebe/api/filter/vacancy")
    public List<Integer> filterVacancies(@RequestParam(name="typeOfVacancyID") String typeOfVacancyID,
                                         @RequestParam(name="occupationalCodeID") String occupationalCodeID){

        List<Integer> filteredVacancyIDs = new ArrayList<Integer>();

        List<Integer> typeOfVacancyIDList = new ArrayList<Integer>();
        List<Integer> occupationalCodeIDList = new ArrayList<Integer>();

        if (!typeOfVacancyID.equals("")) {
            for (String typeOfVacancy : typeOfVacancyID.split(",")) {
                typeOfVacancyIDList.add(Integer.parseInt(typeOfVacancy));
            }
        }  else{
            typeOfVacancyIDList = Arrays.asList();
        }

        if (!occupationalCodeID.equals("")) {
            for (String occupationalCode : occupationalCodeID.split(",")) {
                occupationalCodeIDList.add(Integer.parseInt(occupationalCode));
            }
        } else {
            occupationalCodeIDList = Arrays.asList();
        }

        filteredVacancyIDs =  VacancyQrys.getFilteredVacanciesIds(typeOfVacancyIDList, occupationalCodeIDList);
        return filteredVacancyIDs;
    }

    ///////////////////////    SORT BY     ////////////////////////////////
    // 5. Sort By Vacancy
    @GetMapping("/ebe/api/vacancy/sortBy")
    public List<Integer> SortBy(@RequestParam(value="sortBy") String sortBy,
                                @RequestParam(value="orderBy") String orderBy) {

        List<Integer> orderVacancyIds = new ArrayList<Integer>();

        if (sortBy.equals("Name")) {
            orderVacancyIds = VacancyQrys.sortByVacancyByName(orderBy);
        }

        if (sortBy.equals("Date")) {
            orderVacancyIds = VacancyQrys.sortByVacancyByDate(orderBy);
        }

        return orderVacancyIds;
    }
}
