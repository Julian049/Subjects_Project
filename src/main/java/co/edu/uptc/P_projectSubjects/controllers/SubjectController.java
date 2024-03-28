package co.edu.uptc.P_projectSubjects.controllers;

import co.edu.uptc.P_projectSubjects.dtos.SubjectDto;
import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.models.Subject;
import co.edu.uptc.P_projectSubjects.services.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    SubjectService service = new SubjectService();

    @PostMapping()
    public ResponseEntity<Object> postSubject(@RequestBody SubjectDto subjectDto) {
        try {
            SubjectDto.validateSubject(subjectDto);
            service.add(SubjectDto.toSubject(subjectDto));
            return ResponseEntity.status(HttpStatus.OK).body(subjectDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getSubjects() {
        List<Subject> subjects;
        try {
            subjects = service.getSubjects();
            return ResponseEntity.status(HttpStatus.OK).body(SubjectDto.fromSubjectList(subjects));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @GetMapping("/delete/{subjectCode}")
    public ResponseEntity<Object> deleteSubject(@PathVariable String subjectCode) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(SubjectDto.fromSubject(service.deleteSubject(subjectCode)));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @PostMapping("/modify/{subjectCode}")
    public ResponseEntity<Object> modifySubject(@PathVariable String subjectCode, @RequestBody Subject subject) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(SubjectDto.fromSubject(service.modifySubject(subjectCode, subject)));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }


}
