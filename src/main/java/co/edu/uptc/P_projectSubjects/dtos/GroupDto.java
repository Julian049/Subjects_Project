package co.edu.uptc.P_projectSubjects.dtos;

import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.models.Group;
import co.edu.uptc.P_projectSubjects.models.Place;
import co.edu.uptc.P_projectSubjects.models.Subject;
import co.edu.uptc.P_projectSubjects.services.PlaceService;
import co.edu.uptc.P_projectSubjects.services.SubjectService;
import co.edu.uptc.services.dynamic.UptcList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupDto {

    private Subject subject;
    private Place place;
    private UptcList<String> schedule;

    public static GroupDto fromGroup(Group group) throws ProjectException{
        SubjectService subjectService = new SubjectService();
        PlaceService placeService = new PlaceService();
        GroupDto groupDto = new GroupDto();
        groupDto.setSchedule(group.getSchedule());
        groupDto.setSubject(subjectService.getSubjectByCode(group.getSubjectCode()));
        groupDto.setPlace(placeService.getPlaceByCode(group.getPlaceCode()));
        return groupDto;
    }

    public static List<GroupDto> fromGroupList(List<Group> groups)throws ProjectException{
        List<GroupDto> groupDtos = new UptcList<>();
        for (Group group : groups) {
            groupDtos.add(fromGroup(group));
        }
        return groupDtos;
    }
}
