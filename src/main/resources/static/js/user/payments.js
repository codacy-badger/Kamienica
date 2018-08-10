let table;
const url = "/api/v1/user/payments";

$(document).ready(function () {
  drawTableFromEndpoint();
});

drawTableFromEndpoint = function () {
  $.getJSON(url, function (result) {
    if (result.length === 0) {
      $("#tableContent").hide();
    } else {
      $("#tableContent").show();
      $("#tableContent").removeAttr("hidden");
      objectList = result;
      drawTable(result);
    }
  });
};

drawTable = function (objectList) {
  table = $("#dataTable").DataTable({
    data: objectList,

    columns: [
      {data: "paymentDate"},
      {data: "paymentAmount"},
      {
        data: null,
        render: function (data) {
          const media = data.invoice.media;
          if(media === "ENERGY") {
            return "Energia";
          }
          if(media === "GAS") {
            return "Gaz";
          }
          return "Woda";
        }
      },
    ],
    language: tableTranslation
  });
};

const tableTranslation = {
  decimal: ",",
  processing: "Przetwarzanie...",
  search: "Szukaj:",
  lengthMenu: "Pokaż _MENU_ pozycji",
  info: "Pozycje od _START_ do _END_ z _TOTAL_ łącznie",
  infoEmpty: "Pozycji 0 z 0 dostępnych",
  infoFiltered: "(filtrowanie spośród _MAX_ dostępnych pozycji)",
  infoPostFix: "",
  loadingRecords: "Wczytywanie...",
  zeroRecords: "Nie znaleziono pasujących pozycji",
  emptyTable: "Brak danych",
  paginate: {
    first: "Pierwsza",
    previous: "Poprzednia",
    next: "Następna",
    last: "Ostatnia"
  },
  aria: {
    sortAscending: ": aktywuj, by posortować kolumnę rosnąco",
    sortDescending: ": aktywuj, by posortować kolumnę malejąco"
  }
};