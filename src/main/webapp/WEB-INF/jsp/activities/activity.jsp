<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
activitiesEachPage
<form>
    <div id="divcontainer" class="container text-center">
        <div id="divrow" class="row content">
            <div id="divoptions" class="col-sm-2 sidenav">
                <p id="football"><a href="/activities/?activities=football">Football team</a></p>
                <p id="basketball"><a href="/activities/?activities=basketball">Basketball team</a></p>
                <p id="volleyball"><a href="/activities/?activities=volleyball">Tinklinis</a></p>
            </div>
            <div id="divmid" class="container col-sm-8 text-left">
                <div id="divmidrow1" class="row content">
                    <p>Padaryta per href redirectinant i kita jsp langa, kuriame, pagal musu pasirinkta activity, atspausdins vaiku sarasa</p>
                </div>
                <div id="divmidrow2" class="row content">
                    <div>
                        <p>Mokiniu, kurie lanko si bureli, sarasas:</p>
                        <table>
                            <tr>
                                <th>Mokinio vardas</th>
                                <th>Mokinio pavarde</th>
                            </tr>
                            <c:forEach var="slist" items="${schoolchildlist}">
                                <tr>
                                    <td>${slist.getName()}</td>
                                    <td>${slist.getSurname()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
            <jsp:include page="../imgChangeRightSide.jsp"/>
        </div>
    </div>
    <footer id="footer" class="container-fluid text-center">
        <p>Footer Text</p>
    </footer>
</form>
</body>
</html>