package co.edu.uptc.P_projectSubjects.dtos;

import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.exceptions.TypeMessage;
import co.edu.uptc.P_projectSubjects.models.Place;
import co.edu.uptc.services.dynamic.UptcList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceDto {
    private String name;
    private String placeCode;
    private String location;

    public static PlaceDto fromPlace(Place place) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setName(place.getName());
        placeDto.setPlaceCode(place.getPlaceCode());
        placeDto.setLocation(place.getLocation());
        return placeDto;
    }

    public static List<PlaceDto> fromPlaceList(List<Place> places) {
        List<PlaceDto> placeDtos = new UptcList<>();
        for (Place place : places) {
            placeDtos.add(fromPlace(place));
        }
        return placeDtos;
    }

    public static void validatePlace(PlaceDto placeDto) throws ProjectException {
        if (placeDto.getName() == null ||
                placeDto.getPlaceCode() == null ||
                placeDto.getLocation() == null) {
            throw new ProjectException(TypeMessage.INFORMATION_INCOMPLETE);
        }
    }

    public static Place toPlace(PlaceDto placeDto) {
        Place place = new Place();
        place.setPlaceCode(placeDto.getPlaceCode());
        place.setName(placeDto.getName());
        place.setLocation(placeDto.getLocation());
        return place;
    }
}
