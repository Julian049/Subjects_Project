package co.edu.uptc.P_projectSubjects.services;

import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.exceptions.TypeMessage;
import co.edu.uptc.P_projectSubjects.models.Place;
import co.edu.uptc.P_projectSubjects.models.Subject;
import co.edu.uptc.services.dynamic.UptcList;

import java.util.List;

public class PlaceService {

    List<Place> places = new UptcList<>();

    public PlaceService() {
        this.places = loadPlaces();
    }

    private List<Place> loadPlaces() {
        List<Place> places = new UptcList<>();

        Place place1 = new Place("Salon 1","1111","Edificio 1");
        Place place2 = new Place("Salon 2","2222","Edificio 2");
        Place place3 = new Place("Salon 3","3333","Edificio 3");

        places.add(place1);
        places.add(place2);
        places.add(place3);

        return places;

    }

    public void add(Place place) throws ProjectException {
        places.add(place);
    }

    public String deletePlace(String code) throws ProjectException{
        String out = "No se ha eliminado ningun lugar";
        List<Place> listAux = new UptcList<>();
        for (Place place : places){
            if (!place.getPlaceCode().equals(code)){
                listAux.add(place);
            }else{
                out = "Se ha eliminado el lugar";
            }
        }
        this.places = listAux;
        return out;
    }

    public List<Place> getPlaces() throws ProjectException {
        return places;
    }

    public String modifyPlace(String code, Place newPlace) throws ProjectException{
        String out = "No se ha modificado ningun lugar";
        for (Place place : places ){
            if (place.getPlaceCode().equals(code)){
                place.setPlaceCode(newPlace.getPlaceCode());
                place.setName(newPlace.getName());
                place.setLocation(newPlace.getLocation());
                out = "Se ha modificado el lugar";
            }
        }
        return out;
    }

    public Place getPlaceByCode(String code) throws ProjectException{
        try{
            for (Place place : places){
                Place newPlace = new Place();
                newPlace.setLocation(place.getLocation());
                newPlace.setName(place.getName());
                newPlace.setPlaceCode(place.getPlaceCode());
                if (newPlace.getPlaceCode().equals(code)){
                    return newPlace;
                }
            }
        } catch (Exception e) {
            throw new ProjectException(TypeMessage.NOT_FOUND_FILE);
        }
        return null;
    }
}
