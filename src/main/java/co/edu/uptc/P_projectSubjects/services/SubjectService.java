package co.edu.uptc.P_projectSubjects.services;

import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.exceptions.TypeMessage;
import co.edu.uptc.P_projectSubjects.models.Place;
import co.edu.uptc.P_projectSubjects.models.Subject;
import co.edu.uptc.services.dynamic.UptcList;

import java.util.List;

public class SubjectService {

    List<Subject> subjects = new UptcList<>();

    public SubjectService() {
        this.subjects = loadSubjects();
    }

    private List<Subject> loadSubjects() {
        List<Subject> subjects = new UptcList<>();

        Subject subject1 = new Subject("Calculo III", "8108213");
        Subject subject2 = new Subject("Fisica II", "8108214");
        Subject subject3 = new Subject("Programacion III", "8108257");

        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);

        return subjects;
    }

    public void add(Subject subject) throws ProjectException {
        if (!verifySubjectAlreadyExist(subject)) {
            subjects.add(subject);
        }else{
            throw new ProjectException(TypeMessage.ALREADY_EXISTS);
        }
    }

    private boolean verifySubjectAlreadyExist(Subject subject) {
        boolean noExists = false;
        for (Subject subject1 : subjects) {
            if (subject1.getName().equals(subject.getName()) || subject1.getSubjectCode().equals(subject.getSubjectCode())) {
                noExists = true;
                break;
            }
        }
        return noExists;
    }

    public Subject deleteSubject(String code) throws ProjectException {
        List<Subject> listAux = new UptcList<>();
        Subject subjectDelete = null;
        for (Subject subject : subjects) {
            if (!subject.getSubjectCode().equals(code)) {
                listAux.add(subject);
            } else {
                subjectDelete = subject;
            }
        }
        this.subjects = listAux;
        if (subjectDelete == null) throw new ProjectException(TypeMessage.NOT_FOUND);
        return subjectDelete;
    }

    public List<Subject> getSubjects() throws ProjectException {
        if (subjects.size() == 0) throw new ProjectException(TypeMessage.NO_ITEMS);
        return subjects;
    }

    public Subject modifySubject(String code, Subject newSubject) throws ProjectException {
        Subject modifiedSubject = null;
        for (Subject subject : subjects) {
            if (subject.getSubjectCode().equals(code)) {
                modifiedSubject = subject;
                subject.setSubjectCode(newSubject.getSubjectCode());
                subject.setName(newSubject.getName());
            }
        }
        if (modifiedSubject == null) throw new ProjectException(TypeMessage.NOT_FOUND);
        return modifiedSubject;
    }

    public Subject getSubjectByCode(String code) throws ProjectException {
        try {
            for (Subject subject : subjects) {
                Subject newSubject = new Subject();
                newSubject.setSubjectCode(subject.getSubjectCode());
                newSubject.setName(subject.getName());
                if (newSubject.getSubjectCode().equals(code)) {
                    return newSubject;
                }
            }
        } catch (Exception e) {
            throw new ProjectException(TypeMessage.NOT_FOUND);
        }
        return null;
    }

}