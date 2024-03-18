package co.edu.uptc.P_projectSubjects.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Group {
    private Subject subject;
    private Place place;
    private ArrayList<String> schedule;
}
