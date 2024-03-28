package co.edu.uptc.P_projectSubjects.dtos;

import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.exceptions.TypeMessage;
import co.edu.uptc.P_projectSubjects.models.Group;
import co.edu.uptc.services.dynamic.UptcList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupDto {
    private String subjectCode;
    private String placeCode;
    private UptcList<String> schedule;

    public static GroupDto fromGroup(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setSubjectCode(group.getSubjectCode());
        groupDto.setPlaceCode(group.getPlaceCode());
        groupDto.setSchedule(group.getSchedule());
        return groupDto;
    }

    public static List<GroupDto> fromGroupList(List<Group> groups) {
        List<GroupDto> groupDtos = new UptcList<>();
        for (Group group : groups) {
            groupDtos.add(fromGroup(group));
        }
        return groupDtos;
    }

    public static void validateGroup(GroupDto groupDto) throws ProjectException {
        if (groupDto.getSubjectCode() == null ||
                groupDto.getPlaceCode() == null ||
                verifyScheduleSize(groupDto.getSchedule())) {
            throw new ProjectException(TypeMessage.INFORMATION_INCOMPLETE);
        }
    }

    private static boolean verifyScheduleSize(UptcList<String> list) throws ProjectException {
        if (list.size() > 3) {
            throw new ProjectException(TypeMessage.MAX_ITEMS);
        }
        return false;
    }

    public static Group toGroup(GroupDto groupDto) {
        Group group = new Group();
        group.setSubjectCode(groupDto.getSubjectCode());
        group.setPlaceCode(groupDto.getPlaceCode());
        group.setSchedule(groupDto.getSchedule());
        return group;
    }
}
