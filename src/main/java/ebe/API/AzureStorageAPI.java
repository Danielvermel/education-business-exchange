package ebe.API;

import ebe.DBClasses.Vacancy;
import ebe.DBMethods.EmployerQueries;
import ebe.DBMethods.EventQueries;
import ebe.DBMethods.VacancyQueries;
import ebe.StorageAdapter.AzureBlobAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
public class AzureStorageAPI {

    @Autowired
    private EmployerQueries EmployerQrys;

    @Autowired
    private VacancyQueries VacancyQrys;

    @Autowired
    private EventQueries EventQrys;

    @Autowired
    private AzureBlobAdapter azureBlobAdapter;

    @GetMapping("/ebe/container")
    public ResponseEntity createContainer(@RequestParam String containerName){
        boolean created = azureBlobAdapter.createContainer(containerName);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/ebe/upload")
    @ResponseBody
    public ResponseEntity upload(@RequestParam("name") String containerName,
                                 @RequestParam("file") MultipartFile file,
                                 @RequestParam("ID") String ID){

        URI url = azureBlobAdapter.upload(containerName, file);
        if (containerName.equals("employer")){
            EmployerQrys.insertDocument(Integer.valueOf(ID), url.toString());
        }
        if (containerName.equals("employerlogo")){
            EmployerQrys.insertLogoLink(Integer.valueOf(ID), url.toString());
        }
        if (containerName.equals("vacancy")){
            VacancyQrys.insertDocument(Integer.valueOf(ID), url.toString());
        }
        if (containerName.equals("event")){
            EventQrys.insertDocument(Integer.valueOf(ID), url.toString());
        }

        return ResponseEntity.ok(url);
    }

    @GetMapping("/ebe/blobs")
    public ResponseEntity getAllBlobs(@RequestParam String containerName){
        List<URI> uris = azureBlobAdapter.listBlobs(containerName);
        return ResponseEntity.ok(uris);
    }

    @DeleteMapping("/ebe/delete")
    public ResponseEntity delete(@RequestParam("name") String containerName,
                                 @RequestParam("file") String blobName,
                                 @RequestParam("URL") String url,
                                 @RequestParam("ID") String ID){

        azureBlobAdapter.deleteBlob(containerName, blobName);

        if (containerName.equals("employer")){
            EmployerQrys.deleteDocument(Integer.valueOf(ID), url);
        }
        if (containerName.equals("event")){
            EventQrys.deleteDocument(Integer.valueOf(ID), url);
        }
        if (containerName.equals("vacancy")){
            VacancyQrys.deleteDocument(Integer.valueOf(ID), url);
        }

        return ResponseEntity.ok().build();
    }

}
