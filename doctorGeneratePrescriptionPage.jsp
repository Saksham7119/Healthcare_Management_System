<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Doctor Prescription</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="<c:url value='/static/css/doctorGeneratePrescriptionPage.css' />" />
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
    <div class="mb-5 mx-5 mt-3">
      <h4 class="mb-3 border-bottom pb-2 prescriptionTitle"><i class="bi bi-file-earmark"></i> Patient Prescription</h4>
           <div class="prescription-container mb-3">

        <div class="prescription-body ">
            <form id="prescriptionForm">
                <!-- Hidden Fields -->
                <div class="hidden-fields">
                    <input type="hidden" name="prescription_id" value="">
                    <input type="hidden" name="appointment_id" id="appointmentIdInput" value="">
                </div>

                <!-- Patient Information Section -->
                <div class="section">
                    <h2 class="section-title">Patient Information</h2>
                    <div class="form-grid">
                        <div class="form-group">
                            <label for="patient_name">Patient Name</label>
                            <input type="text" id="patient_name" name="patient_name" value="" readonly>
                        </div>
                        <div class="form-group">
                            <label for="age">Age</label>
                            <input type="number" id="age" name="age" value="45" readonly>
                        </div>
                        <div class="form-group">
                            <label for="gender">Gender</label>
                            <input type="text" id="gender" name="gender" value="" readonly>
                        </div>
                        <div class="form-group">
                            <label for="height">Height (cm)</label>
                            <input type="number" id="height" name="height" value="" readonly>
                        </div>
                        <div class="form-group">
                            <label for="weight">Weight (kg)</label>
                            <input type="number" id="weight" name="weight" value="" readonly>
                        </div>
                        <div class="form-group">
                            <label for="bp">Blood Pressure</label>
                            <input type="text" id="bp" name="bp" value="" readonly>
                        </div>
                    </div>
                    <div class="form-grid" style="margin-top: 15px;">
                        <div class="form-group" style="grid-column: 1 / -1;">
                            <label for="disease">Diagnosed With</label>
                            <input type="text" id="disease" name="disease" value="Hypertension, Type 2 Diabetes" readonly>
                        </div>
                    </div>
                </div>

                <!-- Prescription Details Section -->
                <div class="section">
                    <h2 class="section-title">Prescription Details</h2>
                    
                    <div class="form-group descriptionSection">
                        <label for="description">Description</label>
                        <textarea id="description" name="description" placeholder="Enter detailed description and instructions"></textarea>
                    </div>

                    <div class="medicine-row">
                        <div class="medicine-grid">
                            <div class="form-group">
                                <label for="medicine">Medicine</label>
                                <select id="medicine" name="medicineDenomination">
                                    <option value="">Select Medicine</option>
                                    <c:forEach var="med" items="${applicationScope.medicines}">
                                      <c:forEach var="mf" items="${med.medicineFormats}">
                                          <option value="${mf.medicineDenomination.medicineDenominationId}" >
                                              ${med.name} - 
                                              ${mf.format.name} - 
                                              ${mf.medicineDenomination.denomination}
                                              ${mf.medicineDenomination.medicineUnit.unit}
                                          </option>
                                      </c:forEach>
                                  </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="day_frequency">Day Frequency</label>
                                <select id="day_frequency" name="day_frequency">
                                    <option value="">Select</option>
                                    <c:forEach var="dayFreq" items="${applicationScope.dayFreq}">
                                      <option value="${dayFreq.dayFrequencyId}">${dayFreq.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="span_frequency">Span Frequency</label>
                                <select id="span_frequency" name="span_frequency">
                                    <option value="">Select</option>
                                    <c:forEach var="spanFreq" items="${applicationScope.spanFreq}">
                                      <option value="${spanFreq.spanFrequencyId}">${spanFreq.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="consumptionInfo">
                            <div class="form-group full-width">
                                <label>Consumption Status</label>
                                <div class="checkbox-group">
                                    <div class="checkbox-item">
                                        <input type="checkbox" id="before_meal" name="consumption_status" value="0">
                                        <label for="before_meal">Before Meal</label>
                                    </div>
                                    <div class="checkbox-item">
                                        <input type="checkbox" id="after_meal" name="consumption_status" value="1">
                                        <label for="after_meal">After Meal</label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group full-width">
                                <label>Course Duration</label>
                                <div class="checkbox-group">
                                    <div class="checkbox-item">
                                        <input type="number" id="morning" name="course_duration">
                                        <label for="morning">in days</label>
                                    </div>
                                </div>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Tests Section -->
                <div class="section">
                    <h2 class="section-title">Recommended Tests</h2>
                    <div class="form-group">
                        <label>Select Tests</label>
                        <div class="checkbox-group">
                          <c:forEach var="test" items="${applicationScope.tests}">
                            <div class="checkbox-item">
                              <input type="checkbox" id="${test.name}" name="${test.name}" value="${test.testId}">
                              <label for="blood_test">${test.name}</label>
                            </div>
                          </c:forEach>
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 15px;">
                        <label for="test_notes">Additional Test Instructions</label>
                        <textarea id="test_notes" name="test_notes" placeholder="Enter any specific instructions for the tests"></textarea>
                    </div>
                </div>

                <!-- Save Button -->
                <div class="save-button-container">
                    <button type="button" class="save-button" id="savePrescriptionBtn">Save Prescription</button>
                </div>
            </form>
        </div>
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


      <!--------------------------Scripting Section Starts Here---------------------------->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>
      <script src="static/js/doctorGeneratePrescriptionPage.js"></script>
    </body> 


  </html>