<%--
  Created by IntelliJ IDEA.
  User: Code Academy
  Date: 9/12/2017
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
adminPage
<%--<input type="button" value="TEST" onclick="testJquery()"/>--%>
    <div id="divcontainer" class="container text-center">
        <div id="divrow" class="row content">
            <div id="divoptions" class="col-sm-2 sidenav">
                <p id="forteachers" onclick="modifTeacher()"><a href="#">Modify Teacher info</a></p>
                <p id="forstudents" onclick="modifSchoolchild()"><a href="#">Modify Schoolchild's info</a></p>
            </div>
            <div id="divmid" class="container col-sm-8 text-left">
                <div class="container col-sm-48"></div>
                <div></div>
                <div></div>
                <p id="newDivInsert" ondblclick="insert_new_div()">Ivesti nauja straipsni (double click me)</p>
            </div>
            <div id="divright" class="container col-sm-2 text-right">
                <jsp:include page="imgChangeRightSide.jsp"/>
                <input id="img" type="text" placeholder="Image Name"/>
                <input id="imgUrl" type="text" placeholder="Image Url"/>
                <input type="button" value="INSERT_FROM_URL" onclick="insert_img_from_url()"/>
<form method="post" action="admin/addImgFromComputerBrowser" modelAttribute="uploadForm">
                <input type="file" id="fileId" name="fileUpload" size="50"/>
                <input type="submit" value="Siusti_Pasirinktu_Failus"/>
</form>
                <input type="button" value="GET_IMG" onclick="get_img_from_DB()"/>
                <input type="button" value="DELETE_IMG" onclick="delete_img_from_DB()"/>
                <input type="button" value="TEST" onclick="test_add_existing_file()"/>
                <img id="img1" src="" />
            </div>
        </div>
    </div>
    <footer id="footer" class="container-fluid text-center">
        <p>Footer Text</p>
    </footer>

</body>
<script>
    function modifTeacher() {
        document.getElementById("divmid").innerHTML = "";
        $.ajax({
            type: "GET",
            url: "/allteachers",
            contentType: "application/json; charset=utf-8",
            success: function (teachlist, status) {
//                alert(teachlist[0].phone);  // skirti veikimui patikrinti
//                alert(status);
                // $(document).ready(function(){}) < Naudojamas, kai tik isijungia jsp langas arba triginama function
                // (siuo atveju funkcija paspaudziama ir suveikia)
                $(document).ready(function () {
                    var addNewTeacher = $('<input type="button" id="add_teach_button" value="ADD" class="add" onclick="add_teach_row()"/>').appendTo($('#divmid'));
                    document.getElementById("add_teach_button").style.display = "inline";
                    var table = $('<table id="tableteach" class="table-bordered"/>').appendTo($('#divmid'));
                    $('<tr id="tr">').appendTo(table)
                        .append('<th>name</th>')
                        .append('<th>surname</th>')
                        .append('<th>phone</th>')
                        .append('<th>info</th>')
                        .append('<th>update/save</th>')
                        .append('<th>delete</th>');
                    $(teachlist).each(function (i, tlist) {
                        $('<tr id="tr' + i + '">').appendTo(table)
                            .append($('<input id="id' + i + '" type="hidden"/>').text(tlist.id))
                            .append($('<td id="name' + i + '">').text(tlist.name))
                            .append($('<td id="surname' + i + '">').text(tlist.surname))
                            .append($('<td id="phone' + i + '">').text(tlist.phone))
                            .append($('<td id="info' + i + '">').text(tlist.info))
                            .append($('<td><input type="button" id="edit_teach_button' + i + '" value="EDIT" class="edit" onclick="edit_teach_row(' + i + ')"/></td>'))
                            .append($('<td><input type="button" id="delete_teach_button' + i + '" value="DELETE" class="delete" onclick="delete_teach_row(' + i + ')"></td>'));
                    });
                });
            }
        });
    }
</script>
<script>
    function edit_teach_row(val) {
        $("#tr" + val).hide();
        var newId = "#id" + val + "";
        var newName = "#name" + val + "";
        var newSurname = "#surname" + val;
        var newPhone = "#phone" + val;
        var newInfo = "#info" + val;
        $(document).ready(function () {
            $('<tr id="tr' + val + '">').appendTo($("#tableteach")).insertAfter($("#tr" + val))
                .append($('<input type="hidden" id="id" value="' + $('' + newId + '').text() + '"/>'))
                .append($('<td><input type="text" id="name" value="' + $('' + newName + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="text" id="surname" value="' + $('' + newSurname + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="number" id="phone" value="' + $('' + newPhone + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="text" id="info" value="' + $('' + newInfo + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="button" id="save_teach_button' + val + '" value="SAVE" class="edit" onclick="save_teach_row()"/></td>'))
                .append($('<td><input type="button" id="delete_teach_button' + val + '" value="DELETE" class="delete" onclick="delete_teach_row(' + val + ')"></td>'));
        });
    }
</script>
<script>
    function save_teach_row() {
        $.post("/updateteacher?id="+$('#id').val(),
            {
                name: $('#name').val(),
                surname: $('#surname').val(),
                phone: $('#phone').val(),
                info: $('#info').val()
            },
            function (data, success) {
//                alert(success);
                modifTeacher();
            }
        )
    }
</script>
<script>
    function add_teach_row() {
        document.getElementById("add_teach_button").style.display = "none";
        $(document).ready(function () {
            $('<tr id="tr">').appendTo($('#tableteach'))
                .append($('<td><input type="text" id="name" style="width: 50px"></td>'))
                .append($('<td><input type="text" id="surname" style="width: 50px"></td>'))
                .append($('<td><input type="number" id="phone" style="width: 50px"></td>'))
                .append($('<td><input type="text" id="info" style="width: 50px"></td>'))
                .append($('<td><input type="button" id="add_btn" value="ADD" class="add" onclick="add_teacher();"/></td>')).insertBefore(tr0);
        });
    }
</script>
<script>
    function add_teacher() {
        $.post("/addteacher",
            {
                name: $('#name').val(),
                surname: $('#surname').val(),
                phone: $('#phone').val(),
                info: $('#info').val()
            },
            function (data, status) {
                modifTeacher();
            }
        );
    }
</script>
<script>
    function delete_teach_row(val) {
        var newId = "#id" + val + "";
        $.post("/deleteteacher?id=" + $(''+newId+'').text()
        );
        $("#tr" + val).remove();
    }
</script>
<script>
    //    //"/test1" veikia > Isiunciam kaip atribudus, controleryje gaunam per @ModelAttribute
    //    //, isiunciam i Ajax ir cia gaunam JSON formata per ResponseEntity<>
    //    //"/test2" veikia > Isiunciam kaip atribudus, controleryje gaunam per @ModelAttribute
    //    //, isiunciam i Ajax ir cia gaunam JSON formata per return Object (pasirasom virs metodo @ResponeBody)
//        function testJquery() {
//    // arba "/test2"
//            $.post("/test1",
//                {
//                    name: "name",
//                    surname: "surname",
//                    phone: 1,
//                    info: "info"
//                },
//                function (data, status) {
//                    alert(status);
//                    alert(data.name);
//                }
//            )
//        }
</script>
<script>
    //    //"/test3" veikia > Isiunciam kaip Json formata, controleryje gaunam per @RequestBody
    //    //, isiunciam i Ajax ir cia gaunam JSON formata per ResponseEntity<>
    //    //"/test4" veikia > Isiunciam kaip Json formata, controleryje gaunam per @RequestBody
    //    //, isiunciam i Ajax ir cia gaunam JSON formata per return Object (pasirasom virs metodo ResponeBody)
    //    var data_to_send =
    //        {
    //            name: "name",
    //            surname: "surname",
    //            phone: 1,
    //            info: "info"
    //        };
    //
    //    function testJquery() {
    //        $.ajax({
    //            type: "POST",
    //            url: "/test4",
    //            contentType: "application/json; charset=utf-8",
    //            data: JSON.stringify(data_to_send),
    //            dataType: "json",
    //            success: function (data, status) {
    //                alert(status);
    //                alert(data);
    //                alert(data.name);
    //            }
    //        });
    //    }
</script>
<script>
//        // "/test5" veikia > nieko nesiuniam, controleryje is DB gaunam mokiniu lista, ji persiunciam i antra JSP
//        // ,antram jsp ciklas persukamas ir pagrazina visa informacija html.
//        // Gauta info isiunciam i Ajax ir cia gaunam visa antro jsp turini su loopais atliktais
//        function testJquery() {
//            $.ajax({
//                type: "GET",
//                url: "/test5",
//                success: function (data, status) {
//                    alert(status);
//                    document.getElementById("divmid").innerHTML = data;
//                    alert(data);
//                }
//            });
//        }
</script>
<script>
    function modifSchoolchild() {
        document.getElementById("divmid").innerHTML = "";
        $.ajax({
            type: "GET",
            url: "/allchildren",
            contentType: "application/json; charset=utf-8",
            success: function (childlist, status) {
                $(document).ready(function () {
                    var addNewTeacher = $('<input type="button" id="add_child_button" value="ADD" class="add" onclick="add_child_row()"/>').appendTo($('#divmid'));
                    document.getElementById("add_child_button").style.display = "inline";
                    var table = $('<table id="tableteach" class="table-bordered"/>').appendTo($('#divmid'));
                    $('<tr id="tr"/>').appendTo(table)
                        .append('<th>name</th>')
                        .append('<th>surname</th>')
                        .append('<th>parentinfo</th>')
                        .append('<th>email</th>')
                        .append('<th>address</th>')
                        .append('<th>name</th>')
                        .append('<th>expired date</th>')
                        .append('<th id="thIsValid">is valid</th>')
                        .append('<th id="thUpdate">update/save</th>')
                        .append('<th id="thDelete">delete</th>');
                    $(childlist).each(function (i, slist) {
                        $('<tr id="tr' + i + '">').appendTo(table)
                            .append($('<input id="id' + i + '" type="hidden"/>').text(slist.id))
                            .append($('<td id="name' + i + '">').text(slist.name))
                            .append($('<td id="surname' + i + '">').text(slist.surname))
                            .append($('<td id="parentinfo' + i + '">').text(slist.parentinfo))
                            .append($('<td id="email' + i + '">').text(slist.email))
                            .append($('<td id="address' + i + '">').text(slist.address))
                            .append($('<input id="idcard' + i + '" type="hidden"/>').text(slist.libraryCard.id))
                            .append($('<td id="namecard' + i + '">').text(slist.libraryCard.name))
                            .append($('<td id="expiredDatecard' + i + '">').text(slist.libraryCard.expiredDate))
                            .append($('<td id="statuscard' + i + '">').text(slist.libraryCard.status))
                            .append($('<input id="teacher_id' + i + '" type="hidden"/>').text(slist.teacher.id))
                            .append($('<td><input type="button" id="edit_child_button' + i + '" value="EDIT" class="edit" onclick="edit_child_row(' + i + ')"/></td>'))
                            .append($('<td><input type="button" id="delete_child_button' + i + '" value="DELETE" class="delete" onclick="delete_child_row(' + i + ')"/></td>'));
                    });
                });
            }
        });
    }
</script>
<script>
    function edit_child_row(val) {
        $("#tr" + val).hide();
        var newId = "#id" + val + "";
        var newName = "#name" + val + "";
        var newSurname = "#surname" + val;
        var newParentinfo = "#parentinfo" + val;
        var newEmail = "#email" + val;
        var newAddress = "#address" + val;
        var newIdCard = "#idcard" + val;
        var newNameCard = "#namecard" + val;
        var newExpiredDate = "#expiredDatecard" + val;
        var newStatusCard = "#statuscard" + val;
        var newTeacher_id = "#teacher_id" + val;
        $(document).ready(function () {
            $('<th>teacher_id </th>').appendTo($("#tr")).insertAfter($("#thIsValid"));
            $('<tr id="tr' + val + '">').appendTo($("#tableteach")).insertAfter($("#tr" + val))
                .append($('<input type="hidden" id="id" value="' + $('' + newId + '').text() + '"/>'))
                .append($('<td><input type="text" id="name" value="' + $('' + newName + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="text" id="surname" value="' + $('' + newSurname + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="text" id="parentinfo" value="' + $('' + newParentinfo + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="text" id="email" value="' + $('' + newEmail + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="text" id="address" value="' + $('' + newAddress + '').text() + '" style="width: 50px"/></td>'))
                .append($('<input type="hidden" id="idcard" value="' + $('' + newIdCard + '').text() + '" style="width: 50px"/>'))
                .append($('<td><input type="text" id="namecard" value="' + $('' + newNameCard + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="date" id="expiredDatecard" value="' + $('' + newExpiredDate + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="checkbox" checked="checked" id="statuscard" value="' + $('' + newStatusCard + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="text" id="teacher_id" value="' + $('' + newTeacher_id + '').text() + '" style="width: 50px"/></td>'))
                .append($('<td><input type="button" id="save_teach_button' + val + '" value="SAVE" class="edit" onclick="save_child_row()"/></td>'))
                .append($('<td><input type="button" id="delete_teach_button' + val + '" value="DELETE" class="delete" onclick="delete_child_row(' + val + ')"/></td>'));
            // pasetinam true or false
            if($(''+newStatusCard+'').text() === "false"){
                $('#statuscard').prop('checked', false);
            }
        });
    }
</script>
<script>
    function save_child_row() {
//        $.post("/updatechild?id="+$('#id').val()+"&teacher_id="+$('#teacher_id').val()+"&idcard="+$('#idcard').val(),
//            {
//                name: $('#name').val(),
//                surname: $('#surname').val(),
//                parentinfo: $('#parentinfo').val(),
//                email: $('#email').val(),
//                address: $('#address').val(),
//                libraryCard: {
//                    name: $('#namecard').val(),
//                    expiredDate: $('#expiredDatecard').val(),
//                    status: $('#statuscard').prop('checked')
//                }
//            },
//            function(data, success){
//                modifSchoolchild();
//            }
//        );

        // 1. success negrazina del void?
        // 2. kodel per model attribute nesugauto?

        var dataToSend = {
            name: $('#name').val(),
            surname: $('#surname').val(),
            parentinfo: $('#parentinfo').val(),
            email: $('#email').val(),
            address: $('#address').val(),
            libraryCard: {
                name: $('#namecard').val(),
                expiredDate: $('#expiredDatecard').val(),
                status: $('#statuscard').prop('checked')
            }
        };
        $.ajax({
            type: "POST",
            url: "/updatechild?id=" + $('#id').val() + "&teacher_id=" + $('#teacher_id').val() + "&idcard=" + $('#idcard').val(),
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(dataToSend),
            dataType: "json"
        });
        modifSchoolchild();
    }
</script>
<script>
    function add_child_row(val) {
        document.getElementById("add_child_button").style.display = "none";
        $(document).ready(function () {
            $('<th>teacher_id </th>').appendTo($("#tr")).insertAfter($("#thIsValid"));
            $('<tr id="tr">').appendTo($('#tableteach'))
                .append($('<td><input type="text" id="name" style="width: 50px"></td>'))
                .append($('<td><input type="text" id="surname" style="width: 50px"></td>'))
                .append($('<td><input type="text" id="parentinfo" style="width: 50px"></td>'))
                .append($('<td><input type="text" id="email" style="width: 50px"></td>'))
                .append($('<td><input type="text" id="address" style="width: 50px"></td>'))
                .append($('<input type="hidden" id="idcard" style="width: 50px"/>'))
                .append($('<td><input type="text" id="namecard" style="width: 50px"/></td>'))
                .append($('<td><input type="date" id="expiredDatecard" style="width: 50px"/></td>'))
                .append($('<td><input type="checkbox" checked="checked" id="statuscard" style="width: 50px"/></td>'))
                .append($('<td><input type="number" id="teacher_id" style="width: 50px"></td>'))
                .append($('<td><input type="button" id="add_btn" value="ADD" class="add" onclick="add_child();"/></td>')).insertBefore(tr0);
        });
    }
</script>
<script>
    function add_child() {
        var dataToSend = {
            name: $('#name').val(),
            surname: $('#surname').val(),
            parentinfo: $('#parentinfo').val(),
            email: $('#email').val(),
            address: $('#address').val(),
            libraryCard: {
                name: $('#namecard').val(),
                expiredDate: $('#expiredDatecard').val(),
                status: $('#statuscard').prop('checked')
            }
        };
        $.ajax({
            type: "POST",
            url: "/addchild?teacher_id=" + $('#teacher_id').val(),
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(dataToSend),
            dataType: "json"
        });
        modifSchoolchild();
    }
</script>
<script>
    function delete_child_row(val) {
        var newId = "#id" + val + "";
        $.post("/deletechild?id=" + $(''+newId+'').text()
        );
        $("#tr" + val).remove();
    }
</script>
<%--Page Data--%>
<script>
    window.onload = function construct_mid_div() {
        $.get("/admin/",
            function (pagedata, status) {
                // pakuriam divEilutes pagal elementu listo ilgi
                for(a=0; a < (pagedata.length)/3; a++){
                    var tablerow = $('<div id="divrow'+a+'" class="col-md-12"/>').appendTo($('#divmid'));
                }
                // kas trecia reiksme <div> priskiriam kitai divEilutei
                var rowId = 0;
                $(pagedata).each(function(i,pd){
                    // naudojam console tikrinti lista, kad nemestum alertu (nesiukslintum)
                    // console.log(i+'   '+pd.id);
                    $('<div id="div'+i+'" class="col-md-4" contenteditable="true" ondblclick="insert_option_buttons('+i+')"/>').appendTo($('#divrow'+rowId))
                        .append($('<input type="hidden" id="id'+i+'" contenteditable="true"/>').text(pd.id))
                        .append($('<h1 id="header'+i+'" contenteditable="true"/>').text(pd.header))
                        .append($('<p id="textarea'+i+'" contenteditable="true"/>').text(pd.textarea));
                    // kadangi listas prasideda nuo 0, tad is karto po vieno duomens mestu i kita eilute, todel i+1
                    if((i+1)%3 == 0){rowId=rowId+1}
                });
            });
    };
</script>
<script>
    function insert_option_buttons(val) {
        $('<input type="button" id="update_button' + val + '" value="UPDATE" class="update" onclick="update_div_data('+val+')"/>').appendTo($("#div"+val));
        $('<input type="button" id="delete_button' + val + '" value="DELETE" class="delete" onclick="delete_div_data('+val+')"/>').appendTo($("#div"+val));
    }
</script>
<script>
    function update_div_data(val) {
        $.post("/admin/updatePageData?id="+$("#id"+val).text(),
            {
                header: $("#header"+val).text(),
                textarea: $("#textarea"+val).text()
            },
            function (status) {
                alert(status);
                $("#update_button"+val).hide();
                $("#delete_button"+val).hide();
            }
        );
    }
</script>
<script>
    function delete_div_data(val) {
        $.post("/admin/deletePageData?id="+$("#id"+val).text(),
            function (status) {
                alert(status);
                location.reload();
            }
        );
    }
</script>
<script>
    function insert_new_div() {
        $('<div id="div" class="col-md-4" contenteditable="true"/>').insertAfter($('#newDivInsert'))
            .append($('<textarea id="header" contenteditable="true"/>').attr("placeholder", "Header"))
            .append($('<textarea id="textarea" contenteditable="true"/>').attr("placeholder", "Text Area"))
            .append($('<input type="button" id="add_button" value="ADD" class="add" onclick="add_div()"/>'));

    }
</script>
<script>
    function add_div() {
        $.post("/admin/addPageData",
            {
                header: $("#header").val(),
                textarea: $("#textarea").val()
            },
            function (status) {
                alert(status);
                location.reload();
            }
        );
    }
</script>
<%--Image--%>
<script>
    function insert_img_from_url() {
        $.post("/admin/addImgFromUrl",
            {
                imgname: $('#img').val(),
                imgUrl: $('#imgUrl').val()
            },
            function (status, error) {
                alert(status);
            })
        . fail(function (xhr, status, error) {
                alert(xhr.responseText);
        });
    }
</script>
<script>
    function get_img_from_DB() {
        $.get("/admin/getImgList",
            function (data) {
                alert(data[1].imgname);
                alert(data[1].imgbyte);
                document.getElementById("img1").src = "data:image/png;base64," + data[1].imgbyte;
            }
        )
    }
</script>
<script>
    function delete_img_from_DB() {

    }
</script>
<script>
    function test_add_existing_file() {
        $.post("/admin/test1",
        function (data, status) {
            alert(status);
            alert(data.imgbyte);
            document.getElementById("img1").src = "data:image/png;base64," + data.imgbyte;
        })
    }
</script>
</html>
