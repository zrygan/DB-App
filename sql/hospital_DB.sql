CREATE DATABASE IF NOT EXISTS Hospital_DB;

USE Hospital_DB;

CREATE TABLE IF NOT EXISTS patients_record (
    patient_ID INT PRIMARY KEY AUTO_INCREMENT,
    patient_name VARCHAR(100)    NOT NULL,
    age INT              NOT NULL,
    birth_date DATE      NOT NULL,
    sex ENUM('M', 'F')   NOT NULL,
    patient_height DECIMAL(5, 2) NOT NULL,         -- Height in centimeters or inches
    patient_weight DECIMAL(5, 2) NOT NULL,         -- Weight in kilograms or pounds
    religion VARCHAR(50) NOT NULL,
    doctor INT FOREIGN KEY NOT NULL REFERENCES doctors_record(doctor_ID),
    date_created DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS doctors_record (
    doctor_ID INT PRIMARY KEY,
    doctor_name VARCHAR(100)      NOT NULL,
    specialization VARCHAR(100)   NOT NULL,
    phoneNumber VARCHAR(100)      NOT NULL,
    email VARCHAR(100)            NOT NULL,
    medicalHierarchy VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS consultation_record(
    consultation_ID INT PRIMARY KEY,
    prescription_ID INT FOREIGN KEY REFERENCES prescription_record(prescription_ID),
    doctor_ID INT FOREIGN KEY REFERENCES doctors_record(doctor_ID),
    patient_ID INT FOREIGN KEY REFERENCES patients_record(patient_ID),
    vital_signs_ID INT FOREIGN KEY REFERENCES vital_signs_record(vital_signs_ID),
    lab_report_ID INT FOREIGN KEY REFERENCES lab_report_record(lab_report_ID),
    consultation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS medication_record(
    medication_ID INT PRIMARY KEY,
    generic_name VARCHAR(100),
    brand_name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS prescription_record(
    prescription_ID INT PRIMARY KEY,
    medication_ID INT FOREIGN KEY REFERENCES medication_record(medication_ID),
    prescription_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    frequency INT,
    dosage DECIMAL(5,5),                         -- in grams
    doctor_ID INT FOREIGN KEY REFERENCES doctors_record(doctor_ID),
    patient_ID INT FOREIGN KEY REFERENCES patients_record(patient_ID)
);

CREATE TABLE IF NOT EXISTS vital_signs_record(
    vital_signs_ID INT PRIMARY KEY,
    temperature DECIMAL(2,2),                    -- temperature cannot exceed 3 digits (unrealistic)
    pulse INT,
    respiratory_rate INT,
    systolic_bp INT,
    diastolic_bp INT,
    SPO2 INT,
    vital_signs_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS lab_report_record(
    lab_report_ID INT PRIMARY KEY,
    test_ID INT FOREIGN KEY
);

CREATE TABLE IF NOT EXISTS lab_test_record(
    test_ID INT PRIMARY KEY,
    test_description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS consultation_chief_complaint_record(
    consultation_ID INT PRIMARY KEY,
    complaint_ID INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS chief_complaint_record(
    complaint_ID INT PRIMARY KEY,
    complaint_description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS consultation_medical_diagnosis_record(
    consultation_ID INT PRIMARY KEY,
    diagnosis_ID INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS medical_diagnosis_record(
    diagnosis_ID INT PRIMARY KEY,
    diagnosis_description VARCHAR(255)
);