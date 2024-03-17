package co.edu.uptc.P_projectSubjects.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Group {
    private Subject subject;
    private Place place;
    private String schedule;
}
