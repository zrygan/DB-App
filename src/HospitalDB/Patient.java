package HospitalDB;

/*
 (Name, Age, Birth date, Sex, Height, Weight, Religion, Doctor, Status)

Viewing the Patient Record for a specific patient.
Viewing the summary Patient Record for all patients (e.g., the number of male patients, average height of all patients).
Viewing all the Patient Records for patients with the same status (e.g., admitted, discharged).
 */


 /*
  * Patient Record
  */
public class Patient {
    private String name;
    private int age;
    private int birthM;
    private int birthD;
    private int birthY;
    private char sex;
    private int height;
    private int weight;
    private String religion;
    private String doctor;
    private String status;

    public Patient(String name, String religion, String doctor, String status, int age, int birthM, int birthD, int birthY, char sex, int height, int weight){
        this.name = name;
        this.age = age;
        this.birthM = birthM;
        this.birthD = birthD;
        this. birthY = birthY;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.religion = religion;
        this.doctor = doctor;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name;
    }

    public int getAge() {
        return age;
    }

    public int setAge(int age) {
        return this.age;
    }

    public int getbirthM() {
        return birthM;
    }

    public int setBirthM(int birthM) {
        return this.birthM;
    }

    public int getbirthD() {
        return birthD;
    }

    public int setBirthD(int birthD) {
        return this.birthD;
    }

    public int getbirthY() {
        return birthY;
    }

    public int setBirthY(int birthY) {
        return this.birthY;
    }

    public char getSex() {
        return sex;
    }

    public char setSex(char sex) {
        return this.sex;
    }

    public int getHeight() {
        return height;
    }

    public int setHeight(int height) {
        return this.height;
    }

    public int getWeight() {
        return weight;
    }

    public int setWeight(int weight) {
        return this.weight;
    }

    public String getReligion() {
        return religion;
    }

    public String setReligion(String religion) {
        return this.religion;
    }

    public String getDoctor() {
        return doctor;
    }

    public String setDoctor(String doctor) {
        return this.doctor;
    }

    public String getStatus() {
        return status;
    }

    public String setStatus(String status) {
        return this.status;
    }
}
 