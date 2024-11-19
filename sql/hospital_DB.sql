CREATE DATABASE IF NOT EXISTS Hospital_DB;
USE Hospital_DB;

-- Doctors Record Table
CREATE TABLE IF NOT EXISTS doctors_record (
    doctor_ID INT PRIMARY KEY AUTO_INCREMENT,
    doctor_name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Patients Record Table
CREATE TABLE IF NOT EXISTS patients_record (
    patient_ID INT PRIMARY KEY AUTO_INCREMENT,
    patient_name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    birth_date DATE NOT NULL,
    sex ENUM('M', 'F') NOT NULL,
    patient_height DECIMAL(5, 2) NOT NULL,        -- Height in centimeters or inches
    patient_weight DECIMAL(5, 2) NOT NULL,        -- Weight in kilograms or pounds
    religion VARCHAR(50) NOT NULL,
    doctor_ID INT NOT NULL,
    FOREIGN KEY (doctor_ID) REFERENCES doctors_record(doctor_ID),
    date_created DATETIME DEFAULT CURRENT_TIMESTAMP
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
    FOREIGN KEY (medication_ID) REFERENCES medication_record(medication_ID),
    FOREIGN KEY (doctor_ID) REFERENCES doctors_record(doctor_ID),
    FOREIGN KEY (patient_ID) REFERENCES patients_record(patient_ID)
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

-- Lab Test Record Table
CREATE TABLE IF NOT EXISTS lab_test_record (
    test_ID INT PRIMARY KEY AUTO_INCREMENT,
    test_description VARCHAR(255) NOT NULL
);

-- Lab Report Record Table
CREATE TABLE IF NOT EXISTS lab_report_record (
    lab_report_ID INT PRIMARY KEY AUTO_INCREMENT,
    test_ID INT NOT NULL,
    FOREIGN KEY (test_ID) REFERENCES lab_test_record(test_ID)
);

-- Consultation Record Table
CREATE TABLE IF NOT EXISTS consultation_record (
    consultation_ID INT PRIMARY KEY AUTO_INCREMENT,
    prescription_ID INT,
    doctor_ID INT NOT NULL,
    patient_ID INT NOT NULL,
    vital_signs_ID INT,
    lab_report_ID INT,
    consultation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (prescription_ID) REFERENCES prescription_record(prescription_ID),
    FOREIGN KEY (doctor_ID) REFERENCES doctors_record(doctor_ID),
    FOREIGN KEY (patient_ID) REFERENCES patients_record(patient_ID),
    FOREIGN KEY (vital_signs_ID) REFERENCES vital_signs_record(vital_signs_ID),
    FOREIGN KEY (lab_report_ID) REFERENCES lab_report_record(lab_report_ID)
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
