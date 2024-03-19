package co.edu.uptc.P_projectSubjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Group {
    private String subjectCode;
    private String placeCode;
    private ArrayList<String> schedule;
}
