/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amadita
 */
import java.sql.Timestamp;
public class animal_observations {
    private int observation_id;
    private String observation_code;
    private Timestamp observation_time;
    private String location;
    private String animal_condition;
    private String zona;
    private String notes;
    
    public animal_observations(int observation_id,String observation_code,Timestamp observation_time,String location,String animal_condition, String zona, String notes){
        this.observation_id = observation_id;
        this.observation_code = observation_code;
        this.observation_time = observation_time;
        this.location = location;
        this.animal_condition = animal_condition;
        this.zona= zona;
        this.notes = notes;
        
    }
    public int getID() {
        return observation_id;
    }
    public String getCode() {
        return observation_code;
    }
    public Timestamp getTime() {
        return observation_time;
    }
    public String getLocation() {
        return location;
    }
    public String getCondition() {
        return animal_condition;
    }
    public String getZona() {
        return zona;
    }
    public String getNotes() {
        return notes;
    }
    
    
    
}
