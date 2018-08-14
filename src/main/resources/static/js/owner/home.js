const url = "/api/v1/owner";

$(document).ready(function () {

  $.getJSON(url, function (result) {
    if (result === null) {
      showModal("Brak danych", "Dodaj brakujące dane.");
    } else {
    const invoiceMedia = parseMedia(result.oldestInvoice.media);
    const readingMedia = parseMedia(result.oldestReading.media);

      $("#resicencesNum").text(result.numOfResidences);
      $("#oldestInvoice").text(`${invoiceMedia}: ${result.oldestInvoice.invoiceDate}`);
      $("#oldestReading").text(`${readingMedia}: ${result.oldestReading.readingDate}`);
      $("#emptyApartments").text(result.emptyApartments);

      $("#oldestInvoiceCard").addClass(determineCardColorForDate(result.oldestInvoice.invoiceDate));
      $("#oldestReadingCard").addClass(determineCardColorForDate(result.oldestReading.readingDate));
      $("#emptyApartmentsCard").addClass(lookForEmptyApartments(result.emptyApartments));
    }
  }).fail(function (response) {
    showModal("Bład servera", response.responseJSON.message);
  });
  
});

determineCardColorForDate = function (date) {
  const parsedDay = parseDate(date);
  const days = datediff(parsedDay);

  if(days < 30) {
    return "bg-success";
  } else if (days < 60) {
    return "bg-warning";
  }
  return "bg-danger";
};

lookForEmptyApartments = function(apartments) {
  if(apartments > 0) {
    return "bg-danger";
  }
  return "bg-success";
};


parseMedia = function (arg) {
  if(arg === "ENERGY") {
    return "Energia";
  }
  if(arg === "GAS") {
    return "Gaz";
  }
  return "Woda";
};

function parseDate(str) {
  const mdy = str.split('-');
  return new Date(mdy[2], mdy[1], mdy[0]);
}

function datediff(first) {
  const second = new Date();
  return Math.round((second-first)/(1000*60*60*24));
}