package com.DevTraining.mission5.Service;

import com.DevTraining.mission5.Model.*;
import com.DevTraining.mission5.Repository.RepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService {
    @Autowired
    private RepoImpl repo;

    public String savePet(Pet postPetRequest)  {
        return repo.savePet(postPetRequest);
    }

    public Pet readPet(int ID) {
        return repo.readPet(ID);
    }

    public String updatePet(int ID, PutPetRequest putPetRequest) {

        return repo.updatePet(ID,putPetRequest);
   }
    public String deletePet(int ID) {
        return repo.deletePet(ID);
    }

}
