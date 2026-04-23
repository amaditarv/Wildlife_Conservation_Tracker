import java.sql.Date;
import java.sql.Timestamp;

public class animalProfile {
    // From animal_details
    private int detail_id;
    private double height;
    private double length;
    private String color;
    private String distinguishing_features;
    private String province;

    // From animal_observations
    private int observation_id;
    private String observation_code;
    private Timestamp observation_time;
    private String location;
    private String animal_condition;
    private String notes;

    // From animals table (main data)
    private int animal_id;
    private String kode;
    private String name;
    private String species;
    private String gender;
    private Date date_of_birth;
    private int estimated_age;
    private double weight;
    private String status;
    private String photo_url;

    // ====================== GETTER & SETTER =======================

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDistinguishing_features() {
        return distinguishing_features;
    }

    public void setDistinguishing_features(String distinguishing_features) {
        this.distinguishing_features = distinguishing_features;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getObservation_id() {
        return observation_id;
    }

    public void setObservation_id(int observation_id) {
        this.observation_id = observation_id;
    }

    public String getObservation_code() {
        return observation_code;
    }

    public void setObservation_code(String observation_code) {
        this.observation_code = observation_code;
    }

    public Timestamp getObservation_time() {
        return observation_time;
    }

    public void setObservation_time(Timestamp observation_time) {
        this.observation_time = observation_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAnimal_condition() {
        return animal_condition;
    }

    public void setAnimal_condition(String animal_condition) {
        this.animal_condition = animal_condition;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getEstimated_age() {
        return estimated_age;
    }

    public void setEstimated_age(int estimated_age) {
        this.estimated_age = estimated_age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
