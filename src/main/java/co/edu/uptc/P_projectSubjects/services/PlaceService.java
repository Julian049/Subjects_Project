package co.edu.uptc.P_projectSubjects.services;

import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.exceptions.TypeMessage;
import co.edu.uptc.P_projectSubjects.models.Group;
import co.edu.uptc.P_projectSubjects.models.Place;
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
        if (!verifyPLaceAlreadyExist(place)) {
            places.add(place);
        }else{
            throw new ProjectException(TypeMessage.ALREADY_EXISTS);
        }
    }

    private boolean verifyPLaceAlreadyExist(Place place) {
        boolean noExists = false;
        for (Place place1 : places) {
            if (place1.getPlaceCode().equals(place.getPlaceCode()) || place1.getLocation().equals(place.getLocation()) || place1.getName().equals(place.getName())) {
                noExists = true;
                break;
            }
        }
        return noExists;
    }

    public Place deletePlace(String code) throws ProjectException{
        List<Place> listAux = new UptcList<>();
        Place placeDelete = null;
        for (Place place : places){
            if (!place.getPlaceCode().equals(code)){
                listAux.add(place);
            }else{
                placeDelete = place;
            }
        }
        this.places = listAux;
        if (placeDelete == null) throw new ProjectException(TypeMessage.NOT_FOUND);
        return placeDelete;
    }

    public List<Place> getPlaces() throws ProjectException {
        if (places.size() == 0) throw new ProjectException(TypeMessage.NO_ITEMS);
        return places;
    }

    public Place modifyPlace(String code, Place newPlace) throws ProjectException{
        Place modifiedPlace = null;
        for (Place place : places ){
            if (place.getPlaceCode().equals(code)){
                modifiedPlace = place;
                place.setPlaceCode(newPlace.getPlaceCode());
                place.setName(newPlace.getName());
                place.setLocation(newPlace.getLocation());
            }
        }
        if (modifiedPlace == null) throw new ProjectException(TypeMessage.NOT_FOUND);
        return modifiedPlace;
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
            throw new ProjectException(TypeMessage.NOT_FOUND);
        }
        return null;
    }
}
