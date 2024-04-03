package co.edu.uptc.P_projectSubjects.controllers;

import co.edu.uptc.P_projectSubjects.dtos.GroupDto;
import co.edu.uptc.P_projectSubjects.dtos.GroupDto2;
import co.edu.uptc.P_projectSubjects.dtos.SubjectDto;
import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.models.Group;
import co.edu.uptc.P_projectSubjects.models.Subject;
import co.edu.uptc.P_projectSubjects.services.GroupService;
import co.edu.uptc.P_projectSubjects.services.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    GroupService service = new GroupService();

    @PostMapping()
    public ResponseEntity<Object> postGroup(@RequestBody GroupDto groupDto) {
        try {
            GroupDto.validateGroup(groupDto);
            service.add(GroupDto.toGroup(groupDto));
            return ResponseEntity.status(HttpStatus.OK).body(groupDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getGroupsDto() {
        List<GroupDto2> groups;
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
        List<GroupDto> groupsDto;
        try {
            groupsDto = GroupDto.fromGroupList(service.getGroups());
            return ResponseEntity.status(HttpStatus.OK).body(groupsDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteGroup(@RequestBody GroupDto groupDto) {
        try {
            Group group = GroupDto.toGroup(groupDto);
            return ResponseEntity.status(HttpStatus.OK).body(service.deleteGroup(group));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @PostMapping("/modify/{subjectCode}/{placeCode}")
    public ResponseEntity<Object> modifyGroup(@PathVariable String subjectCode, @PathVariable String placeCode, @RequestBody GroupDto groupDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(GroupDto.fromGroup(service.modifyGroup(placeCode, subjectCode, GroupDto.toGroup(groupDto))));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @GetMapping("/getSubjectsByPlace/{placeCode}")
    public ResponseEntity<Object> getSubjectsByPlace(@PathVariable String placeCode) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(SubjectDto.fromSubjectList(service.getSubjectsWithSamePlace(placeCode)));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @GetMapping("/SubjectsWithMoreGroup")
    public ResponseEntity<Object> SubjectsWithMoreGroup() {
        SubjectService subjectService = new SubjectService();
        try {
            List<Subject> list = subjectService.getSubjects();
            return ResponseEntity.status(HttpStatus.OK).body(SubjectDto.fromSubjectList(service.getSubjectsWithMoreGroup(list)));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @GetMapping("/getSubjectsBySchedule/{schedule}")
    public ResponseEntity<Object> getSubjectsBySchedule(@PathVariable String schedule) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(SubjectDto.fromSubjectList(service.getSubjectsWithSameSchedule(schedule)));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }
}
