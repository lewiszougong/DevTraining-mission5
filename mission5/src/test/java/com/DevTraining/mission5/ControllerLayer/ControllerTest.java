package com.DevTraining.mission5.ControllerLayer;

import com.DevTraining.mission5.Controller.Controller;
import com.DevTraining.mission5.Model.Pet;
import com.DevTraining.mission5.Model.PutPetRequest;
import com.DevTraining.mission5.Service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
//@WebMvcTest  //it will autoconfigure the spring mvc infrastructure for our unit tests
public class ControllerTest {


    private MockMvc mvc;

    @InjectMocks
    private Controller controller;
    @Mock
    private PostService postService;

    @Before
    public void setUp(){
        mvc= MockMvcBuilders.standaloneSetup(controller)
                .build();
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testControllerCreate() throws Exception {

        Pet testPet = new Pet(7, "Deer", "Animal", "Male", 3, "null");
        String result = "Created and saved";
        when(postService.savePet(testPet)).thenReturn(result);

        mvc.perform(post("/home")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(asJsonString(testPet)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Created and saved")));

        verify(postService).savePet(testPet);
    }

    @Test
    public void testControllerRead() throws Exception{
        Pet testPet = new Pet(7, "Deer", "Animal", "Male", 3, "null");
        when (postService.readPet(7)).thenReturn(testPet);

        mvc.perform(get("/home/{id}",7)
                //.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                //.characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",Matchers.is(7)))
                .andExpect(jsonPath("$.pet", Matchers.is("Deer")))
                .andExpect(jsonPath("$.type",Matchers.is("Animal")))
                .andExpect(jsonPath("$.gender",Matchers.is("Male")))
                .andExpect(jsonPath("$.age",Matchers.is(3)))
                .andExpect(jsonPath("$.isFavourite",Matchers.is("null")));
                //.andExpect(content().string(containsString(asJsonString(testPet))));

        verify(postService).readPet(7);
    }

    @Test
    public void testControllerUpdate() throws Exception{
        PutPetRequest testPet = new PutPetRequest("Deer", "Animal", "Male", 3, "false");
        when (postService.updatePet(7,testPet)).thenReturn("Updated");

        mvc.perform(put("/home/{id}",7)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(asJsonString(testPet)))
                .andDo(print())
                .andExpect(status().isOk())
        .andExpect(content().string(containsString("Updated")));

        verify(postService).updatePet(7, testPet);
    }

    @Test
    public void testControllerDelete() throws Exception{
        mvc.perform( MockMvcRequestBuilders.delete("/home/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
