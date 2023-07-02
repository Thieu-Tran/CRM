//Khi nào trang html nội dung đã được nạp vào trình duyệt thì sẽ chạy code bên trong function
$(document).ready(function (){
    $(".btn-delete-task").click(function (){
        var id=$(this).attr("taskid")
        var This=$(this)

        $.ajax({
            method: "GET",
            url: "http://localhost:8080/CRM/task/delete?id="+id,
            // data: { name: "John", location: "Boston" }
        }).done(function( result ) {
            This.closest("tr").remove();
        });

    })
})