$(document).ready(function (){
    $(".btn-delete-role").click(function (){
        var id = $(this).attr("roleid")
        var This = $(this)

        $.ajax({
            method: "GET",
            url: "http://localhost:8080/CRM/role/delete?id="+id,
            // data: { name: "John", location: "Boston" }
        }).done(function( result ) {
            This.closest("tr").remove();
            console.log("ket qua ", result)
        });

    })
})