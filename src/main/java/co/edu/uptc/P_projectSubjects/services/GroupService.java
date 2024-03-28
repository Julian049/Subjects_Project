package co.edu.uptc.P_projectSubjects.services;

import co.edu.uptc.P_projectSubjects.dtos.GroupDto2;
import co.edu.uptc.P_projectSubjects.exceptions.TypeMessage;
import co.edu.uptc.P_projectSubjects.models.Group;
import co.edu.uptc.P_projectSubjects.models.Subject;
import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.services.dynamic.UptcList;

import java.util.List;

public class GroupService {

    List<Group> groups = new UptcList<>();

    public GroupService() {
        this.groups = loadGroups();
    }

    private List<Group> loadGroups() {
        List<Group> groups = new UptcList<>();

        String subject1 = "8108213";
        String subject2 = "8108214";
        String subject3 = "8108257";

        String place1 = "1111";
        String place2 = "1111";
        String place3 = "3333";


        UptcList<String> schedule1 = new UptcList<>();
        schedule1.add("Lunes 8:00-10:00");
        schedule1.add("Miercoles 8:00-10:00");
        schedule1.add("Viernes 8:00-10:00");

        UptcList<String> schedule2 = new UptcList<>();
        schedule2.add("Martes 8:00-10:00");
        schedule2.add("Jueves 8:00-10:00");
        schedule2.add("Viernes 10:00-12:00");

        UptcList<String> schedule3 = new UptcList<>();
        schedule3.add("Lunes 10:00-12:00");
        schedule3.add("Miercoles 10:00-12:00");
        schedule3.add("Viernes 10:00-12:00");

        Group group1 = new Group(subject1, place1, schedule1);
        Group group2 = new Group(subject2, place2, schedule1);
        Group group3 = new Group(subject3, place3, schedule3);

        groups.add(group1);
        groups.add(group2);
        groups.add(group3);

        return groups;

    }

    public List<Group> getGroups() throws ProjectException {
        if (groups.size() == 0) throw new ProjectException(TypeMessage.NO_ITEMS);
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
                    break;
                }
            }
        }
        return out;
    }

    public Group deleteGroup(Group groupToDelete) throws ProjectException {
        List<Group> listAux = new UptcList<>();
        Group groupDelete = null;
        for (Group group : groups) {
            if (!compareGroups(group, groupToDelete)) {
                listAux.add(group);
            } else {
                groupDelete = group;
            }
        }
        this.groups = listAux;
        if (groupDelete == null) throw new ProjectException(TypeMessage.NOT_FOUND);
        return groupDelete;
    }

    public List<GroupDto2> getGroupsDto() throws ProjectException {
        return GroupDto2.fromGroupList(groups);
    }

    public Group modifyGroup(String placeCode, String subjectCode, Group newGroup) throws ProjectException {
        Group modifiedGroup = null;
        for (Group group : groups) {
            if (group.getSubjectCode().equals(subjectCode) && group.getPlaceCode().equals(placeCode)) {
                modifiedGroup = group;
                group.setSchedule(newGroup.getSchedule());
                group.setSubjectCode(newGroup.getSubjectCode());
                group.setPlaceCode(newGroup.getPlaceCode());
            }
        }
        return modifiedGroup;
    }

    public List<Subject> getSubjectsWithSamePlace(String placeCode) throws ProjectException {
        List<Subject> subjects = new UptcList<>();
        for (GroupDto2 group : this.getGroupsDto()) {
            if (group.getPlace().getPlaceCode().equals(placeCode)) {
                subjects.add(group.getSubject());
            }
        }
        return subjects;
    }

    public List<Subject> getSubjectsWithMoreGroup(List<Subject> subjects) throws ProjectException {
        List<Subject> subjectsWithMoreGroup = new UptcList<>();
        for (Subject subject : subjects) {
            int count = 0;
            for (GroupDto2 groupDto2 : this.getGroupsDto()) {
                if (compareSubjects(subject, groupDto2.getSubject())){
                    count++;
                }
            }
            if (count >= 2){
                subjectsWithMoreGroup.add(subject);
            }
        }
        return subjectsWithMoreGroup;
    }

    private boolean compareSubjects(Subject subject1,Subject subject2){
        return subject1.getSubjectCode().equals(subject2.getSubjectCode()) && subject1.getName().equals(subject2.getName());
    }

    public List<Subject> getSubjectsWithSameSchedule(String schedule) throws ProjectException {
        List<Subject> subjects = new UptcList<>();
        for (GroupDto2 group : this.getGroupsDto()) {
            for (String eachSchedule : group.getSchedule()){
                if (eachSchedule.equals(schedule)){
                    subjects.add(group.getSubject());
                }
            }
        }
        return subjects;
    }
}
