$(document).ready(function () {

    $("#residences").append(
        $('<option></option>').val(i).html(residences[i].street + " " + residences[i].number)
    );

    // <a class="navbar-brand" href="/views/owner/home.html">Kamienica</a>
    // <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive"
    //   aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    //   <span class="navbar-toggler-icon"></span>
    // </button>
    // <div class="collapse navbar-collapse" id="navbarResponsive">
    //   <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
    //     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Strona Główna">
    //       <a class="nav-link" href="/views/owner/residence/residence.html">
    //         <i class="fa fa-building" aria-hidden="true"></i>
    //         <span class="nav-link-text">Nieruchomości</span>
    //       </a>
    //     </li>
    //     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Konta">
    //       <a class="nav-link" href="/views/owner/apartment/apartment.html">
    //         <i class="fa fa-home" aria-hidden="true"></i>
    //         <span class="nav-link-text">Mieszkania</span>
    //       </a>
    //     </li>
    //     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Wyciągi">
    //       <a class="nav-link" href="/views/owner/tenant/tenant.html">
    //         <i class="fa fa-users"></i>
    //         <span class="nav-link-text">Najemcy</span>
    //       </a>
    //     </li>
    //     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Wyciągi">
    //       <a class="nav-link" href="/views/owner/reading/readings.html">
    //         <i class="fa fa-bar-chart"></i>
    //         <span class="nav-link-text">Odczyty</span>
    //       </a>
    //     </li>
    //     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
    //       <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#invoice" data-parent="#exampleAccordion">
    //         <i class="fa fa-newspaper-o"></i>
    //         <span class="nav-link-text">Faktury</span>
    //       </a>
    //       <ul class="sidenav-second-level collapse" id="invoice">
    //         <li>
    //           <a href="/views/owner/invoice/energy.html">Energia</a>
    //         </li>
    //         <li>
    //           <a href="/views/owner/invoice/gas.html">Gaz</a>
    //         </li>
    //         <li>
    //           <a href="/views/owner/invoice/water.html">Woda</a>
    //         </li>
    //       </ul>
    //     </li>
    //     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
    //       <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#payment" data-parent="#exampleAccordion">
    //         <i class="fa fa-money"></i>
    //         <span class="nav-link-text">Opłaty</span>
    //       </a>
    //       <ul class="sidenav-second-level collapse" id="payment">
    //         <li>
    //           <a href="/views/owner/payment/energy.html">Energia</a>
    //         </li>
    //         <li>
    //           <a href="/views/owner/payment/gas.html">Gaz</a>
    //         </li>
    //         <li>
    //           <a href="/views/owner/payment/water.html">Woda</a>
    //         </li>
    //       </ul>
    //     </li>
    //     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Wyciągi">
    //       <a class="nav-link" href="/views/owner/meter/meter.html">
    //         <i class="fa fa-bars"></i>
    //         <span class="nav-link-text">Liczniki</span>
    //       </a>
    //     </li>
    //   </ul>
    //   <ul class="navbar-nav sidenav-toggler">
    //     <li class="nav-item">
    //       <a class="nav-link text-center" id="sidenavToggler">
    //         <i class="fa fa-fw fa-angle-left"></i>
    //       </a>
    //     </li>
    //   </ul>
    //   <ul class="navbar-nav ml-auto">
    //     <li class="nav-item dropdown">
    //       <a class="nav-link dropdown-toggle mr-lg-2" id="messagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    //         <i class="fa fa-user fa-fw"></i>
    //         <span class="d-lg-none">Messages
    //           <span class="badge badge-pill badge-primary">12 New</span>
    //         </span>
    //         <span class="indicator text-primary d-none d-lg-block">
    //           <i class="fa fa-fw fa-circle"></i>
    //         </span>
    //       </a>
    //       <div class="dropdown-menu" aria-labelledby="messagesDropdown">
    //         <a class="dropdown-item" href="#">
    //           <div class="dropdown-message">Strona najemcy</div>
    //         </a>
    //         <div class="dropdown-divider"></div>
    //         <a class="dropdown-item" href="#">
    //           <div class="dropdown-message">Ustawienia</div>
    //         </a>
    //         <div class="dropdown-divider"></div>
    //         <a class="dropdown-item" data-toggle="modal" data-target="#exampleModal">
    //           <div class="dropdown-message">
    //             <i class="fa fa-sign-out fa-fw"></i>Wyloguj</div>
    //         </a>
    //       </div>
    //     </li>
    //   </ul>
    // </div>
});