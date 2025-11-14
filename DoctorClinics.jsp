<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Doctor Clinics</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="<c:url value='/static/css/doctorClinics.css' />" />
  </head>

  <body>
    <!--Header Start-->
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <img style="width: 30px; margin-right: 10px" src="static/media/images/brandLogo.png" alt="" />
        <a class="navbar-brand text-white" href="doctorDashboard.jsp">HealthCare</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
          aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a class="nav-link active text-white" aria-current="page" href="doctorHomePage.jsp">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="#">Add Medicine</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="MedicineStore.jsp">Your Store</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle text-white" href="ConfigureDoctorProfile.do" role="button"
                data-bs-toggle="dropdown" aria-expanded="false">
                Profile
              </a>
              <ul class="dropdown-menu">
                <li>
                  <a class="dropdown-item" href="signup.jsp">Configure Profile</a>
                </li>
                <li>
                  <a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#exampleModal">Logout</a>
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
    <div class="mb-5 mx-5 mt-3">
      <h4 class="mb-3 border-bottom pb-2 clinicTitle"><i class="bi bi-hospital"></i> My Clinics</h4>
      <div class="container my-4 mainClinicDiv" id="mainClinicDiv">
        <div class="row row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 g-4 clinicCardParentDiv"
          id="clinicCardParentDiv"></div>
      </div>
      <!-----------------Store End----------->

      <!--------------------Footer Start--------------------------------------->
      <footer class="footer-full text-white py-4">
        <div class="footer-inner container-fluid px-5">
          <div class="row text-center text-md-start">
            <div class="col-md-4 mb-3">
              <h5>About The Company</h5>
              <ul class="list-unstyled">
                <li><i class="bi bi-shield-lock-fill me-2"></i> Privacy Policy</li>
                <li><i class="bi bi-file-earmark-text-fill me-2"></i> Terms & Conditions</li>
              </ul>
            </div>

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

            <div class="col-md-4 mb-3">
              <h5>Contact</h5>
              <p><i class="bi bi-envelope-fill me-2"></i> support@healthconnect.com</p>
              <p><i class="bi bi-telephone-fill me-2"></i> +1 (800) 123-4567</p>
            </div>
          </div>
        </div>
      </footer>

      <!--Footer end-->

      <!--Confirming Clinic removal modal-->
      <div class="modal fade " id="confirmingClinicRemoveModal" tabindex="-1"
        aria-labelledby="confirmingClinicRemoveModal" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-body">
              <h5>Are You Sure you want to remove this Clinic?</h5>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" id="rejectClinicRemoveBtn"
                data-bs-dismiss="modal">No</button>
              <button type="button" id="confirmClinicRemoveBtn" class="btn ">Yes</button>
            </div>
          </div>
        </div>
      </div>
      <!---Confirming Clinic removal modal->


      
    <!--Add Clinic Modal Start-->

      <div class="modal fade" id="setScheduleModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
          <div class="modal-content"
            style="border-radius: 12px; overflow: hidden; box-shadow: 0 8px 30px rgba(0, 0, 0, 0.2);">
            <div class="modal-header"
              style="background-color: rgb(121, 73, 150); color: white; border-bottom: none; padding: 1.5rem;">
              <h5 class="modal-title" id="setScheduleModalLabel" style="font-weight: 600; font-size: 1.5rem;">
                <i class="bi bi-calendar-plus me-2"></i> Set Clinic Schedule
              </h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"
                style="filter: invert(1);"></button>
            </div>
            <div class="modal-body" style="padding: 2rem;">
              <form action="setSchedule.do" method="post" id="setScheduleForm">
                <div class="form-group mb-4 position-relative">
                  <label for="startTime" class="form-label-custom-purple"
                    style="color: rgb(121, 73, 150); font-weight: 500; margin-bottom: 0.5rem; display: block;">Start
                    Time</label>
                  <div class="input-group">
                    <span class="input-group-text"
                      style="background-color: #f0e6f4; border: 1px solid rgb(121, 73, 150); border-right: none; color: rgb(121, 73, 150); border-radius: 8px 0 0 8px;">
                      <i class="bi bi-clock"></i>
                    </span>
                    <input type="time" name="startTime" id="startTime" class="form-control"
                      style="border: 1px solid rgb(121, 73, 150); border-radius: 0 8px 8px 0; padding: 0.75rem 1rem; font-size: 1rem; color: #333;"
                      required />
                  </div>
                </div>

                <div class="form-group mb-4 position-relative">
                  <label for="endTime" class="form-label-custom-purple"
                    style="color: rgb(121, 73, 150); font-weight: 500; margin-bottom: 0.5rem; display: block;">End
                    Time</label>
                  <div class="input-group">
                    <span class="input-group-text"
                      style="background-color: #f0e6f4; border: 1px solid rgb(121, 73, 150); border-right: none; color: rgb(121, 73, 150); border-radius: 8px 0 0 8px;">
                      <i class="bi bi-clock-history"></i>
                    </span>
                    <input type="time" name="endTime" id="endTime" class="form-control"
                      style="border: 1px solid rgb(121, 73, 150); border-radius: 0 8px 8px 0; padding: 0.75rem 1rem; font-size: 1rem; color: #333;"
                      required />
                  </div>
                </div>

                <div class="form-group mb-4 position-relative">
                  <label for="patientLimit" class="form-label-custom-purple"
                    style="color: rgb(121, 73, 150); font-weight: 500; margin-bottom: 0.5rem; display: block;">Patient
                    Limit</label>
                  <div class="input-group">
                    <span class="input-group-text"
                      style="background-color: #f0e6f4; border: 1px solid rgb(121, 73, 150); border-right: none; color: rgb(121, 73, 150); border-radius: 8px 0 0 8px;">
                      <i class="bi bi-person-lines-fill"></i>
                    </span>
                    <input type="number" name="patientLimit" id="patientLimit" class="form-control"
                      placeholder="e.g., 20 patients per session" required
                      style="border: 1px solid rgb(121, 73, 150); border-radius: 0 8px 8px 0; padding: 0.75rem 1rem; font-size: 1rem; color: #333;" />
                  </div>
                </div>

              </form>
            </div>
            <div class="modal-footer" style="border-top: none; padding: 1.5rem 2rem; background-color: #f8f9fa;">
              <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal"
                style="border-color: #ced4da; color: #6c757d; border-radius: 8px; padding: 0.75rem 1.5rem;">
                <i class="bi bi-x-circle me-2"></i> Close
              </button>
              <button type="submit" form="setScheduleForm" class="btn btn-primary" id="setScheduleSubmitBtn" data-bs-dismiss="modal"
                style="background-color: rgb(121, 73, 150); border-color: rgb(121, 73, 150); color: white; border-radius: 8px; padding: 0.75rem 1.5rem; transition: all 0.2s ease;">
                <i class="bi bi-check-circle me-2"></i> Set Schedule
              </button>
            </div>
          </div>
        </div>
      </div>
      <!--Add Clinic Modal End-->

      <!---Show Schedule Start-->
       <div class="schedule-container mainScheduleShowTable noDisplay">
        <div class="schedule-header">
            <h2>📅 Clinic Schedule</h2>
            <p>Daily Appointment Slots</p>
        </div>
        
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th class="checkbox-cell">Select</th>
                        <th>S.No</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th>Patient Limit</th>
                    </tr>
                </thead>
                <tbody class="scheduleTBodyParent">
                </tbody>
            </table>
        </div>
        
        <div class="button-container">
            <button class="delete-btn">🗑️ Delete Selected</button>
            <button class="close-btn">✖️ Close</button>
        </div>
    </div>
      <!---Show Schedule End-->

      <!--------------------------Scripting Section Starts Here---------------------------->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
      <script src="static/js/doctorClinic.js"></script>
  </body>

  </html>