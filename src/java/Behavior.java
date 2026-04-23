/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sintia Dwi
 */
public class Behavior {
   private int behavior_id;
   private String behavior_code;
   private String daily_activity;
   private String diet;
   private String social_behavior;
   private String mating_season;
   
   public Behavior(int behavior_id, String behavior_code, String daily_activity, String diet, 
           String social_behavior, String mating_season){
       this.behavior_id = behavior_id;
       this.behavior_code = behavior_code;
       this.daily_activity = daily_activity;
       this.diet = diet;
       this.social_behavior = social_behavior;
       this.mating_season = mating_season;
   }
   
   public int getID() {
        return behavior_id;
    }
   public String getBheaviorCode() {
        return behavior_code;
    }
   public String getActivity() {
        return daily_activity;
    }
   public String getDiet() {
        return diet;
    }
   public String getSocial() {
        return social_behavior;
    }
   public String getMating() {
        return mating_season;
    }
}

