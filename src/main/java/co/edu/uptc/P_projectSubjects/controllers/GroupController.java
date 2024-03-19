package co.edu.uptc.P_projectSubjects.controllers;

import co.edu.uptc.P_projectSubjects.dtos.GroupDto;
import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.models.Group;
import co.edu.uptc.P_projectSubjects.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    GroupService service = new GroupService();

    @PostMapping()
    public ResponseEntity<Object> postGroup(@RequestBody Group group) {
        try {
            service.add(group);
            return ResponseEntity.status(HttpStatus.OK).body("Group added" + group.toString());
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getGroupsDto() {
        List<GroupDto> groups;
        try {
            groups = service.getGroupsDto();
            return ResponseEntity.status(HttpStatus.OK).body(groups);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @GetMapping("/allGroups")
    public ResponseEntity<Object> getGroups() {
        List<Group> groups;
        try {
            groups = service.getGroups();
            return ResponseEntity.status(HttpStatus.OK).body(groups);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<Object> deleteGroup(@RequestBody Group group) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.deleteGroup(group));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @PostMapping("/modify/{subjectCode}/{placeCode}")
    public ResponseEntity<Object> modifyGroup(@PathVariable String subjectCode, @PathVariable String placeCode, @RequestBody ArrayList<String> schedule) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.modifyGroup(placeCode, subjectCode,schedule));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }
}
