<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Doctor HomePage</title>
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
      href="<c:url value='/static/css/doctorHomePage.css' />"
    />
  </head>
  <body>
    <c:if test="${empty user}">
      <c:redirect url="login.jsp" />
    </c:if>

    <!--Header Start-->
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <img
          style="width: 30px; margin-right: 10px"
          src="static/media/images/brandLogo.png"
          alt=""
        />
        <a class="navbar-brand text-white" href="#">HealthCare</a>
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
                href="doctorHomePage.do"
                >Home</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="#">Appointments</a>
            </li>

            <li class="nav-item">
              <a class="nav-link text-white" href="doctorDashboard.do"
                >Dashboard</a
              >
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
                  <a class="dropdown-item" href="ConfigureDoctorProfile.do"
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

    <!--Welcome Section Start-->
    <div
      class="mx-5 mt-3 mb-3 welcomeSectionDiv d-flex justify-content-between welcome-section"
    >
      <div>
        <h3 class="">Welcome, ${sessionScope.userName}</h3>
        <h5>Doctor Specialization</h5>
        <h6>Years Of Expirience</h6>
        <h6>Consultation Charge : 000</h6>
        <button type="button" class="btn welcomeSectionBtn">
          <i class="bi bi-cloud-plus"></i>
          Add Appointment
        </button>
        <button type="button" class="btn welcomeSectionBtn ms-3">
          <i class="bi bi-shop"></i
          ><a
            href="MedicineMarketDoctor.do"
            style="color: white; text-decoration: none"
          >
            Medicine Market</a
          >
        </button>
        <button type="button" class="btn welcomeSectionBtn ms-3">
          <i class="bi bi-calendar-date"></i>
          Set Schedule
        </button>
      </div>
      <div>
        <img src="static/media/images/user.png" alt="" class="userImg" />
      </div>
    </div>
    <!--Welcome Section End-->

    <!--Notification Section Start-->
    <div class="mb-5 mx-5">
      <h4 class="mb-3 border-bottom pb-2">Notifications</h4>

      <div class="notification-panel" id="notification-panel"></div>
    </div>

    <!--Notification Section End-->

    <!--Upcomining Appointment Starts-->
    <div class="mb-5 mx-5">
      <h4 class="mb-3 border-bottom pb-2">Appointments</h4>

      <!-- Search Bar -->
      <div class="search-box input-group mb-4" style="max-width: 500px">
        <input
          type="text"
          class="form-control"
          placeholder="Search Patient Name"
        />
        <button class="btn-search">Search</button>
      </div>

      <!-- Scrollable Appointments Section -->
      <div class="appointments-container" id="appointments-container"></div> 
      </div>
    </div>
    <!--Upcomining Appointment End-->

    <!--------------------Footer Start--------------------------------------->
    <footer class="text-white py-4" style="background-color: rgb(121, 73, 150)">
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
    <div
      class="modal fade"
      id="configureDoctorModal"
      data-bs-backdrop="static"
      data-bs-keyboard="false"
      tabindex="-1"
      aria-labelledby="staticBackdropLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="staticBackdropLabel">
              Complete Your Profile!
            </h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <h5>
              For performing actions on this website , you need to complete your
              profile... Or else you will not be able to make changes!
            </h5>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Skip for now
            </button>
            <button
              type="button"
              class="btn btn-primary"
              style="background-color: rgb(121, 73, 150)"
            >
              <a
                href="ConfigureDoctorProfile.do"
                style="
                  text-decoration: none;
                  color: white;
                  border: 1px solid rgb(121, 73, 150);
                "
                >Complete Now</a
              >
            </button>
          </div>
        </div>
      </div>
    </div>

    <!--------------------------Configure Manufacturer Modal End---------------------------->

    <!--------------------------Scripting Section Starts Here---------------------------->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
      crossorigin="anonymous"
    ></script>
    <script src="static/js/doctorHomepage.js"></script>
  </body>
</html>
