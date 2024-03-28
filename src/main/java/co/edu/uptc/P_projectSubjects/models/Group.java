package co.edu.uptc.P_projectSubjects.models;

import co.edu.uptc.services.dynamic.UptcList;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Group {
    private String subjectCode;
    private String placeCode;
    private UptcList<String> schedule;
}
