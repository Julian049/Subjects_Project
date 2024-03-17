package co.edu.uptc.P_projectSubjects.services;

import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.models.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectService {

    List<Subject> subjects = new ArrayList<>();

    public SubjectService() {
        this.subjects = loadSubjects();
    }

    private List<Subject> loadSubjects() {
        List<Subject> subjects = new ArrayList<>();

        Subject subject1 = new Subject("Calculo III", "8108213");
        Subject subject2 = new Subject("Fisica II", "8108214");
        Subject subject3 = new Subject("Programacion III", "8108257");

        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);

        return subjects;

    }

    public void add(Subject subject) throws ProjectException{
        subjects.add(subject);
    }

    public String deleteSubject(String code) throws ProjectException{
        String out = "No se ha eliminado ninguna materia";
        List<Subject> listAux = new ArrayList<>();
        for (Subject subject : subjects){
            if (!subject.getSubjectCode().equals(code)){
                listAux.add(subject);
            }else{
                out = "Se ha eliminado la materia";
            }
        }
        this.subjects = listAux;
        return out;
    }

    public List<Subject> getSubjects() throws ProjectException {
        return subjects;
    }

    public String modifyNameSubject(String code, Subject newSubject) throws ProjectException{
        String out = "Ninguna materia ha sido modificada";
        for (Subject subject : subjects ){
            if (subject.getSubjectCode().equals(code)){
                subject.setSubjectCode(newSubject.getSubjectCode());
                subject.setName(newSubject.getName());
                out = "Se ha modificado la materia";
            }
        }
        return out;
    }


}