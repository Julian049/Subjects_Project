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
public class GroupDto2 {

    private Subject subject;
    private Place place;
    private UptcList<String> schedule;

    public static GroupDto2 fromGroup(Group group) throws ProjectException{
        SubjectService subjectService = new SubjectService();
        PlaceService placeService = new PlaceService();
        GroupDto2 groupDto2 = new GroupDto2();
        groupDto2.setSchedule(group.getSchedule());
        groupDto2.setSubject(subjectService.getSubjectByCode(group.getSubjectCode()));
        groupDto2.setPlace(placeService.getPlaceByCode(group.getPlaceCode()));
        return groupDto2;
    }

    public static List<GroupDto2> fromGroupList(List<Group> groups)throws ProjectException{
        List<GroupDto2> group2Dtos = new UptcList<>();
        for (Group group : groups) {
            group2Dtos.add(fromGroup(group));
        }
        return group2Dtos;
    }
}
