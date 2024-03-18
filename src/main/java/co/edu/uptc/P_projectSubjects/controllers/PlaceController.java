package co.edu.uptc.P_projectSubjects.controllers;

import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.models.Place;
import co.edu.uptc.P_projectSubjects.models.Subject;
import co.edu.uptc.P_projectSubjects.services.PlaceService;
import co.edu.uptc.P_projectSubjects.services.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
public class PlaceController {

    PlaceService service = new PlaceService();

    @PostMapping()
    public ResponseEntity<Object> postPlace(@RequestBody Place place) {
        try {
            service.add(place);
            return ResponseEntity.status(HttpStatus.OK).body("Place added" + place.toString());
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getPlaces() {
        List<Place> places;
        try {
            places = service.getPlaces();
            return ResponseEntity.status(HttpStatus.OK).body(places);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @GetMapping("/delete/{placeCode}")
    public ResponseEntity<Object> deletePlace(@PathVariable String placeCode) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.deletePlace(placeCode));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @PostMapping("/modify/{placeCode}")
    public ResponseEntity<Object> modifyPlace(@PathVariable String placeCode, @RequestBody Place place) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.modifyPlace(placeCode, place));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }
}
