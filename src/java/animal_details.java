/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amadita
 */
public class animal_details {
    private int detail_id;
    private double height;
    private double length;
    private String color;
    private String distinguishing_features;
    private String province;
    
    public animal_details(int detail_id,double height, double length, String color, String distinguishing_features, String province){
        this. detail_id = detail_id;
        this. height = height;
        this. length = length;
        this. color = color;
        this. distinguishing_features = distinguishing_features;
        this. province = province;
        
    }
    
    public int getID() {
        return detail_id;
    }
    public double getHeight() {
        return height;
    }
    public double getLength() {
        return length;
    }
    public String getColor() {
        return color;
    }
    public String getFeatures() {
        return distinguishing_features;
    }
    public String getProvince() {
        return province;
    }
    
}
