package com.DevTraining.mission5.Repository;

import com.DevTraining.mission5.Exception.PetNotFoundException;
import com.DevTraining.mission5.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RepoImpl implements Repo{
    @Autowired
    private  JdbcTemplate jdbcTemplate;
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public String savePet(Pet postPetRequest)  {
        jdbcTemplate.update("INSERT INTO TBL_PET(id,pet,type,gender, age, isFavourite) values(?,?,?,?,?,?)"
                ,postPetRequest.getId(),postPetRequest.getPet(), postPetRequest.getType(), postPetRequest.getGender(), postPetRequest.getAge(), "Null");
        return "Created and saved";
    }

    public static class PetRowMapper implements RowMapper<Pet>{
        @Override
        public Pet mapRow(ResultSet resultSet, int i) throws SQLException {
            Pet pet=new Pet();
            pet.setId(resultSet.getInt("id"));
            pet.setPet(resultSet.getString("pet"));
            pet.setType(resultSet.getString("type"));
            pet.setGender(resultSet.getString("gender"));
            pet.setAge(resultSet.getInt("age"));
            pet.setIsFavourite(resultSet.getString("isFavourite"));
            return pet;
        }
    }
    public Pet readPet(int ID) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM TBL_PET WHERE id=?", new Object[]{ID}, new PetRowMapper());

        }catch (Exception e){
            throw new PetNotFoundException("No Pet");
        }
    }

    public String updatePet(int ID, PutPetRequest putPetRequest) {
        String sql = "UPDATE TBL_PET SET pet =?, type =?, gender=?, age=?, isFavourite=? WHERE id =?";
        jdbcTemplate.update(sql,putPetRequest.getPet(), putPetRequest.getType(), putPetRequest.getGender(), putPetRequest.getAge(), putPetRequest.getIsFavourite(),ID);

        return"Updated";
    }
    public String deletePet(int ID){

        String sql = "DELETE FROM TBL_PET WHERE id=?";
        jdbcTemplate.update(sql, ID);
        return "Deleted";

    }

}

