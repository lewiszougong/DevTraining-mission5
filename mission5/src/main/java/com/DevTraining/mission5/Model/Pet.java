package com.DevTraining.mission5.Model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Builder
@Entity
public class Pet {
    @Id
    private Integer id;
    private String pet;
    private String type;
    private String gender;
    private Integer age;
    private String isFavourite;

    public Pet(Integer id, String pet, String type, String gender, Integer age, String isFavourite){
        this.id=id;
        this.pet=pet;
        this.type=type;
        this.gender=gender;
        this.age=age;
        this.isFavourite=isFavourite;

    }

    @Override
    public String toString() {
        return "Config{ Id=" +id+
                ", Pet='" + pet + '\'' +
                ", Type='" + type + '\'' +
                ", Gender='" + gender + '\'' +
                ", Age='" + age + '\'' +
                '}';
    }
}

