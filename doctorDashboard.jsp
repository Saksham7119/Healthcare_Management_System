<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Doctor Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="<c:url value='/static/css/doctorDashboard.css' />" />
  </head>

  <body>
    <!--Header Start-->
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <img style="width: 30px; margin-right: 10px" src="static/media/images/brandLogo.png" alt="" />
        <a class="navbar-brand text-white" href="#">HealthCare</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
          aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a class="nav-link text-white" aria-current="page" href="doctorHomePage.do">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="#">Appointments</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white active" href="doctorDashboard.do">Dashboard</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown"
                aria-expanded="false">
                Profile
              </a>
              <ul class="dropdown-menu">
                <li>
                  <a class="dropdown-item" href="#">Configure Profile</a>
                </li>
                <li>
                  <a class="dropdown-item">Logout</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!--Header End-->

    <!--Welcome Section Start-->
    <h3 class="mb-3 mx-5 mt-3 border-bottom pb-2">
      <strong>Your Dashboard</strong>
    </h3>
    <div class="mx-5 mt-2 welcomeSectionDiv d-flex justify-content-between">
      <div>
        <h4 class="">Welcome, ${sessionScope.userName}</h4>
        <h5>Doctor Specialization</h5>
        <h6>Years Of Expirience</h6>
        <button type="button" class="btn welcomeSectionBtn" data-bs-toggle="modal" data-bs-target="#addClinicModal">
          Add Clinic
        </button>
        <button type="button" class="btn welcomeSectionBtn">
          <a href="DoctorClinic.do" style="color: white; text-decoration: none">View Clinics</a>
        </button>
      </div>
      <div>
        <img src="static/media/images/user.png" alt="" class="userImg" />
      </div>
    </div>
    <!--Welcome Section End-->

    <!--Statistics Start-->
    <div class="m-5">
      <h4 class="mb-3 border-bottom pb-2">Your Statistics</h4>
      <!-- Statistics Section -->
      <div class="row g-3">
        <div class="col-md-3 col-6">
          <div class="card shadow-sm border-0 h-100 stats-card">
            <div class="card-body text-center">
              <h6 class="">Patient Attended :</h6>
              <h4 class="fw-bold">10</h4>
            </div>
          </div>
        </div>

        <div class="col-md-3 col-6">
          <div class="card shadow-sm border-0 h-100 stats-card">
            <div class="card-body text-center">
              <h6 class="">Prescription Pending :</h6>
              <h4 class="fw-bold">3</h4>
            </div>
          </div>
        </div>

        <div class="col-md-3 col-6">
          <div class="card shadow-sm border-0 h-100 stats-card">
            <div class="card-body text-center">
              <h6 class="">Appointments Cancelled :</h6>
              <h4 class="fw-bold">03</h4>
            </div>
          </div>
        </div>

        <div class="col-md-3 col-6">
          <div class="card shadow-sm border-0 h-100 stats-card">
            <div class="card-body text-center">
              <h6 class="">Appointments Rescheduled :</h6>
              <h4 class="fw-bold">02</h4>
            </div>
          </div>
        </div>

        <div class="col-md-3 col-6">
          <div class="card shadow-sm border-0 h-100 stats-card">
            <div class="card-body text-center">
              <h6 class="">Total Patient :</h6>
              <h4 class="fw-bold">17</h4>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--Statistics End-->

    <!--Appointment History Starts-->
    <div class="mb-5 mx-5">
      <h4 class="mb-3 border-bottom pb-2">Appointments History</h4>

      <!-- Search Bar -->
      <div class="search-box input-group mb-4" style="max-width: 500px">
        <input type="text" class="form-control" placeholder="Search Patient Name" />
        <button class="btn-search">Search</button>
      </div>

      <!-- Scrollable Appointments Section -->
      <div class="appointments-container">
        <!-- Appointment Card -->
        <div class="appointment-card mb-3">
          <div class="row align-items-center">
            <div class="col-auto">
              <img src="static/media/images/user.png" alt="patient" />
            </div>
            <div class="col">
              <div class="row">
                <div class="col">
                  <strong>Patient Name</strong>
                </div>
                <div class="col text-end">Age</div>
              </div>
              <div class="row">
                <div class="col">Diagnosis</div>
                <div class="col text-end">Gender</div>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-between gap-2 flex-wrap">
            <div>
              <button class="btn-custom">View Patient History</button>
              <button class="btn-custom">Edit Prescription</button>
              <button class="btn-custom">Delete</button>
            </div>
            <div>
              <span class="status-badge status-attended">Status : Attended</span>
            </div>
          </div>
        </div>

        <!-- Another Card -->
        <div class="appointment-card mb-3">
          <div class="row align-items-center">
            <div class="col-auto">
              <img src="static/media/images/user.png" alt="patient" />
            </div>
            <div class="col">
              <div class="row">
                <div class="col">
                  <strong>Patient Name</strong>
                </div>
                <div class="col text-end">Age</div>
              </div>
              <div class="row">
                <div class="col">Diagnosis</div>
                <div class="col text-end">Gender</div>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-between gap-2 flex-wrap">
            <div>
              <button class="btn-custom">View Patient History</button>
              <button class="btn-custom">Edit Prescription</button>
              <button class="btn-custom">Delete</button>
            </div>
            <div>
              <span class="status-badge status-attended">Status : Attended</span>
            </div>
          </div>
        </div>

        <!-- Another Card -->
        <div class="appointment-card mb-3">
          <div class="row align-items-center">
            <div class="col-auto">
              <img src="static/media/images/user.png" alt="patient" />
            </div>
            <div class="col">
              <div class="row">
                <div class="col">
                  <strong>Patient Name</strong>
                </div>
                <div class="col text-end">Age</div>
              </div>
              <div class="row">
                <div class="col">Diagnosis</div>
                <div class="col text-end">Gender</div>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-between gap-2 flex-wrap">
            <div>
              <button class="btn-custom">View Patient History</button>
              <button class="btn-custom">Edit Prescription</button>
              <button class="btn-custom">Delete</button>
            </div>
            <div>
              <span class="status-badge status-attended">Status : Attended</span>
            </div>
          </div>
        </div>
        <!-- Another Card -->
        <div class="appointment-card mb-3">
          <div class="row align-items-center">
            <div class="col-auto">
              <img src="static/media/images/user.png" alt="patient" />
            </div>
            <div class="col">
              <div class="row">
                <div class="col">
                  <strong>Patient Name</strong>
                </div>
                <div class="col text-end">Age</div>
              </div>
              <div class="row">
                <div class="col">Diagnosis</div>
                <div class="col text-end">Gender</div>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-between gap-2 flex-wrap">
            <div>
              <button class="btn-custom">View Patient History</button>
              <button class="btn-custom">Edit Prescription</button>
              <button class="btn-custom">Delete</button>
            </div>
            <div>
              <span class="status-badge status-attended">Status : Attended</span>
            </div>
          </div>
        </div>
        <!-- Another Card -->
        <div class="appointment-card mb-3">
          <div class="row align-items-center">
            <div class="col-auto">
              <img src="static/media/images/user.png" alt="patient" />
            </div>
            <div class="col">
              <div class="row">
                <div class="col">
                  <strong>Patient Name</strong>
                </div>
                <div class="col text-end">Age</div>
              </div>
              <div class="row">
                <div class="col">Diagnosis</div>
                <div class="col text-end">Gender</div>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-between gap-2 flex-wrap">
            <div>
              <button class="btn-custom">View Patient History</button>
              <button class="btn-custom">Edit Prescription</button>
              <button class="btn-custom">Delete</button>
            </div>
            <div>
              <span class="status-badge status-attended">Status : Attended</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--Appointment History End-->

    <!--Your Schedule Starts-->
    <div class="mb-5 mx-5">
      <h4 class="mb-3 border-bottom pb-2">Your Schedule</h4>
      <textarea name="scheduleBox" id="setScheduleArea" cols="190" rows="10"
        style="resize: none; padding: 20px"></textarea>
    </div>
    <!--Your Schedule End-->

    <!--Add Clinic Modal Start-->

    <div class="modal fade" id="addClinicModal" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Add Your Clinic</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form action="addClinic.do" method="post" id="clinicForm" enctype="multipart/form-data">
              <div class="form-group">
                <i class="bi bi-hospital input-icon"></i>
                <input type="text" name="clinicName" id="clinicName" class="form-control-custom"
                  placeholder="Enter clinic name" required />
                <label for="clinicName" class="form-label-custom">Clinic Name</label>
              </div>

              <div class="form-group">
                <i class="bi bi-geo-alt input-icon"></i>
                <input type="text" name="clinicAddress" id="clinicAddress" class="form-control-custom"
                  placeholder="Enter complete address" required />
                <label for="clinicaddress" class="form-label-custom">Address</label>
              </div>

              <div class="form-row">
                <div class="form-group">
                  <i class="bi bi-telephone input-icon"></i>
                  <input type="tel" name="clinicContact" id="clinicContact" class="form-control-custom"
                    placeholder="+91 XXXXX XXXXX" required />
                  <label for="clinicContact" class="form-label-custom">Phone Number</label>
                </div>

                <!-- <div class="form-group">
                  <i class="bi bi-pin-map input-icon"></i>
                  <input type="text" name="clinicLocation" id="clinicLocation" class="form-control-custom"
                    placeholder="City, State" />
                  <label for="clinicLocation" class="form-label-custom">Location</label>
                </div> -->

                <div class="form-group">
                  <i class="bi bi-pin-map input-icon"></i>
                  <select name="clinicCity" id="clinicCity" class="form-control-custom" required>
                    <option value="" disabled selected>
                      Select City Of Your Clinic
                    </option>
                  </select>
                  <label for="city" class="form-label-custom">City</label>
                </div>
              </div>

              <!-- <div class="form-group">
            <i class="bi bi-capsule input-icon"></i>
            <select name="days" id="days" class="form-control-custom" multiple size="5" required>
              <option value="0" disabled>Select Clinic Days</option>
            </select>
            <label for="specializations" class="form-label-custom">Days Clinic Will Open?</label>
            <small class="form-text-helper">Hold Ctrl (Cmd on Mac) to select multiple</small>
          </div> -->

              <div class="form-group">
                <div class="checkbox-group">
                  <label class="form-label-custom" style="
                      position: relative;
                      top: 0;
                      left: 0;
                      margin-bottom: 1rem;
                    ">Days clinic will open?</label>
                  <div class="checkbox-grid" id="days"></div>
                </div>
              </div>

              <div class="form-group">
                <i class="bi bi-image input-icon"></i>
                <input type="file" name="clinicImage" id="clinicImage" class="form-control-custom file-input"
                  accept="image/*" />
                <label for="clinicImage" class="form-label-custom">Clinic Image</label>
              </div>

              <div class="form-group">
                <i class="bi bi-card-text input-icon" style="top: 2rem"></i>
                <textarea name="aboutClinic" id="aboutClinic" class="form-control-custom"
                  placeholder="Tell us about your clinic, specialties, facilities..." required></textarea>
                <label for="aboutClinic" class="form-label-custom">About My Clinic</label>
              </div>

              <div class="section-divider">
                <span><i class="bi bi-cash-coin me-2"></i>Consultation
                  Charges</span>
              </div>

              <div class="form-row">
                <div class="form-group">
                  <i class="bi bi-currency-rupee input-icon"></i>
                  <input type="number" name="firstVisitCharges" id="firstVisitCharges" class="form-control-custom"
                    placeholder="0" min="0" required />
                  <label for="firstVisitCharges" class="form-label-custom">First Visit Charges</label>
                </div>

                <div class="form-group">
                  <i class="bi bi-currency-rupee input-icon"></i>
                  <input type="number" name="nextVisitCharges" id="nextVisitCharges" class="form-control-custom"
                    placeholder="0" min="0" />
                  <label for="nextVisitCharges" class="form-label-custom">Follow-up Charges</label>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn-custom btn-secondary-custom" data-bs-dismiss="modal">
              <i class="bi bi-x-circle"></i>
              Close
            </button>
            <button type="submit" form="clinicForm" class="btn-custom btn-primary-custom">
              <i class="bi bi-check-circle"></i>
              Add Clinic
            </button>
          </div>
        </div>
      </div>
    </div>
    <!--Add Clinic Modal End-->

    <!--------------------------Scripting Section Starts Here---------------------------->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
      crossorigin="anonymous"></script>
    <script src="static/js/doctorDashboard.js"></script>
  </body>

  </html>