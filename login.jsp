<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
    />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

    <style>
      html,
      body {
        background: linear-gradient(135deg, #b1e3d7, #eeffdb);
      font-family: "Inter", sans-serif;
        height: 100%;
        margin: 0;
        overflow-x: hidden;
      }
      .brand-logo {
      width: 60px;
      margin-bottom: 1rem;
    }
    .btn-primary {
      background-color: #198754;
      border: none;
    }
    .btn-primary:hover {
      background-color: #157347;
    }
    </style>
  </head>

  <body>

    <div
      class="d-flex justify-content-center align-items-center min-vh-100"
    >
      <div
        class="card shadow-lg border-0 rounded-4 p-4"
        style="max-width: 600px; width: 100%"
      >
        <!-- Branding -->
        <div class="text-center mb-4">
          <img
            src="static/media/images/brandLogo.png"
            alt="Logo"
            class="mb-2"
            style="width: 60px"
          />
        <h4 class="fw-bold text-success">Healthcare Portal</h4>
          <p class="text-muted small mb-0">Login to continue</p>
        </div>

        <!-- Login Form -->
        <form action="login.do" method="post" id="login_form">
          <!-- Email -->
          <div class="form-floating mb-3">
            <input
              type="email"
              name="email"
              id="email"
              required
              placeholder="Enter Existing Email"
              class="form-control"
            />
            <label for="email">Email</label>
          </div>

          <!-- Password -->
          <div class="form-floating mb-4">
            <input
              type="password"
              name="password"
              id="password"
              required
              placeholder="Enter Password"
              class="form-control"
            />
            <label for="password">Password</label>
          </div>

          <!-- Submit -->
          <div class="d-grid">
            <button
              type="submit"
              class="btn btn-primary btn-lg d-flex justify-content-center align-items-center gap-2"
            >
              <i class="bi bi-door-open"></i>
              Login
            </button>
          </div>

          <!-- Redirect -->
          <p class="text-center mt-3 mb-0">
            Don't have an account?
            <a
              href="signup.jsp"
              class="text-success fw-semibold text-decoration-none"
              >Sign up here</a
            >
          </p>
        </form>
      </div>
    </div>
  </body>
</html>
