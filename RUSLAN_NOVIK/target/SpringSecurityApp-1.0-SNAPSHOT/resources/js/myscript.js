function Delete_city(j) {

    var data = {
        "id":j
    };
    $.ajax({
        url: "/admin",
        data:data,
        type: "POST"
    }).done(function(data){
        $('#city'+j).hide(400);
    })
    ;
}

$("#button_add_city").click(function(){

    var data = {
        "name": $("#name").val()
    };
    $.ajax({
        url:"/admin",
        data:data,
        type:"POST"
        }).done(function(){
        alert("Добавлен новый город")
    })
});

$("#button_add_polyclinic").click(function(){
    var data = {
        "polyclinic_city": $("#polyclinic_city").val(),
        "polyclinic_name": $("#polyclinic_name").val(),
        "polyclinic_address": $("#polyclinic_address").val()
    };
      $.ajax({
          url: "/admin",
          data: data,
          type: "POST"
      }).done(function(){
          alert("Добавлена новая поликлиника")
    })
});
