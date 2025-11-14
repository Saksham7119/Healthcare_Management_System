<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Manage Your Profile</title>
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
      href="<c:url value='/static/css/doctorConfigureProfile.css' />"
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
                href="ConfigureDoctorProfile.do"
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
                  <a class="dropdown-item">Logout</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!--Header End-->
    <!-----------------Manage Profile Title Start----------->
    <div class="mb-5 mx-5 mt-3">
      <h4 class="mb-3 border-bottom pb-2 profileTitle">
        <i class="bi bi-person-lines-fill"></i>
        Manage Your Profile
      </h4>
    </div>
    <!-----------------Manage Profile Title End----------->

    <!-----------------Manage Profile Start----------->
    <div class="mainDiv mx-5 profile-container mb-5">

         <div class="profile-body">
                <form action="configureDoctor.do" method="post" id="configureDoctorForm">
                    <div class="profile-section">
                        <h2 class="section-title">
                            <i class="fas fa-user section-icon"></i>
                            Personal Information
                        </h2>

                        <div class="form-row-custom">
                            <div class="form-group-custom">
                                <div class="label-wrapper">
                                    <p class="label-text">Full Name</p>
                                </div>
                                <input type="text" class="input-custom" placeholder="Enter your full name" />
                            </div>

                            <div class="form-group-custom">
                                <div class="label-wrapper">
                                    <p class="label-text">Date of Birth</p>
                                </div>
                                <input type="date" class="input-custom" placeholder="DD/MM/YYYY" />
                            </div>
                        </div>

                        <div class="form-row-custom">
                            <div class="form-group-custom">
                                <div class="label-wrapper">
                                    <p class="label-text">Gender</p>
                                </div>
                                <input type="text" class="input-custom" placeholder="Male / Female / Other" />
                            </div>

                            <div class="form-group-custom">
                                <div class="label-wrapper">
                                    <p class="label-text">Contact Number</p>
                                </div>
                                <input type="text" class="input-custom" placeholder="+91 XXXXX XXXXX" />
                            </div>
                        </div>

                        <div class="form-group-custom">
                            <div class="label-wrapper">
                                <p class="label-text">Profile Photo</p>
                            </div>
                            <div class="file-upload-wrapper">
                                <input type="file" src="" alt="" />
                                <div class="file-upload-content">
                                    <i class="fas fa-cloud-upload-alt file-upload-icon"></i>
                                    <p class="file-upload-text">Click to upload!</p>
                                    <p class="file-upload-hint">PNG, JPG or JPEG (Max 5MB)</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="profile-section">
                        <h2 class="section-title">
                            <i class="fas fa-stethoscope section-icon"></i>
                            Professional Information
                        </h2>

                        <div class="form-group-custom">
                            <div class="label-wrapper">
                                <p class="label-text">Date You Started Practicing Medicine (Required)</p>
                            </div>
                            <input name="practiceStartDate" id="practiceStartDate" type="date" class="input-custom" placeholder="DD/MM/YYYY" required/>
                        </div>

                        <div class="form-group-custom">
                            <div class="label-wrapper">
                                <p class="label-text">About Yourself (Required)</p>
                            </div>
                            <textarea 
                              name="aboutDoctor"
                              id="aboutDoctor"
                                class="textarea-custom" 
                                placeholder="Share your experience, approach to patient care, specializations, and what makes your practice unique..."
                                maxlength="300" required
                            ></textarea>
                        </div>

                        <div class="form-row-custom">
                            <div class="form-group-custom">
                                <div class="label-wrapper">
                                    <p class="label-text">Your Degree (Required)</p>
                                </div>
                                <select name="" id="" class="select-custom" required>
                                    <option value="0">Your Degree</option>
                                    <c:forEach var="degrees" items="${applicationScope.degrees}" varStatus="status">
                                      <option value="${status.index+1}">${degrees.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group-custom">
                                <div class="label-wrapper">
                                    <p class="label-text">Any Specializations? (Required)</p>
                                </div>
                                <select name="" id="" class="select-custom" required>
                                    <option value="0">Your Specialization</option>
                                    <c:forEach var="specializations" items="${applicationScope.specializations}" varStatus="status">
                                      <option value="${status.index+1}">${specializations.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group-custom">
                            <div class="label-wrapper">
                                <p class="label-text">Your Schedule</p>
                            </div>
                            <textarea 
                                name="" 
                                id="" 
                                class="textarea-custom" 
                                placeholder="Example:&#10;Monday - Friday: 9:00 AM - 5:00 PM&#10;Saturday: 9:00 AM - 1:00 PM&#10;Sunday: Closed"
                                maxlength="300"
                            ></textarea>
                        </div>
                    </div>

                    <div class="submit-section">
                        <button type="submit" class="submit-btn-custom">
                            <i class="bi bi-bookmark-check"></i>
                            Save Profile
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

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
    <!-----------------Manage Profile End----------->
  </body>
</html>
