package com.openclassroom.mediscreenclient.proxy;

import com.openclassroom.mediscreenclient.model.NotesBean;
import com.openclassroom.mediscreenclient.model.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@FeignClient(name="microservice-notes", url = "microservice-notes:8082")
public interface NotesProxy {

    @PostMapping("patHistory/add")
    public NotesBean addNotes(@RequestBody NotesBean notes);

    @GetMapping("patHistory/find")
    public List<NotesBean> findNotes(@RequestParam int patientId);

    @PutMapping("/patHistory/edit/{id}")
    public NotesBean editNotes(@RequestBody NotesBean notes);

    @DeleteMapping("patHistory/delete")
    public List<NotesBean> deleteNotes(@RequestBody NotesBean notes);

}
