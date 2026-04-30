# Healthcare Management System (HMS)

A comprehensive full-stack Healthcare Management System developed using Java Servlets, JSP, and MySQL. The system provides a complete solution for managing healthcare operations including appointment booking, prescription management, medicine inventory, and multi-role authentication.

---

## 📋 Project Description

This Healthcare Management System is a robust web application designed to streamline healthcare operations by connecting patients, doctors, medicine manufacturers, and administrators on a single platform. The application implements role-based access control with session-based authentication, ensuring secure and personalized experiences for each user type.

The system enables patients to find doctors, book appointments, and view their prescriptions. Doctors can manage their clinic schedules, view patient appointments, and generate prescriptions. Medicine manufacturers can list and manage their medicine inventory, while administrators oversee the entire system.

---

## ✨ Key Features

### Authentication & Security

- **Session-based Authentication**: Secure login/logout with session management
- **Role-based Access Control**: Granular permissions for different user types
- **Password Protection**: Secure credential handling

### Appointment Management

- **Doctor Search**: Find doctors by specialization, location, or clinic
- **Online Booking**: Schedule appointments with available time slots
- **Appointment Tracking**: View and manage upcoming/past appointments

### Prescription System

- **Digital Prescriptions**: Doctors can generate and save prescriptions
- **Patient Access**: Patients can view their prescription history
- **Medicine Integration**: Link prescriptions to available medicines

### Medicine Management

- **Medicine Inventory**: Manufacturers can add and manage medicines
- **Medicine Search**: Search medicines by name, denomination, or manufacturer
- **Inventory Tracking**: Track medicine stock and availability

### Profile Management

- **Doctor Profiles**: Configure specialization, clinic details, and schedule
- **Patient Profiles**: Manage personal information and medical history
- **Manufacturer Profiles**: Company information and medicine catalog

---

## 👥 User Roles and Functionalities

### 🏥 Patient

- Register and login to the system
- Search for doctors by specialization and location
- View doctor profiles and clinic information
- Book appointments at preferred clinics
- View appointment history and status
- Access and download prescriptions
- Update personal profile information

### 👨‍⚕️ Doctor

- Register and configure professional profile
- Set up clinic schedule and working hours
- View patient appointments for each clinic
- Generate digital prescriptions for patients
- View prescription history
- Manage clinic information and settings

### 🏭 Medicine Manufacturer

- Register company profile
- Add new medicines with detailed information
- Manage medicine inventory and stock
- Update medicine details and pricing
- View medicine market analytics

### ⚙️ Administrator

- System-wide oversight and management
- User management and role assignment
- Platform configuration and settings

---

## 🛠️ Tech Stack

### Frontend

- **HTML5**: Semantic markup and structure
- **CSS3**: Custom styling and responsive design
- **JavaScript (ES6+)**: Client-side interactivity
- **Bootstrap 5**: UI components and responsive grid

### Backend

- **Java**: Core programming language
- **Java Servlets**: Request handling and business logic
- **JSP (JavaServer Pages)**: Dynamic view rendering
- **Session Management**: User authentication state

### Database

- **MySQL**: Relational database management
- **JDBC**: Database connectivity and operations

### Server & Tools

- **Apache Tomcat 9.0**: Servlet container and web server
- **Eclipse/IntelliJ**: Development environment
- **Maven**: Project build and dependency management

---

## 🏗️ System Architecture

The application follows the **MVC (Model-View-Controller)** design pattern:

```
┌─────────────────────────────────────────────────────────────┐
│                        VIEW (JSP)                           │
│  login.jsp, signup.jsp, doctorDashboard.jsp, etc.        │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                    CONTROLLER (Servlets)                    │
│  LoginServlet, DoctorDashboardServlet, etc.               │
│  - Request Handling                                         │
│  - Business Logic                                           │
│  - Session Management                                       │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                      MODEL (JavaBeans)                      │
│  User, Patient, Doctor, Appointment, Prescription, etc.    │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                    DATABASE (MySQL)                         │
│  healthcaredb - Relational data storage                    │
└─────────────────────────────────────────────────────────────┘
```

### Request Flow

1. User submits request via browser
2. JSP forwards to appropriate Servlet
3. Servlet processes business logic
4. Servlet interacts with database via JDBC
5. Servlet sets attributes and forwards to JSP
6. JSP renders dynamic HTML response

---

## 🗃️ Database Design

### Core Entities

| Entity                      | Description                                        |
| --------------------------- | -------------------------------------------------- |
| `User`                      | Base user table with authentication credentials    |
| `Patient`                   | Patient-specific information and medical details   |
| `Doctor`                    | Doctor profile with specialization and credentials |
| `Manufacturer`              | Medicine manufacturer company information          |
| `Clinic`                    | Healthcare clinic/facility details                 |
| `ClinicDay`                 | Doctor's working days at each clinic               |
| `Appointment`               | Patient appointment records                        |
| `Prescription`              | Doctor-generated prescriptions                     |
| `Medicine`                  | Medicine inventory and details                     |
| `MedicineDenomination`      | Specific medicine variants                         |
| `GenericMedicine`           | Generic medicine names                             |
| `MedicineFormat`            | Medicine forms (tablet, syrup, etc.)               |
| `MedicineUnit`              | Packaging units                                    |
| `City`, `State`, `Location` | Geographic data                                    |
| `Specialization`            | Medical specializations                            |
| `Degree`                    | Medical degrees and qualifications                 |
| `UserNotification`          | System notifications                               |

### Key Relationships

- One Doctor can have multiple Clinics
- One Clinic can have multiple Doctors (many-to-many via ClinicDay)
- One Patient can have multiple Appointments
- One Appointment can have multiple Prescriptions
- One Prescription links to one Patient and one Doctor

---

## 📂 Project Structure

```
HMS/
├── addPatientAppointmentPage.jsp      # Patient appointment booking
├── design.html                        # Design reference
├── DoctorClinics.jsp                  # Doctor's clinic management
├── doctorConfigureProfile.jsp         # Doctor profile settings
├── doctorDashboard.jsp                # Doctor dashboard
├── doctorGeneratePrescriptionPage.jsp # Prescription generation
├── doctorHomePage.jsp                 # Doctor home page
├── findDoctorForPatientPage.jsp       # Doctor search for patients
├── footer.jsp                         # Common footer
├── header.jsp                         # Common header
├── index.jsp                          # Landing page
├── login.jsp                          # Login page
├── ManufacturerDashboard.jsp          # Manufacturer dashboard
├── ManufacturerDashboard2.jsp         # Manufacturer alternate view
├── MedicineMarketForDoctor.jsp       # Medicine marketplace
├── MedicineStore.jsp                  # Medicine inventory
├── PatientHomePage.jsp               # Patient dashboard
├── patientPrescription.jsp           # Patient prescriptions
├── signup.jsp                         # Registration page
├── viewPatientAppointmentPage.jsp    # View appointments
│
├── static/
│   ├── css/                           # Stylesheets
│   │   ├── addPatientAppointmentPage.css
│   │   ├── doctorClinics.css
│   │   ├── doctorConfigureProfile.css
│   │   ├── doctorDashboard.css
│   │   ├── doctorGeneratePrescriptionPage.css
│   │   ├── doctorHomePage.css
│   │   ├── findDoctorForPatientPage.css
│   │   ├── index.css
│   │   ├── indexPage.css
│   │   ├── ManufacturerDashboard.css
│   │   ├── medicineMarketForDoctor.css
│   │   ├── MedicineStore.css
│   │   ├── patientHomePage.css
│   │   ├── patientPrescription.css
│   │   └── viewPatientAppointmentPage.css
│   │
│   ├── js/                            # JavaScript files
│   │   ├── addPatientAppointmentPage.js
│   │   ├── doctorClinic.js
│   │   ├── doctorDashboard.js
│   │   ├── doctorGeneratePrescriptionPage.js
│   │   ├── doctorHomepage.js
│   │   ├── findDoctorsForPatient.js
│   │   ├── manufacturerDashboard.js
│   │   ├── medicineMarketForDoctor.js
│   │   ├── medicineStore.js
│   │   ├── patientHomepage.js
│   │   ├── patientPrescription.js
│   │   └── viewPatientAppointmentPage.js
│   │
│   └── media/                         # Media assets
│       ├── images/
│       ├── audios/
│       └── videos/
│
├── WEB-INF/
│   ├── web.xml                        # Deployment descriptor
│   ├── classes/
│   │   ├── controllers/               # Servlet controllers
│   │   │   ├── AddClinicServlet.java
│   │   │   ├── AddMedicineServlet.java
│   │   │   ├── AddPatientAppointmentServlet.java
│   │   │   ├── ConfigureDoctorProfileServlet.java
│   │   │   ├── DoctorDashboardServlet.java
│   │   │   ├── LoginServlet.java
│   │   │   ├── LogoutServlet.java
│   │   │   ├── SavePrescriptionServlet.java
│   │   │   ├── ShowMedicinesServlet.java
│   │   │   └── ... (40+ servlets)
│   │   │
│   │   ├── models/                   # Entity classes
│   │   │   ├── User.java
│   │   │   ├── Patient.java
│   │   │   ├── Doctor.java
│   │   │   ├── Appointment.java
│   │   │   ├── Prescription.java
│   │   │   ├── Medicine.java
│   │   │   └── ... (30+ models)
│   │   │
│   │   ├── utils/
│   │   │   └── DBManager.java        # Database utility
│   │   │
│   │   ├── listeners/
│   │   ├── exceptions/
│   │   ├── elfuncs/
│   │   ├── tlds/
│   │   └── src/
│   │
│   ├── lib/                          # Dependencies
│   └── uploads/                      # Uploaded files
│       └── {patient_id}/             # Patient document storage
│
└── README.md                         # This file
```

---

## 🚀 Installation and Setup

### Prerequisites

| Requirement                | Version                   |
| -------------------------- | ------------------------- |
| Java Development Kit (JDK) | 8 or higher               |
| Apache Tomcat              | 9.0 or higher             |
| MySQL Server               | 5.7 or higher             |
| Eclipse IDE                | 2021 or higher (optional) |
| Browser                    | Chrome, Firefox, or Edge  |

### Step 1: Database Setup

```sql
-- Create the database
CREATE DATABASE healthcaredb;

-- Use the database
USE healthcaredb;

-- The application will create tables automatically
-- or import the provided SQL schema file
```

### Step 2: Configure Database Connection

Edit `WEB-INF/web.xml` to update database credentials:

```xml
<context-param>
    <param-name>dbhost</param-name>
    <param-value>localhost:</param-value>
</context-param>
<context-param>
    <param-name>dbport</param-name>
    <param-value>3306</param-value>
</context-param>
<context-param>
    <param-name>dbname</param-name>
    <param-value>healthcaredb</param-value>
</context-param>
<context-param>
    <param-name>dbuser</param-name>
    <param-value>root</param-value>
</context-param>
<context-param>
    <param-name>dbpassword</param-name>
    <param-value>1234</param-value>
</context-param>
```

### Step 3: Import Project to Eclipse

1. Open Eclipse IDE
2. Go to **File → Import**
3. Select **Web → WAR File** or **Existing Projects into Workspace**
4. Browse to the HMS project location
5. Click **Finish**

### Step 4: Configure Tomcat Server

1. Go to **Window → Preferences → Server → Runtime Environments**
2. Click **Add** and select **Apache Tomcat 9.0**
3. Browse to your Tomcat installation directory
4. Click **OK** and apply changes

---

## ▶️ Running the Application

### Method 1: Using Eclipse

1. Right-click on the project in Project Explorer
2. Select **Run As → Run on Server**
3. Choose Apache Tomcat 9.0
4. Click **Finish**

The application will be accessible at: `http://localhost:8080/HMS/`

### Method 2: Using Command Line

1. Build the WAR file:

   ```bash
   jar -cvf HMS.war *
   ```

2. Copy the WAR file to Tomcat webapps:

   ```bash
   copy HMS.war %CATALINA_HOME%\webapps\
   ```

3. Start Tomcat:

   ```bash
   %CATALINA_HOME%\bin\startup.bat
   ```

4. Access the application at `http://localhost:8080/HMS/`

### Default Login Credentials

| Role         | Email                    | Password    |
| ------------ | ------------------------ | ----------- |
| Patient      | patient@example.com      | password123 |
| Doctor       | doctor@example.com       | password123 |
| Manufacturer | manufacturer@example.com | password123 |

> **Note**: These are sample credentials. Register new users through the signup page.

---

## ⚙️ Environment Configuration

### Java Build Path

Ensure the following libraries are in your classpath:

- `servlet-api.jar` (provided by Tomcat)
- `mysql-connector-java.jar` (MySQL JDBC driver)

### Server Configuration

```properties
# application.properties (if used)
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/healthcaredb
db.username=root
db.password=1234

# Session timeout (web.xml)
<session-config>
    <session-timeout>30</session-timeout>
</session-config>
```

### File Upload Settings

The system supports file uploads for:

- Doctor profile pictures
- Patient documents
- Medicine images

Default upload directory: `WEB-INF/uploads/`

---

## 📸 Screenshots

### Landing Page

![Landing Page](static/media/images/screenshot-landing.png)

### Login Page

![Login Page](static/media/images/screenshot-login.png)

### Patient Dashboard

![Patient Dashboard](static/media/images/screenshot-patient-dashboard.png)

### Doctor Dashboard

![Doctor Dashboard](static/media/images/screenshot-doctor-dashboard.png)

### Appointment Booking

![Appointment Booking](static/media/images/screenshot-appointment.png)

### Prescription Generation

![Prescription Generation](static/media/images/screenshot-prescription.png)

> **Note**: Add your own screenshots to `static/media/images/` directory

---

## 🔮 Future Enhancements

### Short-term Improvements

- [ ] Add email notification system for appointment reminders
- [ ] Implement SMS verification for user registration
- [ ] Add prescription PDF download functionality
- [ ] Implement doctor availability calendar view
- [ ] Add medicine interaction checker

### Long-term Enhancements

- [ ] Migrate to Spring Boot framework
- [ ] Implement RESTful API for mobile app integration
- [ ] Add telemedicine/video consultation features
- [ ] Implement AI-powered symptom checker
- [ ] Add insurance claim processing module
- [ ] Implement blockchain for medical records
- [ ] Add analytics dashboard for administrators
- [ ] Implement multi-language support

### Performance Optimizations

- [ ] Implement caching with Redis
- [ ] Add database query optimization
- [ ] Implement load balancing
- [ ] Add CDN for static assets

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author Information

|                  |                                      |
| ---------------- | ------------------------------------ |
| **Project Lead** | Healthcare Development Team          |
| **Institution**  | Academic Project                     |
| **Tech Stack**   | Java Servlets, JSP, MySQL, Bootstrap |
| **Version**      | 1.0.0                                |
| **Last Updated** | April 2026                           |

---

## 🙏 Acknowledgments

- Apache Tomcat community
- MySQL documentation
- Bootstrap framework
- All contributors and testers

---

> **Note**: This project is for educational and demonstration purposes. For production use, ensure proper security audits, input validation, and compliance with healthcare regulations like HIPAA.
