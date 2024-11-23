-- 25 records for each table

INSERT INTO doctors_record (doctor_firstname, doctor_lastname, specialization, phoneNumber, email)
VALUES
('John', 'Doe', 'Cardiology', '123-456-7890', 'john.doe@example.com'),
('Jane', 'Smith', 'Neurology', '123-456-7891', 'jane.smith@example.com'),
('Michael', 'Johnson', 'Orthopedics', '123-456-7892', 'michael.johnson@example.com'),
('Emily', 'Davis', 'Pediatrics', '123-456-7893', 'emily.davis@example.com'),
('Sarah', 'Williams', 'Dermatology', '123-456-7894', 'sarah.williams@example.com'),
('David', 'Brown', 'Endocrinology', '123-456-7895', 'david.brown@example.com'),
('Linda', 'Martinez', 'Gastroenterology', '123-456-7896', 'linda.martinez@example.com'),
('James', 'Garcia', 'Psychiatry', '123-456-7897', 'james.garcia@example.com'),
('Patricia', 'Rodriguez', 'Obstetrics', '123-456-7898', 'patricia.rodriguez@example.com'),
('Robert', 'Wilson', 'Pulmonology', '123-456-7899', 'robert.wilson@example.com'),
('Mary', 'Lopez', 'Ophthalmology', '123-456-7800', 'mary.lopez@example.com'),
('William', 'Gonzalez', 'Nephrology', '123-456-7801', 'william.gonzalez@example.com'),
('Sophia', 'Perez', 'Rheumatology', '123-456-7802', 'sophia.perez@example.com'),
('John', 'Taylor', 'General Surgery', '123-456-7803', 'john.taylor@example.com'),
('Olivia', 'Anderson', 'Anesthesiology', '123-456-7804', 'olivia.anderson@example.com'),
('Ethan', 'Thomas', 'Cardiology', '123-456-7805', 'ethan.thomas@example.com'),
('Ava', 'Jackson', 'Pediatrics', '123-456-7806', 'ava.jackson@example.com'),
('Liam', 'White', 'Gastroenterology', '123-456-7807', 'liam.white@example.com'),
('Mia', 'Harris', 'Plastic Surgery', '123-456-7808', 'mia.harris@example.com'),
('Jacob', 'Martin', 'Hematology', '123-456-7809', 'jacob.martin@example.com'),
('Isabella', 'Thompson', 'Dermatology', '123-456-7810', 'isabella.thompson@example.com'),
('Lucas', 'Moore', 'Urology', '123-456-7811', 'lucas.moore@example.com'),
('Amelia', 'Taylor', 'Infectious Disease', '123-456-7812', 'amelia.taylor@example.com'),
('Henry', 'Anderson', 'Neurology', '123-456-7813', 'henry.anderson@example.com'),
('Charlotte', 'Davis', 'Emergency Medicine', '123-456-7814', 'charlotte.davis@example.com');

INSERT INTO patients_record (patient_firstname, patient_lastname, age, birth_date, sex, pati    ent_height, patient_weight, religion)
VALUES
('Alice', 'Brown', 25, '1999-06-15 00:00:00', 'F', 165.5, 55.5, 'Christian'),
('Bob', 'Clark', 40, '1984-02-20 00:00:00', 'M', 175.0, 80.0, 'Muslim'),
('Charlie', 'Evans', 30, '1994-11-05 00:00:00', 'M', 180.0, 70.0, 'Hindu'),
('Diana', 'Harris', 55, '1969-03-25 00:00:00', 'F', 160.0, 65.0, 'Jewish'),
('Eva', 'Morris', 23, '2001-01-10 00:00:00', 'F', 155.0, 50.0, 'Buddhist'),
('George', 'Wilson', 60, '1964-07-15 00:00:00', 'M', 170.0, 75.0, 'Christian'),
('Helen', 'Lopez', 35, '1989-09-03 00:00:00', 'F', 168.0, 65.5, 'Muslim'),
('Ivy', 'Gonzalez', 28, '1996-05-12 00:00:00', 'F', 162.5, 58.0, 'Jewish'),
('Jack', 'Miller', 50, '1974-11-22 00:00:00', 'M', 179.0, 82.0, 'Hindu'),
('Kim', 'Rodriguez', 45, '1979-03-30 00:00:00', 'F', 158.0, 60.0, 'Christian'),
('Liam', 'Perez', 29, '1995-01-14 00:00:00', 'M', 176.0, 72.5, 'Buddhist'),
('Monica', 'Walker', 39, '1985-06-09', 'F', 164.0, 68.0, 'Muslim'),
('Nina', 'Lopez', 26, '1998-12-01', 'F', 160.0, 55.0, 'Hindu'),
('Oscar', 'Martin', 33, '1991-08-22', 'M', 178.0, 74.0, 'Jewish'),
('Paul', 'Evans', 38, '1986-04-17', 'M', 172.0, 77.5, 'Christian'),
('Quinn', 'Harris', 31, '1993-07-05', 'F', 161.0, 59.5, 'Buddhist'),
('Riley', 'Perez', 46, '1978-10-30', 'M', 174.0, 80.0, 'Muslim'),
('Sophia', 'Clark', 22, '2002-01-12', 'F', 165.0, 58.0, 'Hindu'),
('Toby', 'Taylor', 54, '1970-09-15', 'M', 180.0, 85.0, 'Christian'),
('Ursula', 'Rodriguez', 27, '1997-11-29', 'F', 163.0, 60.0, 'Jewish'),
('Victor', 'Davis', 62, '1962-02-10', 'M', 182.0, 90.0, 'Buddhist'),
('Wendy', 'Martin', 29, '1995-04-23', 'F', 170.0, 64.0, 'Muslim'),
('Xander', 'Lopez', 36, '1988-05-19', 'M', 175.0, 80.5, 'Hindu'),
('Yara', 'Wilson', 41, '1983-09-06', 'F', 160.0, 66.0, 'Jewish'),
('Zane', 'Morris', 29, '1995-12-03', 'M', 179.0, 78.5, 'Buddhist');

INSERT INTO medication_record (generic_name, brand_name)
VALUES
('Aspirin', 'Bayer Aspirin'),
('Paracetamol', 'Tylenol'),
('Amoxicillin', 'Amoxil'),
('Lisinopril', 'Prinivil'),
('Metformin', 'Glucophage'),
('Ibuprofen', 'Advil'),
('Prednisone', 'Deltasone'),
('Simvastatin', 'Zocor'),
('Hydrochlorothiazide', 'Microzide'),
('Omeprazole', 'Prilosec'),
('Acetaminophen', 'Tylenol'),
('Ciprofloxacin', 'Cipro'),
('Furosemide', 'Lasix'),
('Atorvastatin', 'Lipitor'),
('Sertraline', 'Zoloft'),
('Clonazepam', 'Klonopin'),
('Albuterol', 'Ventolin'),
('Losartan', 'Cozaar'),
('Gabapentin', 'Neurontin'),
('Warfarin', 'Coumadin'),
('Propranolol', 'Inderal'),
('Sildenafil', 'Viagra'),
('Metoprolol', 'Lopressor'),
('Amlodipine', 'Norvasc'),
('Ezetimibe', 'Zetia'),
('Paracetamol', 'Panadol');

INSERT INTO prescription_record (medication_ID, prescription_date, frequency, dosage, doctor_ID, patient_ID)
VALUES
(1, '2024-11-01 10:00:00', 3, 0.5, 1, 1),
(2, '2024-11-02 09:30:00', 2, 1.0, 2, 2),
(3, '2024-11-03 14:00:00', 1, 1.0, 3, 3),
(4, '2024-11-04 16:45:00', 1, 0.5, 4, 4),
(5, '2024-11-05 08:30:00', 2, 0.25, 5, 5),
(6, '2024-11-06 11:00:00', 3, 0.2, 6, 6),
(7, '2024-11-07 13:15:00', 1, 0.4, 7, 7),
(8, '2024-11-08 10:30:00', 1, 1.0, 8, 8),
(9, '2024-11-09 09:00:00', 2, 0.5, 9, 9),
(10, '2024-11-10 15:00:00', 1, 0.2, 10, 10),
(11, '2024-11-11 17:30:00', 3, 0.5, 11, 11),
(12, '2024-11-12 12:45:00', 2, 0.5, 12, 12),
(13, '2024-11-13 14:30:00', 1, 0.25, 13, 13),
(14, '2024-11-14 10:00:00', 1, 0.1, 14, 14),
(15, '2024-11-15 09:30:00', 2, 0.5, 15, 15),
(16, '2024-11-16 08:00:00', 1, 0.5, 16, 16),
(17, '2024-11-17 10:00:00', 3, 1.0, 17, 17),
(18, '2024-11-18 14:00:00', 1, 1.0, 18, 18),
(19, '2024-11-19 13:00:00', 2, 0.25, 19, 19),
(20, '2024-11-20 15:30:00', 1, 0.25, 20, 20),
(21, '2024-11-21 17:00:00', 2, 0.5, 21, 21),
(22, '2024-11-22 10:15:00', 3, 0.25, 22, 22),
(23, '2024-11-23 09:00:00', 1, 0.5, 23, 23),
(24, '2024-11-24 16:30:00', 2, 0.1, 24, 24),
(25, '2024-11-25 12:45:00', 1, 0.5, 25, 25);

INSERT INTO vital_signs_record (temperature, pulse, respiratory_rate, systolic_bp, diastolic_bp, SPO2, vital_signs_date)
VALUES
(36.5, 72, 16, 120, 80, 98, '2024-11-01 10:00:00'),
(37.0, 75, 18, 130, 85, 97, '2024-11-02 09:30:00'),
(36.8, 80, 20, 125, 82, 99, '2024-11-03 14:00:00'),
(37.2, 78, 22, 135, 88, 95, '2024-11-04 16:45:00'),
(36.6, 70, 17, 118, 79, 100, '2024-11-05 08:30:00'),
(37.4, 85, 19, 140, 90, 96, '2024-11-06 11:00:00'),
(36.7, 74, 16, 122, 80, 97, '2024-11-07 13:15:00'),
(36.9, 77, 18, 128, 84, 99, '2024-11-08 10:30:00'),
(37.1, 73, 21, 130, 87, 98, '2024-11-09 09:00:00'),
(36.8, 76, 20, 127, 85, 95, '2024-11-10 15:00:00'),
(37.0, 79, 22, 138, 89, 97, '2024-11-11 17:30:00'),
(36.5, 71, 18, 120, 78, 99, '2024-11-12 12:45:00'),
(36.6, 70, 16, 123, 81, 96, '2024-11-13 14:30:00'),
(37.3, 83, 19, 132, 86, 98, '2024-11-14 10:00:00'),
(36.7, 75, 17, 124, 80, 97, '2024-11-15 09:30:00'),
(37.0, 80, 21, 133, 84, 95, '2024-11-16 08:00:00'),
(36.8, 74, 19, 126, 82, 99, '2024-11-17 10:00:00'),
(37.1, 72, 18, 131, 83, 96, '2024-11-18 14:00:00'),
(36.9, 77, 20, 129, 81, 98, '2024-11-19 13:00:00'),
(37.0, 79, 22, 137, 86, 95, '2024-11-20 15:30:00'),
(36.6, 70, 16, 119, 78, 100, '2024-11-21 17:00:00'),
(37.3, 84, 21, 141, 90, 96, '2024-11-22 10:15:00'),
(36.5, 73, 18, 122, 79, 97, '2024-11-23 09:00:00'),
(36.7, 75, 17, 125, 80, 98, '2024-11-24 16:30:00'),
(37.0, 78, 19, 130, 83, 95, '2024-11-25 12:45:00');

INSERT INTO lab_test_record (test_description)
VALUES
('Complete Blood Count (CBC)'),
('Lipid Profile'),
('Liver Function Test'),
('Kidney Function Test'),
('Urinalysis'),
('Thyroid Function Test'),
('Blood Glucose Test'),
('Electrolytes Panel'),
('Liver Enzyme Panel'),
('Blood Culture Test'),
('Bacterial Sensitivity Test'),
('Prostate-Specific Antigen (PSA)'),
('HIV Test'),
('Hepatitis B Test'),
('Hepatitis C Test'),
('Vitamin D Level Test'),
('Cholesterol Test'),
('C-Reactive Protein (CRP) Test'),
('Creatinine Test'),
('Erythrocyte Sedimentation Rate (ESR) Test'),
('Hemoglobin A1c (HbA1c) Test'),
('Coagulation Profile'),
('Blood Type and Crossmatch Test'),
('D-dimer Test'),
('Troponin Test');

INSERT INTO lab_report_record (test_ID)
VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22),
(23),
(24),
(25);

INSERT INTO consultation_record (prescription_ID, doctor_ID, patient_ID, vital_signs_ID, lab_report_ID, consultation_date)
VALUES
(1, 1, 1, 1, 1, '2024-11-01 10:00:00'),
(2, 2, 2, 2, 2, '2024-11-02 09:30:00'),
(3, 3, 3, 3, 3, '2024-11-03 14:00:00'),
(4, 4, 4, 4, 4, '2024-11-04 16:45:00'),
(5, 5, 5, 5, 5, '2024-11-05 08:30:00'),
(6, 6, 6, 6, 6, '2024-11-06 11:00:00'),
(7, 7, 7, 7, 7, '2024-11-07 13:15:00'),
(8, 8, 8, 8, 8, '2024-11-08 10:30:00'),
(9, 9, 9, 9, 9, '2024-11-09 09:00:00'),
(10, 10, 10, 10, 10, '2024-11-10 15:00:00'),
(11, 11, 11, 11, 11, '2024-11-11 17:30:00'),
(12, 12, 12, 12, 12, '2024-11-12 12:45:00'),
(13, 13, 13, 13, 13, '2024-11-13 14:30:00'),
(14, 14, 14, 14, 14, '2024-11-14 10:00:00'),
(15, 15, 15, 15, 15, '2024-11-15 09:30:00'),
(16, 16, 16, 16, 16, '2024-11-16 08:00:00'),
(17, 17, 17, 17, 17, '2024-11-17 10:00:00'),
(18, 18, 18, 18, 18, '2024-11-18 14:00:00'),
(19, 19, 19, 19, 19, '2024-11-19 13:00:00'),
(20, 20, 20, 20, 20, '2024-11-20 15:30:00'),
(21, 21, 21, 21, 21, '2024-11-21 17:00:00'),
(22, 22, 22, 22, 22, '2024-11-22 10:15:00'),
(23, 23, 23, 23, 23, '2024-11-23 09:00:00'),
(24, 24, 24, 24, 24, '2024-11-24 16:30:00'),
(25, 25, 25, 25, 25, '2024-11-25 12:45:00');

INSERT INTO chief_complaint_record (complaint_description)
VALUES
('Fever and chills'),
('Shortness of breath'),
('Chest pain'),
('Headache'),
('Abdominal pain'),
('Nausea and vomiting'),
('Fatigue and weakness'),
('Back pain'),
('Cough'),
('Sore throat'),
('Dizziness and lightheadedness'),
('Swelling in legs'),
('Skin rash'),
('Joint pain'),
('Weight loss'),
('Cough with blood'),
('Difficulty swallowing'),
('Frequent urination'),
('Muscle pain'),
('Difficulty breathing'),
('Bloody stool'),
('Diarrhea'),
('Severe headache'),
('Heart palpitations'),
('Joint stiffness');

INSERT INTO consultation_chief_complaint_record (consultation_ID, complaint_ID)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20),
(21, 21),
(22, 22),
(23, 23),
(24, 24),
(25, 25);

INSERT INTO medical_diagnosis_record (diagnosis_description)
VALUES
('Influenza'),
('Pneumonia'),
('Myocardial Infarction'),
('Migraine'),
('Gastritis'),
('Gastroenteritis'),
('Chronic Fatigue Syndrome'),
('Herniated Disc'),
('Common Cold'),
('Tonsillitis'),
('Vertigo'),
('Congestive Heart Failure'),
('Eczema'),
('Osteoarthritis'),
('Malaria'),
('Tuberculosis'),
('Esophageal Cancer'),
('Diabetes Type 2'),
('Fibromyalgia'),
('Chronic Obstructive Pulmonary Disease (COPD)'),
('Colorectal Cancer'),
('Hypertension'),
('Acute Sinusitis'),
('Rheumatoid Arthritis'),
('Atrial Fibrillation');

INSERT INTO consultation_medical_diagnosis_record (consultation_ID, diagnosis_ID)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20),
(21, 21),
(22, 22),
(23, 23),
(24, 24),
(25, 25);
