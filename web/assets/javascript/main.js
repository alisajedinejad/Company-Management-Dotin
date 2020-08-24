$(document).ready(function () {


});

var menuLast = "";

function openMenu() {
    $("#vacationIcon,#profileIcon,#checkIcon,#mailIcon,#createUserIcon").removeClass('active');

    if (!$('#menu5').hasClass("infinite")) {

        closeMenu();
    }
    else {
        $("#vacation,#profile,#check,#mail,#createUser").hide();
        $('#menu5').removeClass("infinite");
        $('#menu5').animate({
            left: '275px',
            transform: 'rotate(180deg)'
        });

        $("#profileName,#profileName2").fadeIn(300);


        $('#menu4').animate({
            height: '500px',
            width: '2px',
            top: '75px',
            left: '275px'
        });
        $('.menuItems').animate({
            width: '200px',
            left: '30px'
        });

        $('.menuItems p').fadeIn(500);

        $('#menu3').animate({
            top: '525px',
            left: '250px'
        });

        $('#menu2').animate({
            top: '75px',
            left: '185px'
        });

        $('#menu').animate({
            width: '250px'
        });
        $('#profileName2').animate({
            display: 'block'
        });
    }
}
function closeMenu() {

    $("#vacationIcon,#profileIcon,#checkIcon,#mailIcon,#createUserIcon").removeClass('active');


    $('.menuItems').animate({
        width: '40px',
        left: '30px'
    });
    $('.menuItems p').fadeOut();
    $('#menu5').animate({
        left: '125px',
        transform: 'rotate(0deg)'

    });
    $("#profileName , #profileName2").fadeOut(300);


    $('#menu5').addClass("infinite");
    $('#menu4').animate({
        height: '415px',
        width: '1px',
        top: '125px',
        left: '125px'
    });

    $('#menu3').animate({
        top: '75px',
        left: '100px'
    });

    $('#menu2').animate({
        top: '540px',
        left: '35px'
    });

    $('#menu').animate({
        width: '100px'
    });
    $('#profileName2').animate({
        display: 'none'
    });


}
function alert(msg, status) {


    $("#report div p").text(msg);
    $("#report").fadeIn(400);

    if (status == "success") {

        $("#report div img").attr("src", "/assets/images/tick.png");

    }
    else if (status == "fail") {
        $("#report div img").attr("src", "/assets/images/close.png");
    }
    else if (status == "warning") {
        $("#report div img").attr("src", "/assets/images/warn.png");

    }


}
function closeAlert() {

    $("#report").fadeOut(400);
}
function closeCorfirm(s) {
    if (s == "1") {

    }
    else {

    }

    $("#confirm").fadeOut(400);
}
function openMenuItemes(status) {
    closeMenu();
    $("#vacation,#profile,#check,#mail,#createUser").hide();
    $("#vacationIcon,#profileIcon,#checkIcon,#mailIcon,#createUserIcon").removeClass('active');

    if (status == "createUser") {


        if (menuLast != "createUser") {
            $("#createUser").show(300);
            $("#createUserIcon").addClass("active");
            menuLast = "createUser";
        }
        else {
            $("#createUserIcon").removeClass("active");

            menuLast = "";
        }

    }
    else if (status == "vacation") {


        if (menuLast != "vacation") {
            $("#vacation").show(300);
            $("#vacationIcon").addClass("active");
            menuLast = "vacation";
        }
        else {
            $("#vacationIcon").removeClass("active");

            menuLast = "";
        }

    }
    else if (status == "check") {

        if (menuLast != "check") {
            $("#check").show(300);
            $("#checkIcon").addClass("active");
            menuLast = "check";
        }
        else {
            $("#checkIcon").removeClass("active");

            menuLast = "";
        }
    }
    else if (status == "mail") {
        if (menuLast != "mail") {
            $("#mail").show(300);
            $("#mailIcon").addClass("active");
            menuLast = "mail";
        }
        else {
            $("#mailIcon").removeClass("active");

            menuLast = "";
        }
    }
    else if (status == "profile") {
        if (menuLast != "profile") {
            $("#profile").show(300);
            $("#profileIcon").addClass("active");
            menuLast = "profile";
        }
        else {
            $("#profileIcon").removeClass("active");

            menuLast = "";
        }
    }
}

function mySelect() {

    $(".disable").prop('disabled', true);
}

function activeAccept(status, e, number, id2) {


    var id = "#acceptD" + number + "," + "#denyD" + number + "," + "#pendingD" + number;

    $(id).removeClass("activeAccept");

    $("#" + e.id).addClass("activeAccept");

    var requestId = id2;


    $.ajax({
        url: "/Ajax",
        type: "POST",
        data: {
            requestId: requestId,
            status: status
        },
        success: function (response) {
            //$('.load').hide();

            response = JSON.parse(response);
            //console.log(response['result']);

            if (response['result'] == "done") {
                alert("ثبت انجام شد", "success");

            }
            else {
                alert("مشکلی پیش آمده دوباره امتحان کنید", "fail");

            }


        },
        error: function (err) {
            console.log(err);
        }

    });


}

function sendFile(id) {

    var fd = new FormData();
    var files = $('#file')[0].files[0];
    var extension = $('#file')[0].files[0].name.substr(( $('#file')[0].files[0].name.lastIndexOf('.') + 1));
    fd.append("file", files, id + "." + extension);

    console.log(fd);

    $.ajax({
        url: '/UploadServlet',
        type: 'post',
        data: fd,
        id: id,
        contentType: false,
        processData: false,

        success: function (response) {

            response = JSON.parse(response);

            if (response['result'] == "done") {
                alert("ثبت انجام شد", "success");

            }
            else {
                alert('file not uploaded', "fail");
            }
        }
    });
}