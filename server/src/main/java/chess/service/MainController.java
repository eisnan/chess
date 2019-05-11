package chess.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @PostMapping("/start")
    public ResponseEntity<Long> start(@RequestParam(name = "name") String name) {
        System.out.println(name);
        return ResponseEntity.ok(1L);
    }



}
