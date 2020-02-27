package com.DevTraining.mission5.Controller;


import com.DevTraining.mission5.Model.*;
import com.DevTraining.mission5.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class Controller {

    @Autowired
    PostService postService;

    @PostMapping
    public String createResponse(@RequestBody Pet postPetRequest) {
        return postService.savePet(postPetRequest);
    }
    @GetMapping("/{id}")
    public Pet getValue(@PathVariable(value="id") int id) {
            return postService.readPet(id);
    }
    @PutMapping("/{id}")
    public String updateResponse(@PathVariable(value="id") int id, @RequestBody PutPetRequest putPetRequest) {
        return postService.updatePet(id,putPetRequest);
    }
    @DeleteMapping("/{id}")
    public String deleteResponse(@PathVariable(value= "id") int id) {
        return postService.deletePet(id);
    }

}


