/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sintia Dwi
 */
public class Data {
    private int user_id;
    private String full_name;
    private String email;
    private Object role;
    private int phone_number;
    private String photo_url;
    private Object status_user;
    private String pass;
    
    public Data (int user_id, String full_name, String email, Object role, 
            int phone_number, String photo_url, Object status_user, String pass){
      this.user_id = user_id;
      this.full_name = full_name;
      this.email = email;
      this.role = role;
      this.phone_number = phone_number;
      this.photo_url = photo_url;
      this.status_user = status_user;
      this.pass = pass;
    }
    
    public int getUserID(){
        return this.user_id;
    }
    public String getFullName(){
        return this.full_name;
    }
    public String getEmail(){
        return this.email;
    }
    public Object getRole(){
        return this.role;
    }
    public int getPhone(){
        return this.phone_number;
    }
    public String getPhoto(){
        return this.photo_url;
    }
    public Object getStatus(){
        return this.status_user;
    }
    public String getPass(){
        return this.pass;
    }
    
    
}
