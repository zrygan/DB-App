CREATE DATABASE IF NOT EXISTS hospital_DB;

USE hospital_DB;

CREATE TABLE IF NOT EXISTS patients_record(
	patient_ID INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS doctors_record(
	doctor_ID INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS medication_record(
	medication_ID INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS medical_record(
    record_ID INT PRIMARY KEY,
    patient_ID  INT,
    chief_complaint CHAR(255),
    reason_for_admission CHAR(100),
    medical_diagnosis CHAR(100),
    temperature DECIMAL(2,2), -- temperature of patient cannot exceed 2 places (>=100), unrealistic
    pulse INT,
    BP_systolic INT,
    BP_diastolic INT,
    spo2 INT,
    date_time DATETIME,
    FOREIGN KEY (patient_ID) REFERENCES patients_record(patient_ID)
);