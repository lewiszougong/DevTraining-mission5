package com.DevTraining.mission5.ServiceLayer;


import com.DevTraining.mission5.Model.Pet;
import com.DevTraining.mission5.Model.PutPetRequest;
import com.DevTraining.mission5.Repository.RepoImpl;
import com.DevTraining.mission5.Service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private RepoImpl repo;

    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testServiceCreate(){
        Pet testPet= new Pet( 7,"Monkey", "Animal", "Female", 2,"null");
        when(repo.savePet(testPet)).thenReturn("Created and saved");
        String status =postService.savePet(testPet);
        assertEquals("Created and saved", status);
    }

    @Test
    public void testRepoRead(){
        Pet testPet= new Pet( 6,"Duck", "Animal", "Female", 1,"null");
        when(repo.readPet(6)).thenReturn(testPet);
        Pet result=postService.readPet(6);

        assertEquals("Duck", result.getPet());
        assertEquals("Animal",result.getType());
        assertEquals("Female",result.getGender());
        assertEquals(Integer.valueOf(1),result.getAge());
    }

    @Test
    public void testRepoUpdate(){
        PutPetRequest testPet= new PutPetRequest("Wolf","Animal","Male",8,"false");
        when(repo.updatePet(6,testPet)).thenReturn("Updated");
        String status= postService.updatePet(6, testPet);
        assertEquals("Updated", status);
    }
    @Test
    public void testRepoDelete(){
        when(repo.deletePet(6)).thenReturn("Deleted");
        String status=postService.deletePet(6);
        assertEquals("Deleted", status);
    }

}
