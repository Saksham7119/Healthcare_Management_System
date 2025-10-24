<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <img
      style="width: 30px; margin-right: 10px"
      src="static/media/images/brandLogo.png"
      alt=""
    />
    <a class="navbar-brand" href="#">HealthCare</a>
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
          <a class="nav-link active" aria-current="page" href="index.jsp"
            >Home</a
          >
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Contact</a>
        </li>
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            role="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            Already a User?
          </a>
          <ul class="dropdown-menu">
            <li>
              <a class="dropdown-item" href="signup.do">New User? Register!</a>
            </li>
            <li>
              <a
                class="dropdown-item"
                data-bs-toggle="modal"
                data-bs-target="#loginModal"
                >Login</a
              >
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </div>

  
</nav>
