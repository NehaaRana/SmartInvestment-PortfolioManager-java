<%-- 
    Document   : index
    Created on : 09-Sept-2025, 5:18:11 pm
    Author     : Prachi
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Smart Investment Portfolio – Home</title>

  <!-- Bootstrap & Icons -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

  <style>
    :root{
      --brand:#1e66f5;
      --brand-2:#0ea5e9;
    }
    .navbar-brand { font-weight: 700; letter-spacing:.3px; }
    .btn-brand { background: var(--brand); color:#fff; }
    .btn-brand:hover { background: #1653c9; color:#fff; }
    .hero {
      background: radial-gradient(1200px 600px at 10% 10%, rgba(30,102,245,.10), transparent 60%),
                  radial-gradient(1000px 500px at 90% 20%, rgba(14,165,233,.10), transparent 60%);
    }
    .shadow-soft { box-shadow: 0 8px 24px rgba(0,0,0,.06); }
    .kpi-card .kpi { font-size: clamp(1.6rem, 2.2vw, 2.2rem); font-weight: 700; }
    .nav-link.active { color: var(--brand) !important; }
    .watchlist-table th, .watchlist-table td { vertical-align: middle; }
    .section-title { font-weight: 700; letter-spacing:.2px; }
    footer a { text-decoration: none; }
  </style>
</head>
<body class="bg-light">

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg bg-white border-bottom sticky-top">
  <div class="container">
    <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/">
      <i class="bi bi-graph-up-arrow me-2 text-primary"></i> Smart Portfolio
    </a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="mainNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/portfolio">Portfolio</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/sip">SIP Plans</a></li>
        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/reports">Reports</a></li>
      </ul>

      <form class="d-flex me-lg-3 mb-2 mb-lg-0" role="search" action="${pageContext.request.contextPath}/search">
        <input class="form-control" name="q" type="search" placeholder="Search stocks, funds…" aria-label="Search">
      </form>

      <div class="d-flex gap-2">
        <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/auth/login"><i class="bi bi-box-arrow-in-right me-1"></i>Login</a>
        <a class="btn btn-brand" href="${pageContext.request.contextPath}/auth/register"><i class="bi bi-person-plus me-1"></i>Sign up</a>
      </div>
    </div>
  </div>
</nav>

<!-- HERO -->
<header class="hero">
  <div class="container">
    <div class="row align-items-center py-5">
      <div class="col-lg-7">
        <div class="p-4 p-lg-5 bg-white rounded-4 shadow-soft">
          <span class="badge text-bg-primary-subtle border border-primary-subtle mb-3">New</span>
          <h1 class="display-6 mb-3">Track. Plan. Grow. <span class="text-primary">All in one place.</span></h1>
          <p class="lead text-muted mb-4">
            Manage equities, mutual funds, and SIPs with real-time insights, clean reports, and smart allocations.
          </p>
          <div class="d-flex flex-wrap gap-2">
            <a href="${pageContext.request.contextPath}/portfolio" class="btn btn-brand btn-lg">
              <i class="bi bi-pie-chart me-2"></i>Go to Portfolio
            </a>
            <a href="${pageContext.request.contextPath}/sip" class="btn btn-outline-primary btn-lg">
              <i class="bi bi-calendar2-week me-2"></i>Manage SIPs
            </a>
            <a href="${pageContext.request.contextPath}/reports" class="btn btn-outline-secondary btn-lg">
              <i class="bi bi-diagram-3 me-2"></i>View Reports
            </a>
          </div>
        </div>
      </div>
      <div class="col-lg-5 mt-4 mt-lg-0">
        <div class="bg-white rounded-4 shadow-soft p-4">
          <h6 class="text-uppercase text-muted mb-3">Quick Snapshot</h6>
          <div class="row g-3">
            <div class="col-6">
              <div class="p-3 rounded-3 border kpi-card bg-light">
                <div class="text-muted">Total Value</div>
                <div class="kpi">₹ 24.5L</div>
              </div>
            </div>
            <div class="col-6">
              <div class="p-3 rounded-3 border kpi-card bg-light">
                <div class="text-muted">1D P/L</div>
                <div class="kpi text-success">+₹ 12,340</div>
              </div>
            </div>
            <div class="col-6">
              <div class="p-3 rounded-3 border kpi-card bg-light">
                <div class="text-muted">SIP / month</div>
                <div class="kpi">₹ 18,000</div>
              </div>
            </div>
            <div class="col-6">
              <div class="p-3 rounded-3 border kpi-card bg-light">
                <div class="text-muted">YTD Return</div>
                <div class="kpi text-primary">+11.8%</div>
              </div>
            </div>
          </div>
          <small class="text-muted d-block mt-3">* Demo figures. Connect data to populate live stats.</small>
        </div>
      </div>
    </div>
  </div>
</header>

<!-- QUICK ACTIONS -->
<section class="py-5">
  <div class="container">
    <h2 class="section-title mb-4">Quick actions</h2>
    <div class="row g-3">
      <div class="col-sm-6 col-lg-3">
        <a class="text-decoration-none" href="${pageContext.request.contextPath}/orders/new">
          <div class="p-4 bg-white border rounded-4 h-100 shadow-soft">
            <div class="d-flex align-items-center">
              <i class="bi bi-plus-circle fs-3 text-primary me-3"></i>
              <div>
                <div class="fw-semibold">Add Asset</div>
                <small class="text-muted">Stocks, ETFs, or MF units</small>
              </div>
            </div>
          </div>
        </a>
      </div>
      <div class="col-sm-6 col-lg-3">
        <a class="text-decoration-none" href="${pageContext.request.contextPath}/sip/create">
          <div class="p-4 bg-white border rounded-4 h-100 shadow-soft">
            <div class="d-flex align-items-center">
              <i class="bi bi-calendar-plus fs-3 text-success me-3"></i>
              <div>
                <div class="fw-semibold">Start SIP</div>
                <small class="text-muted">Automate monthly investing</small>
              </div>
            </div>
          </div>
        </a>
      </div>
      <div class="col-sm-6 col-lg-3">
        <a class="text-decoration-none" href="${pageContext.request.contextPath}/rebalance">
          <div class="p-4 bg-white border rounded-4 h-100 shadow-soft">
            <div class="d-flex align-items-center">
              <i class="bi bi-sliders2 fs-3 text-info me-3"></i>
              <div>
                <div class="fw-semibold">Rebalance</div>
                <small class="text-muted">Keep target allocation</small>
              </div>
            </div>
          </div>
        </a>
      </div>
      <div class="col-sm-6 col-lg-3">
        <a class="text-decoration-none" href="${pageContext.request.contextPath}/reports">
          <div class="p-4 bg-white border rounded-4 h-100 shadow-soft">
            <div class="d-flex align-items-center">
              <i class="bi bi-file-earmark-bar-graph fs-3 text-warning me-3"></i>
              <div>
                <div class="fw-semibold">Generate Report</div>
                <small class="text-muted">P/L, XIRR, tax summary</small>
              </div>
            </div>
          </div>
        </a>
      </div>
    </div>
  </div>
</section>

<!-- WATCHLIST PREVIEW -->
<section class="pb-5">
  <div class="container">
    <div class="d-flex justify-content-between align-items-end mb-3">
      <h2 class="section-title mb-0">Watchlist</h2>
      <a class="small" href="${pageContext.request.contextPath}/watchlist">View all</a>
    </div>

    <div class="bg-white border rounded-4 shadow-soft">
      <div class="table-responsive">
        <table class="table align-middle mb-0 watchlist-table">
          <thead class="table-light">
            <tr>
              <th>Symbol</th>
              <th>Company</th>
              <th class="text-end">LTP</th>
              <th class="text-end">Change</th>
              <th class="text-end d-none d-md-table-cell">Day Range</th>
              <th class="text-end">Action</th>
            </tr>
          </thead>
          <tbody>
            <!-- Sample rows; replace with dynamic data -->
            <tr>
              <td class="fw-semibold">INFY</td>
              <td>Infosys Ltd.</td>
              <td class="text-end">₹ 1,595.20</td>
              <td class="text-end text-success">+1.14%</td>
              <td class="text-end d-none d-md-table-cell">₹ 1,560 – 1,602</td>
              <td class="text-end">
                <a href="${pageContext.request.contextPath}/orders/new?symbol=INFY" class="btn btn-sm btn-outline-primary">Buy</a>
              </td>
            </tr>
            <tr>
              <td class="fw-semibold">HDFCBANK</td>
              <td>HDFC Bank</td>
              <td class="text-end">₹ 1,578.90</td>
              <td class="text-end text-danger">−0.42%</td>
              <td class="text-end d-none d-md-table-cell">₹ 1,565 – 1,592</td>
              <td class="text-end">
                <a href="${pageContext.request.contextPath}/orders/new?symbol=HDFCBANK" class="btn btn-sm btn-outline-primary">Buy</a>
              </td>
            </tr>
            <tr>
              <td class="fw-semibold">SBI</td>
              <td>State Bank of India</td>
              <td class="text-end">₹ 823.10</td>
              <td class="text-end text-success">+0.78%</td>
              <td class="text-end d-none d-md-table-cell">₹ 812 – 828</td>
              <td class="text-end">
                <a href="${pageContext.request.contextPath}/orders/new?symbol=SBI" class="btn btn-sm btn-outline-primary">Buy</a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="p-3 text-center text-muted small border-top">
        Live quotes coming soon — wire up your market data feed to replace demo values.
      </div>
    </div>
  </div>
</section>

<!-- FOOTER -->
<footer class="border-top bg-white">
  <div class="container py-4">
    <div class="row gy-3 align-items-center">
      <div class="col-md-6">
        <div class="small text-muted">
          © <%= java.time.Year.now() %> Smart Portfolio. All rights reserved.
        </div>
      </div>
      <div class="col-md-6">
        <div class="d-flex gap-3 justify-content-md-end">
          <a class="text-muted small" href="${pageContext.request.contextPath}/terms">Terms</a>
          <a class="text-muted small" href="${pageContext.request.contextPath}/privacy">Privacy</a>
          <a class="text-muted small" href="${pageContext.request.contextPath}/contact">Contact</a>
        </div>
      </div>
    </div>
  </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>