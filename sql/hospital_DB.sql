DROP DATABASE IF EXISTS Hospital_DB;

CREATE DATABASE IF NOT EXISTS Hospital_DB;
USE Hospital_DB;

-- Doctors Record Table
CREATE TABLE IF NOT EXISTS doctors_record (
    doctor_ID INT PRIMARY KEY AUTO_INCREMENT,
    doctor_firstname VARCHAR(100) NOT NULL,
    doctor_lastname VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Patients Record Table
CREATE TABLE IF NOT EXISTS patients_record (
    patient_ID INT PRIMARY KEY AUTO_INCREMENT,
    patient_firstname VARCHAR(100) NOT NULL,
    patient_lastname VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    birth_date DATE NOT NULL,
    sex VARCHAR(5) NOT NULL,
    patient_height DECIMAL(5, 2) NOT NULL,        -- Height in centimeters or inches
    patient_weight DECIMAL(5, 2) NOT NULL,        -- Weight in kilograms or pounds
    religion VARCHAR(50) NOT NULL,
    date_created DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Vital Signs Record Table
CREATE TABLE IF NOT EXISTS vital_signs_record (
    vital_signs_ID INT PRIMARY KEY AUTO_INCREMENT,
    temperature DECIMAL(4, 1),                   -- Adjusted for realistic temperature ranges
    pulse INT,
    respiratory_rate INT,
    systolic_bp INT,
    diastolic_bp INT,
    SPO2 INT,
    vital_signs_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Consultation Record Table
CREATE TABLE IF NOT EXISTS consultation_record (
    consultation_ID INT PRIMARY KEY AUTO_INCREMENT,
    doctor_ID INT NOT NULL,
    patient_ID INT NOT NULL,
    vital_signs_ID INT,
    consultation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (doctor_ID) REFERENCES doctors_record(doctor_ID),
    FOREIGN KEY (patient_ID) REFERENCES patients_record(patient_ID),
    FOREIGN KEY (vital_signs_ID) REFERENCES vital_signs_record(vital_signs_ID)
);

-- Medication Record Table
CREATE TABLE IF NOT EXISTS medication_record (
    medication_ID INT PRIMARY KEY AUTO_INCREMENT,
    generic_name VARCHAR(100) NOT NULL,
    brand_name VARCHAR(100) NOT NULL
);

-- Prescription Record Table
CREATE TABLE IF NOT EXISTS prescription_record (
    prescription_ID INT PRIMARY KEY AUTO_INCREMENT,
    medication_ID INT NOT NULL,
    prescription_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    frequency INT NOT NULL,
    dosage DECIMAL(5, 2) NOT NULL,               -- Dosage in grams
    doctor_ID INT NOT NULL,
    patient_ID INT NOT NULL,
    consultation_ID INT NOT NULL,
    FOREIGN KEY (medication_ID) REFERENCES medication_record(medication_ID),
    FOREIGN KEY (doctor_ID) REFERENCES doctors_record(doctor_ID),
    FOREIGN KEY (patient_ID) REFERENCES patients_record(patient_ID),
    FOREIGN KEY (consultation_ID) REFERENCES consultation_record(consultation_ID)
);

-- Lab Test Record Table
CREATE TABLE IF NOT EXISTS lab_test_record (
    test_ID INT PRIMARY KEY AUTO_INCREMENT,
    test_description VARCHAR(255) NOT NULL
);

-- Lab Report Record Table
CREATE TABLE IF NOT EXISTS lab_report_record (
    lab_report_ID INT PRIMARY KEY AUTO_INCREMENT,
    test_ID INT NOT NULL,
    consultation_ID INT NOT NULL,
    FOREIGN KEY (test_ID) REFERENCES lab_test_record(test_ID),
    FOREIGN KEY (consultation_ID) REFERENCES consultation_record(consultation_ID)
);

-- Chief Complaint Record Table
CREATE TABLE IF NOT EXISTS chief_complaint_record (
    complaint_ID INT PRIMARY KEY AUTO_INCREMENT,
    complaint_description VARCHAR(255) NOT NULL
);

-- Consultation Chief Complaint Junction Table
CREATE TABLE IF NOT EXISTS consultation_chief_complaint_record (
    consultation_ID INT NOT NULL,
    complaint_ID INT NOT NULL,
    PRIMARY KEY (consultation_ID, complaint_ID),
    FOREIGN KEY (consultation_ID) REFERENCES consultation_record(consultation_ID),
    FOREIGN KEY (complaint_ID) REFERENCES chief_complaint_record(complaint_ID)
);

-- Medical Diagnosis Record Table
CREATE TABLE IF NOT EXISTS medical_diagnosis_record (
    diagnosis_ID INT PRIMARY KEY AUTO_INCREMENT,
    diagnosis_description VARCHAR(255) NOT NULL
);

-- Consultation Medical Diagnosis Junction Table
CREATE TABLE IF NOT EXISTS consultation_medical_diagnosis_record (
    consultation_ID INT NOT NULL,
    diagnosis_ID INT NOT NULL,
    PRIMARY KEY (consultation_ID, diagnosis_ID),
    FOREIGN KEY (consultation_ID) REFERENCES consultation_record(consultation_ID),
    FOREIGN KEY (diagnosis_ID) REFERENCES medical_diagnosis_record(diagnosis_ID)
);

-- Doctors Record Table
INSERT INTO doctors_record (doctor_firstname, doctor_lastname, specialization, phoneNumber, email)
VALUES ('0', '0', '0', '0', '0');

-- Patients Record Table
INSERT INTO patients_record (patient_firstname, patient_lastname, age, birth_date, sex, patient_height, patient_weight, religion)
VALUES ('0', '0', 0, '0000-01-01', '0', 0.00, 0.00, '0');

-- Medication Record Table
INSERT INTO medication_record (generic_name, brand_name)
VALUES ('0', '0');

-- Vital Signs Record Table
INSERT INTO vital_signs_record (temperature, pulse, respiratory_rate, systolic_bp, diastolic_bp, SPO2)
VALUES (0.0, 0, 0, 0, 0, 0);

-- Lab Test Record Table
INSERT INTO lab_test_record (test_description)
VALUES ('0');

-- Consultation Record Table
INSERT INTO consultation_record (doctor_ID, patient_ID, vital_signs_ID)
VALUES (1, 1, 1);

-- Chief Complaint Record Table
INSERT INTO chief_complaint_record (complaint_description)
VALUES ('0');

-- Consultation Chief Complaint Junction Table
INSERT INTO consultation_chief_complaint_record (consultation_ID, complaint_ID)
VALUES (1, 1);

-- Medical Diagnosis Record Table
INSERT INTO medical_diagnosis_record (diagnosis_description)
VALUES ('0');

-- Consultation Medical Diagnosis Junction Table
INSERT INTO consultation_medical_diagnosis_record (consultation_ID, diagnosis_ID)
VALUES (1, 1);

-- Prescription Record Table
INSERT INTO prescription_record (medication_ID, frequency, dosage, doctor_ID, patient_ID, consultation_ID)
VALUES (1, 0, 0.00, 1, 1, 1);

-- Lab Report Record Table
INSERT INTO lab_report_record (test_ID, consultation_ID)
VALUES (1, 1);
