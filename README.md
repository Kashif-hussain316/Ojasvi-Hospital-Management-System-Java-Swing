# Ojasvi-Hospital-Management-System-Java-Swing
A Java-based desktop application designed to simplify and digitalize hospital operations such as patient registration, appointment scheduling, doctor management, prescriptions, and billing.
Built entirely using Core Java & Swing, the system requires no database setup, making it ideal for small clinics and educational demonstrations.


---

📌 Project Overview

The Ojasvi Hospital Management System is a lightweight, easy-to-use hospital administration tool developed as part of the Advanced Java course. It demonstrates key programming concepts including:

Object-Oriented Programming

Java Swing GUI Development

Exception Handling

Collections Framework

Modular System Design


The system includes pre-loaded sample data for demonstration and runs on any machine with Java 8+ installed.


---

✨ Key Features

🧑‍🤝‍🧑 Patient Management

Register new patients with details such as name, gender, phone, blood group, and disease.

Auto-generated unique patient IDs (e.g., P001).

Organized patient table view.


🩺 Doctor Management

View pre-loaded doctor details: specialization, qualification, consultation fee.

Auto-generated unique doctor IDs.


📅 Appointment Scheduling

Book appointments by selecting patient & doctor.

Assign date & time.

Auto-generated appointment IDs.

Real-time dropdown update after new patient registration.


💊 Prescription Module

Generate digital prescriptions containing medicines & instructions.

Auto-generated prescription IDs (RX001).

Complete list of past prescriptions.


💰 Billing System

Generate itemized bills: consultation, medicine & room charges.

Auto calculation of total.

Payment tracking (Paid / Unpaid).

Prevention of duplicate payments.


📊 Interactive Dashboard

Displays real-time statistics:

Total Patients

Total Doctors

Appointments

Prescriptions

Bills

Pending Payments




---

🛠️ Technologies Used

Java (JDK 8+)

Java Swing (GUI)

ArrayList Collections

Object-Oriented Programming

No database required



---

📐 System Architecture

The system follows a 3-layer architecture:

1. Presentation Layer – Java Swing UI


2. Business Logic Layer – Core application logic


3. Data Layer – In-memory storage using ArrayList
4. 📂 Project Structure
/src
 ├── model
 │    ├── Person.java
 │    ├── Patient.java
 │    ├── Doctor.java
 │    ├── Appointment.java
 │    ├── Prescription.java
 │    └── Bill.java
 │
 ├── exception
 │    └── HospitalException.java
 │
 └── ui
      └── OjasviHospitalSystem.java   (Main GUI + Application Logic)

🚀 How to Run

1. Install Java JRE/JDK 8 or above
2. Clone the repository:
git clone https://github.com/your-username/ojasvi-hms.git


3. Compile:

   javac OjasviHospitalSystem.java


5. Run:

   java OjasviHospitalSystem

Dashboard:

Patient Registration

Appointment Booking

Billing

🧪 Testing

Performed:

Unit Testing

Integration Testing

Performance Testing

Input Validation

UI Testing


All core features passed successfully.


---

⚠️ Limitations

No permanent storage (data resets on restart)

No login/user roles

Single-user system

No advanced search/filter

No report export (PDF/Excel)



---

🔮 Future Enhancements

Add MySQL/PostgreSQL database

Multi-user login system

Search & filtering

PDF/Excel report generation

SMS/Email appointment reminders

Mobile application support

Pharmacy & lab modules

Analytics dashboard



---

👥 Team Members

Kashif Hussain – Backend, Architecture, Integration

Gaurav Gupta – UI/UX & Swing Development

Deendayal – Testing, Documentation, Data Initialization



---

📄 License

This project is developed for academic purposes.
Feel free to use or modify with credit.
