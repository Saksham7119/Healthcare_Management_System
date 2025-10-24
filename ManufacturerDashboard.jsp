<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Manufacturer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" />
    <link rel="stylesheet" href="<c:url value='/static/css/ManufacturerDashboard.css' />" />
  </head>

  <body>

    <c:if test="${empty user}">
      <c:redirect url="login.jsp" />
    </c:if>

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
              <a class="nav-link active text-white" aria-current="page" href="ManufacturerDashboard.do">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="#">Add Medicine</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="MedicineStore.do">Your Store</a>
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
    <h3 class="mb-4 mx-5 mt-3 border-bottom pb-2">
      <strong>Your Dashboard</strong>
    </h3>
    <div class="mx-5 mt-2 welcomeSectionDiv d-flex justify-content-between">
      <div>
        <h4 class="">
          <h4>Welcome, ${sessionScope.userName}</h4>
          <h5>
            Empower healthcare by managing and listing high quality medicines.
          </h5>
          <button type="button" class="btn welcomeSectionBtn" data-bs-toggle="modal" data-bs-target="#addMedicineModal">
            Add Medicine
          </button>
          <button type="button" class="btn welcomeSectionBtn ms-3">
            <a href="MedicineStore.do" style="text-decoration: none; color: white;"> View Medicine</a>
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

      <div class="notification-panel" id="notification-panel">

      </div>
    </div>

    <!--Notification Section End-->

    <!--Medicine Statistics Start-->
    <div class="m-5">
      <h4 class="mb-3 border-bottom pb-2">Medicine Statistics</h4>
      <div class="row g-3">
        <div class="col-md-3 col-6">
          <div class="card shadow-sm border-0 h-100">
            <div class="card-body text-center">
              <h6 class="text-muted">Total Medicines Listed</h6>
              <h4 class="fw-bold text-primary">27</h4>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-6">
          <div class="card shadow-sm border-0 h-100">
            <div class="card-body text-center">
              <h6 class="text-muted">Medicines In Stock</h6>
              <h4 class="fw-bold text-success">22</h4>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-6">
          <div class="card shadow-sm border-0 h-100">
            <div class="card-body text-center">
              <h6 class="text-muted">Medicines Out of Stock</h6>
              <h4 class="fw-bold text-danger">5</h4>
            </div>
          </div>
        </div>
        <div class="col-md-3 col-6">
          <div class="card shadow-sm border-0 h-100">
            <div class="card-body text-center">
              <h6 class="text-muted">Last Updated</h6>
              <h4 class="fw-bold text-dark">dd/mm/yy</h4>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--Medicine Statistics End-->

    <!--------------------Footer Start--------------------------------------->
    <footer class="text-white py-4" style="background-color: rgb(45, 109, 131)">
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

    <%-- <c:if test="${empty user}">
      <c:redirect url="signup.do" />
      </c:if>
      --%>

      <!--------------------------Configure Manufacturer Modal Start---------------------------->
       <div class="modal" id="configureManufacturerModal" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title text-primary">Fill Your Details</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <form action="configureManufacturer.do" method="post" id="configureManufacturerForm">
                <div class="form-floating mb-3">
                  <input type="text" name="companyName" id="companyName" required placeholder="Enter Your Company Name"
                    class="form-control" />
                  <label for="companyName">Company Name</label>
                </div>
                <div class="form-floating mb-3">
                  <input type="email" name="companyEmail" id="companyEmail" required placeholder="Enter Company Email"
                    class="form-control" />
                  <label for="companyEmail">Company Email</label>
                </div>
                <div class="form-floating mb-3">
                  <input type="text" name="companyWebsite" id="companyWebsite" placeholder="Company Website Link"
                    class="form-control" />
                  <label for="companyWebsite">Website Link(if any)</label>
                </div>
                <div class="form-floating mb-3">
                  <input type="text" name="licenceNumber" id="licenceNumber" placeholder="Licence Number"
                    class="form-control" />
                  <label for="licenceNumber">Licence Number </label>
                </div>

                <div class="modal-footer">
                  <button type="submit" class="btn btn-primary">Submit</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <!--------------------------Configure Manufacturer Modal End---------------------------->

      <!-- ------------------------Add Medicine Modal Start---------------------------->
      <!-- <div class="modal" id="addMedicineModal" tabindex="-1">
        <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">

          <div class="modal-content shadow-lg rounded-3 border-0">
            <div class="modal-header" style="background-color: rgb(45, 109, 131)">
              <h5 class="modal-title text-white">Add New Medicine</h5>
              <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                aria-label="Close"></button>
            </div>
            <div class="modal-body p-4">
              <form id="addMedicineForm" class="needs-validation" novalidate action="addMedicine.do" method="post">
                <div class="addMedicineDiv">

                  <div class="row g-3 mb-3">
                    <div class="col-md-6">
                      <div class="form-floating">
                        <input type="text" name="medicineName" id="medicineName" required
                          placeholder="Enter Medicine Name" class="form-control" />
                        <label for="medicineName">Medicine Name</label>
                      </div>
                    </div>

                    <div class="col-md-6">
                      <div class="form-floating">
                        <input type="text" name="medicineDescription" id="medicineDescription" required
                          placeholder="Enter Medicine Description" class="form-control" />
                        <label for="medicineDescription">Description</label>
                      </div>
                    </div>
                  </div>

                  <div class="row g-3 mb-3">
                    <div class="col-md-6">
                      <div class="form-floating">
                        <input type="text" name="medicineSideEffect" id="medicineSideEffect" required
                          placeholder="Enter Medicine Side Effects" class="form-control" />
                        <label for="medicineSideEffect">Side Effects</label>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-floating">
                        <select name="medicineType" id="medicineType" class="form-select">
                          <option disabled selected value="0">Select</option>
                          <option value="1">Veg</option>
                          <option value="2">Non-Veg</option>
                        </select>
                        <label for="medicineType">Medicine Type</label>
                      </div>
                    </div>
                  </div>

                  <div class="row g-3 mb-3">
                    <div class="col-md-6">
                      <div class="form-floating">
                        <select name="genericMedicineType" id="genericMedicineType" class="form-select">
                          <option value="0" selected>Select</option>
                          <c:forEach var="genericMedicine" items="${applicationScope.genericMedicine}"
                            varStatus="status">
                            <option value="${status.index + 1}">${genericMedicine.name}</option>
                          </c:forEach>
                        </select>
                        <label for="genericMedicineType">Generic Medicine</label>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="modal-footer border-0">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="addMedicineCloseBtn">
                    Close
                  </button>
                  <button type="submit" class="btn" id="addMedicineSubmitBtn"
                    style="background-color: rgb(45, 109, 131); color: white ;">
                    Next
                  </button>
                </div>
              </form>  -->

      <!-- ######################## -->

      <!-- <form id="saveFormatForm" class="needs-validation" novalidate action="saveFormat.do" method="post">
                <div class="col-md-6 addDenominationDiv" style="display: none;">
                  <label for="noOfDenomination" class="form-label fw-bold">Total No. Of Denomination?</label>
                  <input type="number" name="noOfDenomination" id="noOfDenomination" required
                    placeholder="Enter No. of Denomination" class="form-control mb-2" />
                  <button type="button" class="btn mb-3" style="background-color: rgb(45, 109, 131); color: white"
                    id="generateInputForDenomination">Generate</button>


                </div>
                <input type="hidden" id="medicineId" name="medicineId" value="${medicineAdded.medicineId}">
                <div id="denominationContainer"></div>

                <div class="modal-footer border-0">
                  <button type="submit" class="btn" id="addMedicineFormatSubmitBtn"
                    style="background-color: rgb(45, 109, 131); color: white; display:none;">
                    Submit
                  </button>
                </div>
            </div>
            </form>
          </div>
        </div>
      </div>
      </div>  -->

      <!--------------------------NEW Medicine Modal START---------------------------->
      <div class="modal" id="addMedicineModal" tabindex="-1">
        <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
          <div class="modal-content shadow-lg rounded-3 border-0">
            <div class="modal-header">
              <h5 class="modal-title text-white">
                <i class="bi bi-capsule"></i>
                Add New Medicine
              </h5>
              <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                aria-label="Close"></button>
            </div>
            <div class="modal-body p-4">
              <form id="addMedicineForm" class="needs-validation" novalidate action="addMedicine.do" method="post">
                <div class="addMedicineDiv">
                  <div class="section-title">
                    <i class="bi bi-info-circle-fill"></i>
                    Basic Information
                  </div>

                  <div class="row g-3 mb-3">
                    <div class="col-md-6">
                      <div class="icon-wrapper">
                        <i class="bi bi-capsule field-icon"></i>
                        <div class="form-floating">
                          <input type="text" name="medicineName" id="medicineName" required
                            placeholder="Enter Medicine Name" class="form-control" />
                          <label for="medicineName">Medicine Name</label>
                        </div>
                      </div>
                    </div>

                    <div class="col-md-6">
                      <div class="icon-wrapper">
                        <i class="bi bi-file-text field-icon"></i>
                        <div class="form-floating">
                          <input type="text" name="medicineDescription" id="medicineDescription" required
                            placeholder="Enter Medicine Description" class="form-control" />
                          <label for="medicineDescription">Description</label>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="row g-3 mb-3">
                    <div class="col-md-6">
                      <div class="icon-wrapper">
                        <i class="bi bi-exclamation-triangle field-icon"></i>
                        <div class="form-floating">
                          <input type="text" name="medicineSideEffect" id="medicineSideEffect" required
                            placeholder="Enter Medicine Side Effects" class="form-control" />
                          <label for="medicineSideEffect">Side Effects</label>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="icon-wrapper">
                        <i class="bi bi-tag field-icon"></i>
                        <div class="form-floating">
                          <select name="medicineType" id="medicineType" class="form-select">
                            <option disabled selected value="0">Select</option>
                            <option value="1">Veg</option>
                            <option value="2">Non-Veg</option>
                          </select>
                          <label for="medicineType">Medicine Type</label>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="row g-3 mb-3">
                    <div class="col-md-6">
                      <div class="icon-wrapper">
                        <i class="bi bi-hospital field-icon"></i>
                        <div class="form-floating">
                          <select name="genericMedicineType" id="genericMedicineType" class="form-select">
                            <option value="0" disabled>Select</option>
                            <c:forEach var="genericMedicine" items="${applicationScope.genericMedicine}"
                              varStatus="status">
                              <option value="${status.index + 1}">${genericMedicine.name}</option>
                            </c:forEach>
                          </select>
                          <label for="genericMedicineType">Generic Medicine</label>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="modal-footer border-0">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="addMedicineCloseBtn">
                    <i class="bi bi-x-circle"></i>
                    Close
                  </button>
                  <button type="submit" class="btn" id="addMedicineSubmitBtn">
                    <i class="bi bi-arrow-right-circle"></i>
                    Next
                  </button>
                </div>
              </form>

              <!-- ######################## -->

              <form id="saveFormatForm" class="needs-validation" novalidate action="saveFormat.do" method="post">
                <div class="col-md-12 addDenominationDiv" style="display: none;">
                  <div class="section-title">
                    <i class="bi bi-boxes"></i>
                    Denomination Details
                  </div>
                  <label for="noOfDenomination" class="form-label fw-bold">
                    <i class="bi bi-123"></i>
                    Total No. Of Denomination?
                  </label>
                  <input type="number" name="noOfDenomination" id="noOfDenomination" required
                    placeholder="Enter No. of Denomination" class="form-control mb-2" min="1" max="10" />
                  <button type="button" class="btn mb-3" id="generateInputForDenomination">
                    <i class="bi bi-arrow-repeat"></i>
                    Generate
                  </button>
                </div>
                <input type="hidden" id="medicineId" name="medicineId" value="">
                <div id="denominationContainer"></div>

                <div class="modal-footer border-0">
                  <button type="submit" class="btn" id="addMedicineFormatSubmitBtn" style="display:none;">
                    <i class="bi bi-check-circle-fill"></i>
                    Submit
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <!--------------------------Scripting Section Starts Here---------------------------->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>

      <script src="static/js/manufacturerDashboard.js"></script>
  </body>

  </html>