<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Healthcare | Sign Up</title>

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
  
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

  <style>
    body {
      background: linear-gradient(135deg, #b1e3d7, #eeffdb);
      font-family: "Inter", sans-serif;
    }
    .signup-wrapper {
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 2rem;
    }
    .signup-card {
      width: 100%;
      max-width: 900px; /* wider card */
      background: #fff;
      padding: 2rem;
      border-radius: 1rem;
      box-shadow: 0 8px 24px rgba(0,0,0,0.08);
    }
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
    .form-floating > label {
      color: #6c757d;
    }
  </style>

  <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>

  <div class="toast-container position-fixed top-0 start-50 translate-middle-x p-3">
    <div id="name_toast" class="toast align-items-center text-bg-danger border-0">
      <div class="d-flex">
        <div class="toast-body">Invalid User Name...</div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
      </div>
    </div>
  </div>

  <div class="signup-wrapper">
    <div class="signup-card">

      <div class="text-center mb-4">
        <img src="static/media/images/brandLogo.png" alt="Logo" class="brand-logo">
        <h4 class="fw-bold text-success">Healthcare Portal</h4>
        <p class="text-muted small mb-0">Create your account to get started</p>
      </div>

      <form action="signup.do" method="post" id="signup_form">

        <div class="row g-3">
          <div class="col-md-6 form-floating">
            <input type="text" name="name" id="name" required placeholder="Full Name" class="form-control">
            <label for="name" class="px-3">Full Name</label>
          </div>

          <div class="col-md-6 form-floating">
            <input type="email" name="email" id="email" required placeholder="Email" class="form-control">
            <label for="email" class="px-3">Email Address</label>
          </div>

          <div class="col-md-6 form-floating">
            <input type="password" name="password" id="password" required placeholder="Password" class="form-control">
            <label for="password" class="px-3">Password</label>
          </div>

          <div class="col-md-6 form-floating">
            <input type="tel" name="phone" id="phone" required placeholder="Phone Number" class="form-control">
            <label for="phone" class="px-3">Phone Number</label>
          </div>

          <div class="col-md-6 form-floating">
            <input type="date" name="dob" id="dob" required class="form-control">
            <label for="dob" class="px-3">Date of Birth</label>
          </div>

          <div class="col-md-6 form-floating">
            <select name="gender" id="gender" class="form-select">
              <option disabled selected value="0">Select</option>
              <option value="1">Male</option>
              <option value="2">Female</option>
              <option value="3">Other</option>
            </select>
            <label for="gender" class="px-3">Gender</label>
          </div>

          <div class="col-md-12 form-floating">
            <input type="text" name="address" id="address" required placeholder="Address" class="form-control ">
            <label for="address" class="px-3">Address</label>
          </div>

          <div class="col-md-6 form-floating">
            <select disabled name="country_id" id="country_id" class="form-select">
              <option value="1">India</option>
            </select>
            <label for="country_id" class="px-3">Country</label>
          </div>

          <div class="col-md-6 form-floating">
            <select name="userType_id" id="userType_id" class="form-select">
              <option disabled selected value="0">Select</option>
              <option value="1">Patient</option>
              <option value="2">Doctor</option>
              <option value="3">Manufacturer</option>
            </select>
            <label for="userType_id" class="px-3">User Type</label>
          </div>
        </div>

        <div class="d-flex justify-content-center my-3">
          <div class="g-recaptcha" data-sitekey="6LdYlqQrAAAAAHJEOnrrTx8k18y8og_Wbm1bV2hp"></div>
        </div>

        <div class="d-grid">
          
          <button type="submit" class="btn btn-primary btn-lg"><i class="bi bi-box-arrow-in-right mx-2"></i>Sign Up</button>
        </div>

        <p class="text-center mt-3 mb-0">
          Already have an account?
          <a href="login.jsp" class="text-success fw-semibold text-decoration-none">Login here</a>
        </p>
      </form>

    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
