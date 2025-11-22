<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Manufacturer</title>
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
      href="<c:url value='/static/css/MedicineStore.css' />"
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
        <a class="navbar-brand text-white" href="ManufacturerDashboard.jsp">HealthCare</a>
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
                href="ManufacturerDashboard.do"
                >Home</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="#">Add Medicine</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="MedicineStore.jsp"
                >Your Store</a
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
                  <a class="dropdown-item" href="signup.jsp"
                    >Configure Profile</a
                  >
                </li>
                <li>
                  <a
                    class="dropdown-item"
                    href="logout.do"
                    >Logout</a
                  >
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
            <h4><i class="bi bi-shop me-2"></i>My Medicine Store</h4>
        </div>
        <div class="mainStoreDiv" id="mainStoreDiv">
          <div class="searchMedicineDiv">
            <input type="text" id="searchStoreMedicineInput" placeholder="Search Medicine Here!">
          </div>
            <div class="medicineCardParentDiv" id="medicineCardParentDiv"></div>
        </div>
    </div>
    <!--Store End-->
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
    
    
    <!--Edit Medicine Card Start-->
        <div class="modal fade" id="medicineEditForm" tabindex="-1">
        <div class="modal-dialog modal-dialog-scrollable modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <!-- <h5 class="modal-title">Student Form</h5> -->
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div id="form_box">
                        <div id="success" class="message">Success</div>
                        <div id="error" class="message">Failed</div>

                        <div id="caption">
                            Student Registration
                        </div>
                        <form id="form" class="py-3">
                            <input type="hidden" id="medicine_id" value="">
                            <div class="field_box">
                                <label for="medicine_name">Medicine Name</label>
                                <input type="text" id="medicine_name">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Edit Medicine Card End-->

    <!--Edit Medicine Image Start-->
        <div class="modal fade" id="medicineImageEditForm" tabindex="-1">
        <div class="modal-dialog modal-dialog-scrollable modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4>Edit Medicine Image</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div id="editMedicineImagebox">
                        <div class="info-box">
                            <p>Upload a new image for your medicine.</p>
                        </div>
                        
                        <form id="form" class="py-3" mulp>
                            <input type="hidden" id="medicine_id" value="">
                            
                            <div class="field_box">
                                <label for="medicineImage">Medicine Image</label>
                                <input type="file" id="pic" accept="image/*">
                            </div>
                            
                            <button type="button" id="uploadEditImageBtn">Upload Image</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Edit Medicine Image End-->

    <!--Confirming medicine removal modal-->
<div class="modal fade " id="confirmingMedicineRemovalModal" tabindex="-1" aria-labelledby="confirmingMedicineRemovalModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
        <h5>Are You Sure you want to remove medicine for this denomination?</h5>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" id="rejectMedicineRemoveBtn" data-bs-dismiss="modal">No</button>
        <button type="button" id="confirmMedicineRemoveBtn" class="btn ">Yes</button>
      </div>
    </div>
  </div>
</div>
    <!---Confirming medicine removal modal->

    <!--------------------------Scripting Section Starts Here---------------------------->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
      crossorigin="anonymous"
    ></script>
    <script src="static/js/medicineStore.js"></script>
  </body>
</html>
