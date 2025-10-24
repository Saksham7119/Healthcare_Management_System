<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Healthcare Portal</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"/>
  <link rel="stylesheet" href="<c:url value='/static/css/index.css' />">
</head>
<body>

  <c:import url="header.jsp"/>

  <!-- Hero Section -->
  <section class="hero">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 mb-4">
          <h1>Manage Healthcare Effortlessly</h1>
          <p>
            A secure platform connecting <strong>patients</strong>, <strong>doctors</strong> and <strong>manufacturers</strong>.
            Simplify appointments, prescriptions, and medicine supply.
          </p>
          <a href="#roles" class="btn btn-light text-primary fw-semibold rounded-pill px-4 py-2 mt-3">
            Get Started <i class="bi bi-arrow-right ms-2"></i>
          </a>
        </div>
        <div class="col-lg-6 text-center">
          <img src="static/media/images/medicine.png" alt="Healthcare Illustration" class=" rounded-4"/>
        </div>
      </div>
    </div>
  </section>

  <!-- Role Selection -->
  <section id="roles" class="container my-5">
    <div class="row g-4">
      <div class="col-md-4">
        <div class="role-card">
          <img src="static/media/images/doctor.png" alt="Patient"/>
          <h5>Patient</h5>
          <a href="signup.do" class="btn btn-primary w-100">Register as Patient</a>
        </div>
      </div>
      <div class="col-md-4">
        <div class="role-card">
          <img src="static/media/images/doctor.png" alt="Doctor"/>
          <h5>Doctor</h5>
          <a href="signup.do" class="btn btn-success w-100">Register as Doctor</a>
        </div>
      </div>
      <div class="col-md-4">
        <div class="role-card">
          <img src="static/media/images/doctor.png" alt="Manufacturer"/>
          <h5>Manufacturer</h5>
          <a href="signup.do" class="btn btn-warning w-100">Register as Manufacturer</a>
        </div>
      </div>
    </div>
    <div class="text-center mt-5">
      <a href="#" class="btn btn-outline-primary rounded-pill px-4 py-2">Book Appointment</a>
    </div>
  </section>

  <!-- Why Choose Us -->
  <section class="container my-5">
    <div class="why-us">
      <h2 class="text-center">Why Choose HealthCare+</h2>
      <div class="row g-4 mt-3">
        <div class="col-md-4 text-center">
          <i class="bi bi-shield-lock-fill"></i>
          <h6 class="mt-2 fw-semibold">Secure & Private</h6>
          <p>We keep your health records encrypted and safe.</p>
        </div>
        <div class="col-md-4 text-center">
          <i class="bi bi-calendar-check-fill"></i>
          <h6 class="mt-2 fw-semibold">Easy Appointments</h6>
          <p>Schedule and manage appointments seamlessly.</p>
        </div>
        <div class="col-md-4 text-center">
          <i class="bi bi-people-fill"></i>
          <h6 class="mt-2 fw-semibold">One Connected Platform</h6>
          <p>Patients, doctors & pharmacies working together.</p>
        </div>
      </div>
    </div>
  </section>

  <!-- Footer -->
  <footer class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-6 mb-3">
          <h5 class="text-white">HealthCare+</h5>
          <p class="small">
            Your trusted digital healthcare partner. 
            Simplifying care, improving lives.
          </p>
        </div>
        <div class="col-md-3 mb-3">
          <h6 class="text-white">Quick Links</h6>
          <ul class="list-unstyled">
            <li><a href="#">Home</a></li>
            <li><a href="#">Patient Portal</a></li>
            <li><a href="#">Doctor Portal</a></li>
            <li><a href="#">Manufacturer Portal</a></li>
          </ul>
        </div>
        <div class="col-md-3 mb-3">
          <h6 class="text-white">Contact</h6>
          <p class="small mb-1"><i class="bi bi-envelope me-2"></i> support@healthcare.com</p>
          <p class="small"><i class="bi bi-telephone me-2"></i> +1 (800) 123 456</p>
        </div>
      </div>
    </div>
  </footer>

  <!-- Login Modal (unchanged) -->
  <div class="modal fade" id="loginModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content rounded-4 shadow-lg border-0">
        <div class="modal-header border-0 flex-column text-center">
          <img src="static/media/images/brandLogo.png" alt="Logo" class="mb-2" style="width: 60px;" />
          <h5 class="fw-bold text-success mb-1">Healthcare Portal</h5>
          <p class="text-muted small mb-0">Login to continue</p>
        </div>
        <div class="modal-body px-4">
          <form action="login.do" method="post" id="login_form">
            <div class="form-floating mb-3">
              <input type="email" name="email" id="email" class="form-control" placeholder="Enter Existing Email" required/>
              <label for="email">Email</label>
            </div>
            <div class="form-floating mb-2">
              <input type="password" name="password" id="password" class="form-control" placeholder="Enter Password" required/>
              <label for="password">Password</label>
            </div>
          </form>
        </div>
        <div class="modal-footer border-0 d-flex justify-content-between">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
            <i class="bi bi-x-circle me-2"></i>Close
          </button>
          <button type="submit" form="login_form" class="btn btn-primary d-flex align-items-center gap-2">
            <i class="bi bi-door-open"></i> Login
          </button>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
