$(document).ready(function() {
  $("form").on("submit", function(event) {
    if($("#name").val() == '') {
    event.preventDefault();
      alert("이름 입력")
      $("#name").focus()
      return;
    }
      if($("#addr").val() == '') {
      event.preventDefault();
      alert("주소 입력")
      $("#addr").focus()
      return;
    }
  })
})
