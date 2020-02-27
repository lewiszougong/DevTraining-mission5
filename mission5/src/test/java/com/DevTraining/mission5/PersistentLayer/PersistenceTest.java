package com.DevTraining.mission5.PersistentLayer;

import com.DevTraining.mission5.Model.Pet;
import com.DevTraining.mission5.Model.PutPetRequest;
import com.DevTraining.mission5.Repository.RepoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Statement;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)    //what is this
@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class PersistenceTest {
    @Autowired
    private RepoImpl repo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setTestData() {
        String createTbl=("CREATE TABLE TBL_PET(id integer PRIMARY KEY NOT NULL, pet varchar(20) NOT NULL, type varchar(10) NOT NULL, gender varchar(10) NOT NULL, age integer NOT NULL, isFavourite varchar(5) NOT NULL)");
        String query1="INSERT INTO TBL_PET(id, pet, type, gender, age, isFavourite) VALUES (2, 'Rabbit', 'Animal', 'Male', 3, 'null');";
        jdbcTemplate.execute(createTbl);
        jdbcTemplate.execute(query1);
    }
    @After
    public void deleteTestData(){
        String deleteTbl=("DROP TABLE TBL_PET");
        jdbcTemplate.execute(deleteTbl);
    }
    @Test
    public void testRepoCreate() {
        Pet testPet= new Pet( 4,"Monkey", "Animal", "Female", 2,"null");
        String status =repo.savePet(testPet);
        assertEquals("Created and saved", status);
    }
    @Test
    public void testRepoRead(){

        Pet result=repo.readPet(2);
        assertEquals("Rabbit", result.getPet());
        assertEquals("Animal",result.getType());
        assertEquals("Male",result.getGender());
        assertEquals(Integer.valueOf(3),result.getAge());
    }
    @Test
    public void testRepoUpdate(){
        PutPetRequest testPet= new PutPetRequest("Wolf","Animal","Male",8,"false");
        String status= repo.updatePet(2, testPet);
        assertEquals("Updated", status);
        Pet result=repo.readPet(2);
        assertEquals("Wolf", result.getPet());
        assertEquals("Animal",result.getType());
        assertEquals("Male",result.getGender());
        assertEquals(Integer.valueOf(8),result.getAge());
        assertEquals("false",result.getIsFavourite());
    }
    @Test
    public void testRepoDelete(){
        String status=repo.deletePet(2);
        assertEquals("Deleted", status);
    }

}
