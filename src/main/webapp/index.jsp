<%-- 
    Document   : index
    Created on : 09-Sept-2025, 5:18:11 pm
    Author     : Tax
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Portfolio Manager - Home</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg bg-white border-bottom">
  <div class="container">
    <a class="navbar-brand fw-semibold" href="${pageContext.request.contextPath}/">Portfolio Manager</a>
    <div class="ms-auto">
      <a class="btn btn-outline-primary me-2" href="portfolio">Portfolio</a>
      <a class="btn btn-outline-success me-2" href="sip">SIP Plans</a>
      <a class="btn btn-outline-secondary" href="reports">Reports</a>
    </div>
  </div>
</nav>

<main class="container py-5">
  <div class="p-5 bg-white rounded-4 shadow-sm">
    <h1 class="display-6 mb-2">Welcome to Portfolio Manager</h1>
    <p class="text-muted">Track portfolios, SIPs, and generate reports.</p>

    <div class="mt-3">
      <a href="portfolio" class="btn btn-primary me-2">Go to Portfolio</a>
      <a href="sip" class="btn btn-success me-2">Manage SIP Plans</a>
      <a href="reports" class="btn btn-secondary">View Reports</a>
    </div>
  </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
