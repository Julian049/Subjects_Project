package co.edu.uptc.P_projectSubjects.controllers;

import co.edu.uptc.P_projectSubjects.dtos.PlaceDto;
import co.edu.uptc.P_projectSubjects.exceptions.ProjectException;
import co.edu.uptc.P_projectSubjects.models.Place;
import co.edu.uptc.P_projectSubjects.services.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
public class PlaceController {

    PlaceService service = new PlaceService();

    @PostMapping()
    public ResponseEntity<Object> postPlace(@RequestBody PlaceDto placeDto) {
        try {
            PlaceDto.validatePlace(placeDto);
            service.add(PlaceDto.toPlace(placeDto));
            return ResponseEntity.status(HttpStatus.OK).body(placeDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getPlaces() {
        List<PlaceDto> placesDto;
        try {
            placesDto = PlaceDto.fromPlaceList(service.getPlaces());
            return ResponseEntity.status(HttpStatus.OK).body(placesDto);
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp())
                    .body(e.getMenssage());
        }
    }

    @DeleteMapping("/delete/{placeCode}")
    public ResponseEntity<Object> deletePlace(@PathVariable String placeCode) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(PlaceDto.fromPlace(service.deletePlace(placeCode)));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }

    @PostMapping("/modify/{placeCode}")
    public ResponseEntity<Object> modifyPlace(@PathVariable String placeCode, @RequestBody Place place) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(PlaceDto.fromPlace(service.modifyPlace(placeCode, place)));
        } catch (ProjectException e) {
            return ResponseEntity.status(e.getMenssage().getCodeHttp()).body(e.getMenssage());
        }
    }
}
