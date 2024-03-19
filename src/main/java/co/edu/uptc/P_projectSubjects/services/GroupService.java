package co.edu.uptc.P_projectSubjects.services;

import co.edu.uptc.P_projectSubjects.dtos.GroupDto;
import co.edu.uptc.P_projectSubjects.models.Group;
import co.edu.uptc.P_projectSubjects.models.Place;
import co.edu.uptc.P_projectSubjects.models.Subject;
import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;

import java.util.ArrayList;
import java.util.List;

public class GroupService {

    List<Group> groups = new ArrayList<>();

    public GroupService() {
        this.groups = loadGroups();
    }

    private List<Group> loadGroups() {
        List<Group> groups = new ArrayList<>();

        String subject1 = "8108213";
        String subject2 = "8108214";
        String subject3 = "8108257";

        String place1 = "1111";
        String place2 = "2222";
        String place3 = "3333";


        ArrayList<String> schedule1 = new ArrayList<>();
        schedule1.add("Lunes 8:00-10:00");
        schedule1.add("Miercoles 8:00-10:00");
        schedule1.add("Viernes 8:00-10:00");

        ArrayList<String> schedule2 = new ArrayList<>();
        schedule2.add("Martes 8:00-10:00");
        schedule2.add("Jueves 8:00-10:00");
        schedule2.add("Viernes 10:00-12:00");

        ArrayList<String> schedule3 = new ArrayList<>();
        schedule3.add("Lunes 10:00-12:00");
        schedule3.add("Miercoles 10:00-12:00");
        schedule3.add("Viernes 10:00-12:00");

        Group group1 = new Group(subject1, place1, schedule1);
        Group group2 = new Group(subject2, place2, schedule2);
        Group group3 = new Group(subject3, place3, schedule3);

        groups.add(group1);
        groups.add(group2);
        groups.add(group3);

        return groups;

    }

    public List<Group> getGroups() throws ProjectException{
        return groups;
    }

    public void add(Group group) throws ProjectException {
        groups.add(group);
    }

    private boolean compareGroups(Group group1, Group group2) {
        return group1.getPlaceCode().equals(group2.getPlaceCode()) && group1.getSubjectCode().equals(group2.getSubjectCode()) && compareSchedule(group1, group2);
    }

    private boolean compareSchedule(Group group1, Group group2) {
        boolean out = false;
        for (String schedule1 : group1.getSchedule()) {
            for (String schedule2 : group2.getSchedule()) {
                if (schedule1.equals(schedule2)) {
                    out = true;
                }
            }
        }
        return out;
    }
    public String deleteGroup(Group groupToDelete) throws ProjectException {
        String out = "No se ha eliminado ningun grupo";
        List<Group> listAux = new ArrayList<>();
        for (Group group : groups) {
            if (!compareGroups(group,groupToDelete)) {
                listAux.add(group);
            } else {
                out = "Se ha eliminado el grupo";
            }
        }
        this.groups = listAux;
        return out;
    }

    public List<GroupDto> getGroupsDto() throws ProjectException {
        return GroupDto.fromGroupList(groups);
    }

    public String modifyGroup(String placeCode, String subjectCode, ArrayList<String> newSchedule) throws ProjectException {
        String out = "Ningun grupo ha sido modificada";
        for (Group group : groups) {
            if (group.getSubjectCode().equals(subjectCode) && group.getPlaceCode().equals(placeCode)) {
                    group.setSchedule(newSchedule);
                    out = "Se ha modificado el grupo";
            }
        }
        return out;
    }
}
