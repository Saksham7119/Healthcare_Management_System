<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Patient Portal</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
    />
    <link
      rel="stylesheet"
      href="<c:url value='/static/css/findDoctorForPatientPage.css' />"
    />
  </head>

  <body>
    <!--Header Start-->
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <img
          style="width: 30px; margin-right: 10px"
          src="static/media/images/brandLogo.png"
          alt=""
        />
        <a class="navbar-brand text-white" href="">HealthCare</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNavDropdown"
          aria-controls="navbarNavDropdown"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a
                class="nav-link active text-white"
                aria-current="page"
                href="PatientHomePage.do"
                >Home</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link text-white">My Appointments</a>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle text-white"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Profile
              </a>
              <ul class="dropdown-menu">
                <li>
                  <a class="dropdown-item" href="signup.jsp"
                    >Configure Profile</a
                  >
                </li>
                <li>
                  <a class="dropdown-item" href="logout.do">Logout</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!--Header End-->

    <!--In here we neeD to fix that if there are no cards then the footer moves up , we need to fix it in the bottom-->

    <!-----------------Store Start----------->
    <!-- <div class="mb-5 mx-5 mt-3">
      <h4 class="mb-3 border-bottom pb-2">Your Store</h4>
      <div class="container my-4 mainStoreDiv" id="mainStoreDiv">
        <div class="row row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4 medicineCardParentDiv" id="medicineCardParentDiv">
      </div>
    </div> -->

    <div class="store-container">
      <div class="store-header">
        <h4><i class="bi bi-search-heart me-2"></i>Find Your Doctor</h4>
      </div>
      <div class="showDoctorDiv" id="showDoctorDiv">
        <div class="findDoctorPanel d-flex" id="findDoctorPanel">
          <div>
            <input
              type="text"
              name="searchDoctorInput"
              id="searchDoctorInput"
              class="searchDoctorInput"
              placeholder="Search here..."
            />
          </div>
          <div class="d-flex searchDoctorInputInfo">
            <i class="bi bi-info-circle px-1"></i><p>Search Clinic By Clinic Name, Doctor Name, City or Department!</p>
          </div>
        </div>
        <div class="doctorsCardParentDiv" id="doctorsCardParentDiv"></div>
      </div>
    </div>
    <!--Store End-->
    <!-----------------Store End----------->

    <!--------------------Footer Start--------------------------------------->
    <footer class="text-white py-4" style="background-color: green">
      <div class="container">
        <div class="row">
          <div class="col-md-4 mb-3">
            <h5>About The Company</h5>
            <ul class="list-unstyled">
              <li>
                <i class="bi bi-shield-lock-fill me-2"></i> Privacy Policy
              </li>
              <li>
                <i class="bi bi-file-earmark-text-fill me-2"></i> Terms &
                Conditions
              </li>
            </ul>
          </div>

          <div class="col-md-4 mb-3">
            <h5>Quick Links</h5>
            <ul class="list-unstyled">
              <li><i class="bi bi-house-fill me-2"></i> Home</li>
              <li><i class="bi bi-person-fill me-2"></i> Patient Portal</li>
              <li>
                <i class="bi bi-person-badge-fill me-2"></i> Doctor Portal
              </li>
              <li><i class="bi bi-capsule-pill me-2"></i> Pharmacy Portal</li>
              <li><i class="bi bi-telephone-fill me-2"></i> Contact Us</li>
            </ul>
          </div>

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
    <!--Footer end-->

    <%--
    <c:if test="${empty user}">
      <c:redirect url="login.do" />
    </c:if>
    --%>

    <!--------------------------Scripting Section Starts Here---------------------------->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
      crossorigin="anonymous"
    ></script>
    <script src="static/js/findDoctorsForPatient.js"></script>
  </body>
</html>
