CREATE DATABASE IF NOT EXISTS Hospital_DB;

USE Hospital_DB;

CREATE TABLE IF NOT EXISTS patients_record (
    patient_ID INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT,
    birth_date DATE,
    sex ENUM('MALE', 'FEMALE'),
    height DECIMAL(5, 2),         -- Height in centimeters or inches
    weight DECIMAL(5, 2),         -- Weight in kilograms or pounds
    religion VARCHAR(50),
    doctor VARCHAR(100),
    status ENUM('ADMITTED', 'DISCHARGED'),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS doctors_record (
    doctor_ID VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100),
    specialization VARCHAR(100),
    phoneNumber VARCHAR(100),
    email VARCHAR(100),
    medicalHierarchy VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS medication_record (
    medication_ID INT PRIMARY KEY AUTO_INCREMENT,
    doctor_ID VARCHAR(100),
    patient_ID INT,
    generic_name VARCHAR(100),
    brand_name VARCHAR(100),
    date_time DATETIME,
    frequency VARCHAR(100), -- e.g., every day, every week, every 8 hours
    dosage FLOAT, -- in milliliters (e.g. 10 = 10 mL)
    FOREIGN KEY (doctor_ID) REFERENCES doctors_record(doctor_ID),
    FOREIGN KEY (patient_ID) REFERENCES patients_record(patient_ID)
);

CREATE TABLE IF NOT EXISTS medical_record (
    record_ID INT PRIMARY KEY AUTO_INCREMENT,
    patient_ID INT,
    chief_complaint VARCHAR(255),
    reason_for_admission VARCHAR(100),
    medical_diagnosis VARCHAR(100),
    temperature DECIMAL(3,2), -- temperature of patient (e.g., 37.00)
    pulse INT,
    BP_systolic INT,
    BP_diastolic INT,
    spo2 INT,
    date_time DATETIME,
    FOREIGN KEY (patient_ID) REFERENCES patients_record(patient_ID)
);
