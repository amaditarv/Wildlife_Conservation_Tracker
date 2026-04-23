/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amadita
 */

import java.sql.Date;

public class Animal {
   private String kode;
   private int animal_id;
   private String name;
   private String species;
   private String gender;
   private Date date_of_birth;
   private int estimated_age;
   private double weight;
   private String status;
   private String photo_url;
  
   public Animal (String kode, int animal_id, String name, String species,String gender,Date date_of_birth,
           int estimated_age,double weight,String status,String photo_url){
    this.kode = kode;
    this.animal_id = animal_id;
    this.name = name;
    this.species = species;
    this.gender = gender;
    this.date_of_birth = date_of_birth;
    this.estimated_age = estimated_age;
    this.weight = weight;
    this.status = status;
    this.photo_url = photo_url;
}
   
   public String getKode() {
        return kode;
    }
    public int getID() {
        return animal_id;
    }
   
   public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public Date getDate() {
        return date_of_birth;
    }

    public int getAge() {
        return estimated_age;
    }

    public double getWeight() {
        return weight;
    }

    public String getStatus() {
        return status;
    }

    public String getPhoto_url() {
        return photo_url;
    }
   
   
}
