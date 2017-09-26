<%--
  Created by IntelliJ IDEA.
  User: Code Academy
  Date: 9/11/2017
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
</head>
<body>

<%--Include Header--%>
<jsp:include page="header.jsp"/>
firstPage
<form>

    <div id="divcontainer" class="container text-center">
                <div id="divrow" class="row content">
                    <div id="divoptions" class="col-sm-2">
                        <p id="forteachers" onclick="getMeniuContentOfFistPage('forTeachers')"><a href="#">For Teachers</a></p>
                        <p id="forstudents" onclick="getMeniuContentOfFistPage('forChildren')"><a href="#">For Studends</a></p>
                        <p id="forparents" onclick="getMeniuContentOfFistPage('forParents')"><a href="#">For Parents</a></p>
                    </div>
                    <div id="divmid" class="container col-sm-8 text-left">
                        <div id="divmidrow1" class="row content">
                            <div id="divmid1" class="col-sm-4 text-left">
                                <h1>Welcome</h1>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                <hr>
                                <h3>Test</h3>
                                <p>Lorem ipsum...</p>
                            </div>
                            <div id="divmid2" class="col-sm-4 text-left">
                                <h1>Welcome</h1>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                <hr>
                                <h3>Test</h3>
                                <p>Lorem ipsum...</p>
                            </div>
                            <div id="divmid3" class="col-sm-4 text-left">
                                <h1>Welcome</h1>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                <hr>
                                <h3>Test</h3>
                                <p>Lorem ipsum...</p>
                            </div>
                        </div>
                        <div id="divmidrow2" class="row content">
                            <div id="divmid4" class="col-sm-4 text-left">
                                <h1>Welcome</h1>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                <hr>
                                <h3>Test</h3>
                                <p>Lorem ipsum...</p>
                            </div>
                        </div>
                    </div>
        <jsp:include page="imgChangeRightSide.jsp"/>
    </div>
    </div>

    <footer id="footer" class="container-fluid text-center">
        <p>Footer Text</p>
    </footer>
</form>
</body>
<script>
    function getMeniuContentOfFistPage(val) {
        $.ajax({
            type : "GET",
            url : "/firstPage?address="+val,
            contentType:"text/html; charset=utf-8",
            success: function(data){
                document.getElementById("divmid").innerHTML = data;
            }
        });
    }
</script>
</html>
