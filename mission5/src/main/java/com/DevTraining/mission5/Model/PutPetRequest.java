package com.DevTraining.mission5.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PutPetRequest {
    String pet;
    String type;
    String gender;
    Integer age;
    String isFavourite;

}
