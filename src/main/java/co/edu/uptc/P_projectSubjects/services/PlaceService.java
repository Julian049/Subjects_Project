package co.edu.uptc.P_projectSubjects.services;

import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.models.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceService {

    List<Place> places = new ArrayList<>();

    public PlaceService() {
        this.places = loadPlaces();
    }

    private List<Place> loadPlaces() {
        List<Place> places = new ArrayList<>();

        Place place1 = new Place("Salon 1","1111","Edificio 1");
        Place place2 = new Place("Salon 2","2222","Edificio 2");
        Place place3 = new Place("Salon 3","333","Edificio 3");

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
        List<Place> listAux = new ArrayList<>();
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


}
