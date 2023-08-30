CREATE TABLE medicalPrescription (
 prescription_code serial PRIMARY KEY, 
 civ VARCHAR(16) NOT NULL,
 telephone_number VARCHAR(10),
 priority VARCHAR(1) CHECK (priority = 'P'
								OR priority = 'D'
								OR priority = 'U'),
 medic_CIV VARCHAR(16) NOT NULL,
 description VARCHAR(512),
 medical_service_code VARCHAR(6),
 date_of_creation DATE,
 date_of_insertion TIMESTAMP
)