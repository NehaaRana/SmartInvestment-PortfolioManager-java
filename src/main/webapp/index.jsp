<%-- 
    Document   : index
    Created on : 09-Sept-2025, 5:18:11 pm
    Author     : Prachi
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Portfolio Manager - Home</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <style>
    .hero {
      background: linear-gradient(135deg, #0d6efd, #198754);
      color: white;
      padding: 80px 20px;
      border-radius: 1rem;
    }
    .feature-icon {
      font-size: 2rem;
      color: #0d6efd;
    }
  </style>
</head>
<body class="bg-light">

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm fixed-top">
  <div class="container">
    <a class="navbar-brand fw-bold text-primary" href="${pageContext.request.contextPath}/">
      Portfolio Manager
    </a>
    <div class="ms-auto">
      <a class="btn btn-outline-primary me-2" href="portfolio">Portfolio</a>
      <a class="btn btn-outline-success me-2" href="sip">SIP Plans</a>
      <a class="btn btn-outline-secondary" href="reports">Reports</a>
    </div>
  </div>
</nav>

<!-- Hero Section -->
<header class="hero text-center mt-5">
  <div class="container">
    <h1 class="display-4 fw-bold">Smart Investment, Simplified</h1>
    <p class="lead">Track portfolios, subscribe SIPs, and generate reports with ease.</p>
    <div class="mt-4">
      <a href="portfolio" class="btn btn-light btn-lg me-2">Explore Portfolio</a>
      <a href="sip" class="btn btn-warning btn-lg">Start SIP Now</a>
    </div>
  </div>
</header>

<!-- Features Section -->
<main class="container my-5">
  <div class="row text-center g-4">
    <div class="col-md-4">
      <div class="card border-0 shadow-sm p-4 h-100">
        <div class="feature-icon mb-3">
          <i class="bi bi-briefcase"></i>
        </div>
        <h5 class="fw-bold">Portfolio Tracking</h5>
        <p class="text-muted">Easily monitor multiple investments with real-time updates.</p>
      </div>
    </div>

    <div class="col-md-4">
      <div class="card border-0 shadow-sm p-4 h-100">
        <div class="feature-icon mb-3">
          <i class="bi bi-cash-coin"></i>
        </div>
        <h5 class="fw-bold">SIP Management</h5>
        <p class="text-muted">Set up and manage SIPs with flexible investment options.</p>
      </div>
    </div>

    <div class="col-md-4">
      <div class="card border-0 shadow-sm p-4 h-100">
        <div class="feature-icon mb-3">
          <i class="bi bi-graph-up-arrow"></i>
        </div>
        <h5 class="fw-bold">Reports & Insights</h5>
        <p class="text-muted">Generate detailed investment reports and track performance.</p>
      </div>
    </div>
  </div>
</main>

<!-- Footer -->
<footer class="bg-dark text-white py-4">
  <div class="container text-center">
    <p class="mb-1">© 2025 Portfolio Manager | Smart Investment WebApp</p>
    <small class="text-secondary">Designed with ❤️ using JSP & Bootstrap 5</small>
  </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</body>
</html>