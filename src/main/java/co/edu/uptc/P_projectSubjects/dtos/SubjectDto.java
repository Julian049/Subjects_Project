package co.edu.uptc.P_projectSubjects.dtos;

import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.exceptions.TypeMessage;
import co.edu.uptc.P_projectSubjects.models.Subject;
import co.edu.uptc.services.dynamic.UptcList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectDto {
    private String name;
    private String subjectCode;

    public static SubjectDto fromSubject(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName(subject.getName());
        subjectDto.setSubjectCode(subject.getSubjectCode());
        return subjectDto;
    }

    public static List<SubjectDto> fromSubjectList(List<Subject> subjects) {
        List<SubjectDto> subjectDtos = new UptcList<>();
        for (Subject subject : subjects) {
            subjectDtos.add(fromSubject(subject));
        }
        return subjectDtos;
    }

    public static void validateSubject(SubjectDto subjectDto) throws ProjectException {
        if (subjectDto.getName() == null ||
                subjectDto.getSubjectCode() == null) {
            throw new ProjectException(TypeMessage.INFORMATION_INCOMPLETE);
        }
    }

    public static Subject toSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setSubjectCode(subjectDto.getSubjectCode());
        subject.setName(subjectDto.getName());
        return subject;
    }
}
