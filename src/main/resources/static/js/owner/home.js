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
      $("#emptyApartments").text(result.numOfResidences);
    }
  }).fail(function (response) {
    showModal("Bład servera", response.responseJSON.message);
  });
  
});

parseMedia = function (arg) {
  if(arg === "ENERGY") {
    return "Energia";
  }
  if(arg === "GAS") {
    return "Gaz";
  }
  return "Woda";
};