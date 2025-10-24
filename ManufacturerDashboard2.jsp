<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Manufacturer Dashboard - HealthCare</title>
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
    <style>
      :root {
        --primary-color: #2d6d83;
        --primary-light: #3a8ba3;
        --primary-dark: #1f5a6e;
        --accent-color: #4fa8c5;
        --light-bg: #f8fbfc;
        --card-shadow: 0 4px 6px rgba(45, 109, 131, 0.1);
        --hover-shadow: 0 8px 25px rgba(45, 109, 131, 0.15);
      }

      body {
        background-color: var(--light-bg);
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        line-height: 1.6;
      }

      /* Header Styling */
      .navbar {
        background: linear-gradient(135deg, var(--primary-color), var(--primary-light));
        box-shadow: var(--card-shadow);
        padding: 1rem 0;
      }

      .navbar-brand {
        font-weight: 700;
        font-size: 1.5rem;
        letter-spacing: 0.5px;
      }

      .nav-link {
        font-weight: 500;
        transition: all 0.3s ease;
        position: relative;
      }

      .nav-link:hover {
        transform: translateY(-1px);
        color: #e3f2f7 !important;
      }

      .nav-link.active::after {
        content: '';
        position: absolute;
        width: 100%;
        height: 2px;
        bottom: -5px;
        left: 0;
        background-color: white;
        border-radius: 2px;
      }

      /* Welcome Section */
      .welcome-section {
        background: linear-gradient(135deg, #ffffff, #f0f9fc);
        border-radius: 20px;
        padding: 2.5rem;
        margin: 2rem 0;
        box-shadow: var(--card-shadow);
        border: 1px solid rgba(45, 109, 131, 0.05);
      }

      .welcome-title {
        color: var(--primary-color);
        font-weight: 700;
        margin-bottom: 0.5rem;
      }

      .welcome-subtitle {
        color: var(--primary-dark);
        margin-bottom: 2rem;
        opacity: 0.8;
      }

      .action-btn {
        background: linear-gradient(135deg, var(--primary-color), var(--accent-color));
        border: none;
        padding: 12px 24px;
        border-radius: 12px;
        font-weight: 600;
        transition: all 0.3s ease;
        color: white;
        margin-right: 1rem;
        margin-bottom: 0.5rem;
      }

      .action-btn:hover {
        transform: translateY(-2px);
        box-shadow: var(--hover-shadow);
        color: white;
      }

      .user-avatar {
        width: 120px;
        height: 120px;
        border-radius: 50%;
        object-fit: cover;
        border: 4px solid var(--primary-color);
        box-shadow: var(--card-shadow);
      }

      /* Section Headers */
      .section-header {
        color: var(--primary-dark);
        font-weight: 700;
        padding-bottom: 0.75rem;
        margin-bottom: 1.5rem;
        border-bottom: 3px solid var(--primary-color);
        position: relative;
      }

      .section-header::after {
        content: '';
        position: absolute;
        bottom: -3px;
        left: 0;
        width: 60px;
        height: 3px;
        background: var(--accent-color);
        border-radius: 2px;
      }

      /* Notifications */
      .notification-panel {
        max-height: 400px;
        overflow-y: auto;
      }

      .notification-item {
        background: linear-gradient(135deg, #e6f7fb, #ffffff);
        border: 1px solid var(--accent-color);
        border-radius: 15px;
        margin-bottom: 0.75rem;
        padding: 1rem 1.5rem;
        transition: all 0.3s ease;
        position: relative;
      }

      .notification-item:hover {
        transform: translateX(5px);
        box-shadow: var(--hover-shadow);
      }

      .notification-item::before {
        content: '';
        position: absolute;
        left: 0;
        top: 0;
        bottom: 0;
        width: 4px;
        background: var(--accent-color);
        border-radius: 15px 0 0 15px;
      }

      /* Statistics Cards */
      .stats-card {
        background: white;
        border-radius: 16px;
        padding: 2rem 1.5rem;
        text-align: center;
        border: none;
        box-shadow: var(--card-shadow);
        transition: all 0.3s ease;
        position: relative;
        overflow: hidden;
      }

      .stats-card::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        height: 4px;
        background: linear-gradient(135deg, var(--primary-color), var(--accent-color));
      }

      .stats-card:hover {
        transform: translateY(-5px);
        box-shadow: var(--hover-shadow);
      }

      .stats-number {
        font-size: 2.5rem;
        font-weight: 800;
        margin: 0.5rem 0;
      }

      .stats-label {
        color: #6c757d;
        font-weight: 500;
        font-size: 0.95rem;
      }

      .stats-primary .stats-number { color: var(--primary-color); }
      .stats-success .stats-number { color: #198754; }
      .stats-danger .stats-number { color: #dc3545; }
      .stats-info .stats-number { color: var(--accent-color); }

      /* Modal Styling */
      .modal-content {
        border: none;
        border-radius: 20px;
        overflow: hidden;
      }

      .modal-header {
        background: linear-gradient(135deg, var(--primary-color), var(--primary-light));
        padding: 1.5rem 2rem;
      }

      .modal-title {
        font-weight: 700;
        font-size: 1.25rem;
      }

      .modal-body {
        padding: 2rem;
      }

      .form-floating label {
        color: var(--primary-dark);
        font-weight: 500;
      }

      .form-control:focus,
      .form-select:focus {
        border-color: var(--accent-color);
        box-shadow: 0 0 0 0.25rem rgba(79, 168, 197, 0.25);
      }

      .modal-footer {
        padding: 1.5rem 2rem;
        background-color: #f8f9fa;
      }

      /* Footer */
      footer {
        background: linear-gradient(135deg, var(--primary-dark), var(--primary-color));
        margin-top: 4rem;
      }

      footer h5 {
        color: white;
        font-weight: 600;
        margin-bottom: 1rem;
      }

      footer ul li {
        margin-bottom: 0.5rem;
        transition: all 0.3s ease;
      }

      footer ul li:hover {
        transform: translateX(5px);
        color: var(--accent-color);
      }

      /* Responsive Design */
      @media (max-width: 768px) {
        .welcome-section {
          padding: 1.5rem;
        }
        
        .user-avatar {
          width: 80px;
          height: 80px;
        }
        
        .stats-card {
          margin-bottom: 1rem;
        }
      }

      /* Loading Animation */
      .loading {
        position: relative;
        overflow: hidden;
      }

      .loading::after {
        content: '';
        position: absolute;
        top: 0;
        left: -100%;
        width: 100%;
        height: 100%;
        background: linear-gradient(90deg, transparent, rgba(255,255,255,0.4), transparent);
        animation: loading 1.5s infinite;
      }

      @keyframes loading {
        0% { left: -100%; }
        100% { left: 100%; }
      }

      /* Custom Scrollbar */
      .notification-panel::-webkit-scrollbar {
        width: 6px;
      }

      .notification-panel::-webkit-scrollbar-track {
        background: #f1f1f1;
        border-radius: 10px;
      }

      .notification-panel::-webkit-scrollbar-thumb {
        background: var(--accent-color);
        border-radius: 10px;
      }

      .notification-panel::-webkit-scrollbar-thumb:hover {
        background: var(--primary-color);
      }
    </style>
  </head>

  <body>
    <c:if test="${empty user}">
      <c:redirect url="login.jsp"/>
    </c:if>

    <!-- Header Start -->
    <nav class="navbar navbar-expand-lg sticky-top">
      <div class="container-fluid px-4">
        <div class="d-flex align-items-center">
          <img
            style="width: 40px; height: 40px; margin-right: 12px"
            src="static/media/images/brandLogo.png"
            alt="HealthCare Logo"
            class="rounded-circle"
          />
          <a class="navbar-brand text-white" href="#">HealthCare</a>
        </div>
        
        <button
          class="navbar-toggler border-0"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNavDropdown"
          aria-controls="navbarNavDropdown"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <i class="bi bi-list text-white fs-3"></i>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a
                class="nav-link active text-white px-3"
                aria-current="page"
                href="ManufacturerDashboard.do"
              >
                <i class="bi bi-house-door me-1"></i>Home
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white px-3" href="#" data-bs-toggle="modal" data-bs-target="#addMedicineModal">
                <i class="bi bi-plus-circle me-1"></i>Add Medicine
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white px-3" href="MedicineStore.do">
                <i class="bi bi-shop me-1"></i>Your Store
              </a>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle text-white px-3"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <i class="bi bi-person-circle me-1"></i>Profile
              </a>
              <ul class="dropdown-menu dropdown-menu-end shadow-lg border-0 rounded-3">
                <li>
                  <a class="dropdown-item py-2" href="#" data-bs-toggle="modal" data-bs-target="#configureManufacturerModal">
                    <i class="bi bi-gear me-2 text-primary"></i>Configure Profile
                  </a>
                </li>
                <li><hr class="dropdown-divider"></li>
                <li>
                  <a class="dropdown-item py-2 text-danger" href="#">
                    <i class="bi bi-box-arrow-right me-2"></i>Logout
                  </a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- Header End -->

    <!-- Main Content Container -->
    <div class="container-fluid px-4 py-3">
      
      <!-- Welcome Section Start -->
      <div class="welcome-section">
        <div class="row align-items-center">
          <div class="col-lg-8 col-md-7">
            <h2 class="welcome-title">
              <c:if test="${not empty user}">
                Welcome back, ${user.name}!
              </c:if>
            </h2>
            <p class="welcome-subtitle fs-5 mb-4">
              Empower healthcare by managing and listing high-quality medicines for better patient outcomes.
            </p>
            <div class="action-buttons">
              <button
                type="button"
                class="btn action-btn"
                data-bs-toggle="modal"
                data-bs-target="#addMedicineModal"
              >
                <i class="bi bi-plus-lg me-2"></i>Add New Medicine
              </button>
              <button type="button" class="btn action-btn">
                <i class="bi bi-eye me-2"></i>View Inventory
              </button>
              <button type="button" class="btn action-btn">
                <i class="bi bi-graph-up me-2"></i>Analytics
              </button>
            </div>
          </div>
          <div class="col-lg-4 col-md-5 text-center">
            <img src="static/media/images/user.png" alt="User Avatar" class="user-avatar" />
          </div>
        </div>
      </div>
      <!-- Welcome Section End -->

      <!-- Statistics Section Start -->
      <div class="row mb-5">
        <div class="col-12">
          <h3 class="section-header">Medicine Statistics</h3>
        </div>
        <div class="col-lg-3 col-md-6 mb-4">
          <div class="card stats-card stats-primary h-100">
            <div class="card-body">
              <i class="bi bi-capsule-pill fs-1 text-primary mb-2"></i>
              <h6 class="stats-label">Total Medicines Listed</h6>
              <h3 class="stats-number">127</h3>
              <small class="text-muted">
                <i class="bi bi-arrow-up text-success me-1"></i>+5 this month
              </small>
            </div>
          </div>
        </div>

        <div class="col-lg-3 col-md-6 mb-4">
          <div class="card stats-card stats-success h-100">
            <div class="card-body">
              <i class="bi bi-check-circle fs-1 text-success mb-2"></i>
              <h6 class="stats-label">Medicines In Stock</h6>
              <h3 class="stats-number">98</h3>
              <small class="text-muted">Available for distribution</small>
            </div>
          </div>
        </div>

        <div class="col-lg-3 col-md-6 mb-4">
          <div class="card stats-card stats-danger h-100">
            <div class="card-body">
              <i class="bi bi-exclamation-circle fs-1 text-danger mb-2"></i>
              <h6 class="stats-label">Out of Stock</h6>
              <h3 class="stats-number">12</h3>
              <small class="text-muted">
                <i class="bi bi-arrow-down text-danger me-1"></i>Need restocking
              </small>
            </div>
          </div>
        </div>

        <div class="col-lg-3 col-md-6 mb-4">
          <div class="card stats-card stats-info h-100">
            <div class="card-body">
              <i class="bi bi-clock fs-1 text-info mb-2"></i>
              <h6 class="stats-label">Last Updated</h6>
              <h3 class="stats-number" style="font-size: 1.5rem;">Today</h3>
              <small class="text-muted">2 hours ago</small>
            </div>
          </div>
        </div>
      </div>
      <!-- Statistics Section End -->

      <!-- Notifications Section Start -->
      <div class="row">
        <div class="col-12">
          <h3 class="section-header">Recent Notifications</h3>
          <div class="notification-panel">
            <div class="notification-item">
              <div class="d-flex justify-content-between align-items-center">
                <div>
                  <i class="bi bi-plus-circle-fill text-primary me-2"></i>
                  <strong>New Medicine Added</strong>
                  <p class="mb-0 mt-1 text-muted">You have successfully listed "Amoxicillin 500mg" in your inventory.</p>
                </div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
            </div>

            <div class="notification-item">
              <div class="d-flex justify-content-between align-items-center">
                <div>
                  <i class="bi bi-arrow-clockwise text-info me-2"></i>
                  <strong>Inventory Updated</strong>
                  <p class="mb-0 mt-1 text-muted">Expiry date updated for Paracetamol tablets. New expiry: Dec 2025.</p>
                </div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
            </div>

            <div class="notification-item">
              <div class="d-flex justify-content-between align-items-center">
                <div>
                  <i class="bi bi-exclamation-triangle-fill text-warning me-2"></i>
                  <strong>Stock Alert</strong>
                  <p class="mb-0 mt-1 text-muted">Low stock warning: Aspirin tablets are running low (5 units remaining).</p>
                </div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
            </div>

            <div class="notification-item">
              <div class="d-flex justify-content-between align-items-center">
                <div>
                  <i class="bi bi-check-circle-fill text-success me-2"></i>
                  <strong>Quality Check Completed</strong>
                  <p class="mb-0 mt-1 text-muted">All medicines have passed the monthly quality inspection.</p>
                </div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Footer Start -->
    <footer class="text-white py-5">
      <div class="container">
        <div class="row">
          <div class="col-md-4 mb-4">
            <h5><i class="bi bi-shield-check me-2"></i>About The Company</h5>
            <ul class="list-unstyled">
              <li class="mb-2">
                <a href="#" class="text-white-50 text-decoration-none">
                  <i class="bi bi-shield-lock-fill me-2"></i>Privacy Policy
                </a>
              </li>
              <li>
                <a href="#" class="text-white-50 text-decoration-none">
                  <i class="bi bi-file-earmark-text-fill me-2"></i>Terms & Conditions
                </a>
              </li>
            </ul>
          </div>

          <div class="col-md-4 mb-4">
            <h5><i class="bi bi-link-45deg me-2"></i>Quick Links</h5>
            <ul class="list-unstyled">
              <li class="mb-2">
                <a href="#" class="text-white-50 text-decoration-none">
                  <i class="bi bi-house-fill me-2"></i>Home
                </a>
              </li>
              <li class="mb-2">
                <a href="#" class="text-white-50 text-decoration-none">
                  <i class="bi bi-person-fill me-2"></i>Patient Portal
                </a>
              </li>
              <li class="mb-2">
                <a href="#" class="text-white-50 text-decoration-none">
                  <i class="bi bi-person-badge-fill me-2"></i>Doctor Portal
                </a>
              </li>
              <li>
                <a href="#" class="text-white-50 text-decoration-none">
                  <i class="bi bi-capsule-pill me-2"></i>Pharmacy Portal
                </a>
              </li>
            </ul>
          </div>

          <div class="col-md-4 mb-4">
            <h5><i class="bi bi-headset me-2"></i>Contact Support</h5>
            <p class="mb-2">
              <i class="bi bi-envelope-fill me-2"></i>
              <a href="mailto:support@healthcare.com" class="text-white-50 text-decoration-none">
                support@healthcare.com
              </a>
            </p>
            <p class="mb-0">
              <i class="bi bi-telephone-fill me-2"></i>
              <a href="tel:+18001234567" class="text-white-50 text-decoration-none">
                +1 (800) 123-4567
              </a>
            </p>
          </div>
        </div>
        
        <hr class="my-4 border-light opacity-25">
        <div class="text-center text-white-50">
          <p class="mb-0">&copy; 2024 HealthCare Solutions. All rights reserved.</p>
        </div>
      </div>
    </footer>
    <!-- Footer End -->

    <!-- Configure Manufacturer Modal Start -->
    <div class="modal fade" id="configureManufacturerModal" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title text-white">
              <i class="bi bi-gear me-2"></i>Configure Your Profile
            </h5>
            <button
              type="button"
              class="btn-close btn-close-white"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <form action="configureManufacturer.do" method="post" id="configureManufacturerForm">
              <div class="form-floating mb-3">
                <input
                  type="text"
                  name="companyName"
                  id="companyName"
                  required
                  placeholder="Enter Your Company Name"
                  class="form-control"
                />
                <label for="companyName">Company Name</label>
              </div>
              <div class="form-floating mb-3">
                <input
                  type="email"
                  name="companyEmail"
                  id="companyEmail"
                  required
                  placeholder="Enter Company Email"
                  class="form-control"
                />
                <label for="companyEmail">Company Email</label>
              </div>
              <div class="form-floating mb-3">
                <input
                  type="url"
                  name="companyWebsite"
                  id="companyWebsite"
                  placeholder="Company Website Link"
                  class="form-control"
                />
                <label for="companyWebsite">Website URL (Optional)</label>
              </div>
              <div class="form-floating mb-3">
                <input
                  type="text"
                  name="licenceNumber"
                  id="licenceNumber"
                  required
                  placeholder="Licence Number"
                  class="form-control"
                />
                <label for="licenceNumber">License Number</label>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
              Cancel
            </button>
            <button type="submit" class="btn action-btn" form="configureManufacturerForm">
              <i class="bi bi-check-lg me-1"></i>Save Profile
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- Configure Manufacturer Modal End -->

    <!-- Add Medicine Modal Start -->
    <div class="modal fade" id="addMedicineModal" tabindex="-1">
      <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title text-white">
              <i class="bi bi-plus-circle me-2"></i>Add New Medicine
            </h5>
            <button
              type="button"
              class="btn-close btn-close-white"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <form id="addMedicineForm" action="addMedicine.do" method="post">
              <div class="row g-3 mb-3">
                <div class="col-md-6">
                  <div class="form-floating">
                    <input
                      type="text"
                      name="medicineName"
                      id="medicineName"
                      required
                      placeholder="Enter Medicine Name"
                      class="form-control"
                    />
                    <label for="medicineName">Medicine Name</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-floating">
                    <input
                      type="text"
                      name="medicineDescription"
                      id="medicineDescription"
                      required
                      placeholder="Enter Medicine Description"
                      class="form-control"
                    />
                    <label for="medicineDescription">Description</label>
                  </div>
                </div>
              </div>

              <div class="row g-3 mb-3">
                <div class="col-md-6">
                  <div class="form-floating">
                    <textarea
                      name="medicineSideEffect"
                      id="medicineSideEffect"
                      required
                      placeholder="Enter Medicine Side Effects"
                      class="form-control"
                      style="height: 100px"
                    ></textarea>
                    <label for="medicineSideEffect">Side Effects</label>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-floating mb-3">
                    <select name="medicineType" id="medicineType" class="form-select" required>
                      <option value="" selected disabled>Select Type</option>
                      <option value="1">Vegetarian</option>
                      <option value="2">Non-Vegetarian</option>
                    </select>
                    <label for="medicineType">Medicine Type</label>
                  </div>
                  
                  <div class="form-floating">
                    <select name="genericMedicineType" id="genericMedicineType" class="form-select" required>
                      <option value="" selected disabled>Select Generic</option>
                      <c:forEach var="genericMedicine" items="${applicationScope.genericMedicine}" varStatus="status">
                        <option value="${status.index + 1}">${genericMedicine.name}</option>
                      </c:forEach>
                    </select>
                    <label for="genericMedicineType">Generic Medicine</label>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
              Cancel
            </button>
            <button
              type="button"
              class="btn action-btn"
              id="addMedicineNextBtn"
              data-bs-toggle="modal"
              data-bs-target="#addMedicineDenominationModal"
            >
              Next Step <i class="bi bi-arrow-right ms-1"></i>
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- Add Medicine Modal End -->

    <!-- Add Medicine Denomination Modal Start -->
    <div class="modal fade" id="addMedicineDenominationModal" tabindex="-1">
      <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title text-white">
              <i class="bi bi-grid-3x3-gap me-2"></i>Medicine Denominations
            </h5>
            <button
              type="button"
              class="btn-close btn-close-white"
              data-bs-dismiss="modal