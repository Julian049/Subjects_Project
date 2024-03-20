package co.edu.uptc.P_projectSubjects.models;

import co.edu.uptc.services.dynamic.UptcList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@ToString
public class Group {
    private String subjectCode;
    private String placeCode;
    private UptcList<String> schedule;
}
