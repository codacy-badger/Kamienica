$(document).ready(function () {

  $('.input-daterange').datepicker({
    //weekStart: 0,
    minViewMode: 1,
    todayBtn: true,
    language: "pl",
    format: 'yyyy-mm-dd'
  });

 
  //http://jsfiddle.net/2NVRD/1661/
  // $(function () {
  //   var dateFormat = "yy-mm-dd",
  //     from = $("#dateStart")
  //       .datepicker({
  //         defaultDate: "+1w",
  //         changeMonth: true,
  //         changeYear: true,
  //         changeDay: false,
  //         dateFormat: dateFormat
  //       })
  //       .on("change", function () {
  //         console.log(this);
  //         to.datepicker("option", "minDate", getDate(this));
  //       }),
  //     to = $("#dateEnd").datepicker({
  //       defaultDate: "+1w",
  //       changeMonth: true,
  //       changeYear: true,
  //       dateFormat: dateFormat
  //     })
  //       .on("change", function () {
  //         console.log(this);
  //         from.datepicker("option", "maxDate", getDate(this));
  //       });

  //   function getDate(element) {
  //     var date;
  //     try {
  //       date = $.datepicker.parseDate(dateFormat, element.value);
  //     } catch (error) {
  //       date = null;
  //     }

  //     return date;
  //   }
  // });
});



