<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>HealthCare</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<link rel="stylesheet" href="<c:url value='/static/css/indexPage.css' />">

<style>
   .brand-logo {
      width: 60px;
      margin-bottom: 1rem;
    }
    .btn-primary {
      background-color: #198754;
      border: none;
    }
    .btn-primary:hover {
      background-color: #157347;
    }
</style>
  </head>
  <body id="top">
    <!--Header Start-->
    <c:import url="header.jsp" />
    <!--Header End-->

<!--------------------------Hero Section Start---------------------------->
    <div class="heroSection">
      <p>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fuga, quaerat
        explicabo. Numquam voluptas commodi dicta alias quibusdam sequi, iusto
        qui praesentium, maiores odio laudantium dolor, aspernatur eius ipsum
        quia atque. Vel repellat sed iusto ex dignissimos sint ducimus dolor,
        nesciunt quisquam aliquid, maiores odit recusandae, sit vero nemo rem
        quidem nostrum. Commodi reprehenderit dolore inventore odio error nemo
        voluptatem enim. Beatae harum veniam praesentium omnis possimus officia!
        Quos, rerum? Aspernatur fuga veritatis, aliquam alias modi dolore!
        Incidunt molestiae sit consequatur cumque aperiam ut voluptatum? Dolorem
        laboriosam expedita ipsam alias itaque.
      </p>
      <img src="static/media/images/medicine.png class=" rounded-5" alt="" />
    </div>
<!--------------------------Hero Section End---------------------------->

<!--------------------------User Selection Start---------------------------->
    <div class="redirectSection d-flex justify-content-around mt-5">
      <div class="card rounded-5" style="width: 18rem">
        <img
          src="static/media/images/doctor.png"
          class="card-img-top userSelectionImg"
          alt="..."
        />
        <div class="card-body">
          <a href="signup.do" class="btn patientLoginBtn d-flex justify-content-center"
            >I am a Patient</a
          >
        </div>
      </div>
      <div class="card rounded-5" style="width: 18rem">
        <img
          src="static/media/images/doctor.png"
          class="card-img-top userSelectionImg"
          alt="..."
        />
        <div class="card-body">
          <a href="signup.do" class="btn doctorLoginBtn d-flex justify-content-center"
            >I am a Doctor</a
          >
        </div>
      </div>
      <div class="card rounded-5" style="width: 18rem">
        <img
          src="static/media/images/doctor.png"
          class="card-img-top userSelectionImg"
          alt="..."
        />
        <div class="card-body">
          <a href="signup.do" class="btn manufacturerLoginBtn d-flex justify-content-center"
            >I am a Manufacturer</a
          >
        </div>
      </div>
    </div>
    <div class="d-flex justify-content-center my-5">
          <a href="#" class="btn bookAppointmentBtn rounded-pill px-5">Book Your Appointment</a>
    </div>
<!--------------------------User Selection End---------------------------->

<!--------------------------Why Choose Us Section Start---------------------------->

    <div class="container my-2">
  <h2 class="text-center mb-4">Why Choose Us ?</h2>
  <div class="row">
    <!-- Point 1 -->
    <div class="col-md-6 mb-3">
      <h5>1. Secure & Private</h5>
      <p>
        Your health data is encrypted, confidential, and stored with the highest
        security standards. We follow HIPAA-compliant practices (or region-specific
        equivalents) to protect your sensitive medical information — ensuring only
        you and your authorized providers have access.
      </p>
    </div>
    <!-- Point 2 -->
    <div class="col-md-6 mb-2">
      <h5>2. Mobile-Friendly and Accessible</h5>
      <p>
        Whether you’re on a smartphone, tablet, or desktop — our responsive platform
        lets you manage your health on-the-go. Book appointments, order medicine, or
        check reports anytime, anywhere — no app installation required.
      </p>
    </div>
    <!-- Point 3 -->
    <div class="col-md-6 mb-2">
      <h5>3. Smart Appointment & Prescription System</h5>
      <p>
        No more missed consultations or forgotten doses — we help you stay ahead of
        your health needs.
      </p>
    </div>
    <!-- Point 4 -->
    <div class="col-md-6 mb-2">
      <h5>4. Connected Care for All</h5>
      <p>
        We bring together patients, doctors, and pharmacies under one virtual roof —
        streamlining communication and care delivery. Prescriptions written by doctors
        are directly visible to pharmacies; patients can track every interaction seamlessly.
      </p>
    </div>
    <!-- Point 5 -->
    <div class="col-md-12 mb-2">
      <h5>5. Trusted by Clinics and Professionals</h5>
      <p>
        Already in use by 100+ clinics and pharmacies, our platform is trusted by
        licensed healthcare professionals for everyday operations. You can count on
        verified doctors, real-time responses, and a platform built with real-world
        healthcare in mind.
      </p>
    </div>
  </div>
</div>
<!--------------------------Why Choose Us Section End---------------------------->

<!--------------------------Footer Start---------------------------->
<footer class="text-white py-4" style="background-color:rgb(36, 73, 83);">
  <div class="container">
    <div class="row">
      
      <!-- About the Company -->
      <div class="col-md-4 mb-3">
        <h5>About The Company</h5>
        <ul class="list-unstyled">
          <li><i class="bi bi-shield-lock-fill me-2"></i> Privacy Policy</li>
          <li><i class="bi bi-file-earmark-text-fill me-2"></i> Terms & Conditions</li>
        </ul>
      </div>

      <!-- Navigation Links -->
      <div class="col-md-4 mb-3">
        <h5>Quick Links</h5>
        <ul class="list-unstyled">
          <li><i class="bi bi-house-fill me-2"></i> Home</li>
          <li><i class="bi bi-person-fill me-2"></i> Patient Portal</li>
          <li><i class="bi bi-person-badge-fill me-2"></i> Doctor Portal</li>
          <li><i class="bi bi-capsule-pill me-2"></i> Pharmacy Portal</li>
          <li><i class="bi bi-telephone-fill me-2"></i> Contact Us</li>
        </ul>
      </div>

      <!-- Contact Info -->
      <div class="col-md-4 mb-3">
        <h5>Contact</h5>
        <p>
          <i class="bi bi-envelope-fill me-2"></i> 
          Email: support@healthconnect.com
        </p>
        <p>
          <i class="bi bi-telephone-fill me-2"></i> 
          Phone: +1 (800) 123-4567
        </p>
      </div>

    </div>
  </div>
</footer>

<!--------------------------Footer End---------------------------->

<!--------------------------Login Modal Start---------------------------->

<!--IDK WHYYYYY BUT THIS DOESNT WORKKKKKKKKKKKKKKKKKKKKKKKKKK-->
<div class="modal fade" id="loginModal" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content rounded-4 shadow-lg border-0">
      
      <!-- Branding -->
      <div class="modal-header border-0 flex-column text-center">
        <img src="static/media/images/brandLogo.png" alt="Logo" class="mb-2" style="width: 60px;" />
        <h5 class="fw-bold text-success mb-1">Healthcare Portal</h5>
        <p class="text-muted small mb-0">Login to continue</p>
      </div>

      <!-- Login Form -->
      <div class="modal-body px-4">
        <form action="login.do" method="post" id="login_form">
          <!-- Email -->
          <div class="form-floating mb-3">
            <input type="email" name="email" id="email" class="form-control" placeholder="Enter Existing Email" required />
            <label for="email">Email</label>
          </div>
          
          <!-- Password -->
          <div class="form-floating mb-2">
            <input type="password" name="password" id="password" class="form-control" placeholder="Enter Password" required />
            <label for="password">Password</label>
          </div>
        </form>
      </div>

      <!-- Footer -->
      <div class="modal-footer border-0 d-flex justify-content-between">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="bi bi-x-circle me-2"></i>Close</button>
        <button type="submit" form="login_form" class="btn btn-primary d-flex align-items-center gap-2">
          <i class="bi bi-door-open"></i>
           Login
        </button>
      </div>
    </div>
  </div>
</div>

<!--------------------------Login Modal End---------------------------->





<!--------------------------Scripting Section Starts Here---------------------------->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
