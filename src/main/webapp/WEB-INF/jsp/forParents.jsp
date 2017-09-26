<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="divmid" class="container col-sm-8 text-left">
    <div id="divmidrow1" class="row content">
        <div id="divmid1" class="col-sm-4 text-left">
            <p>Padaryta per JQuerry kreipiantis per controleri ir jame issikvieciant kito jsp visa info</p>
        </div>
        <div id="divmid2" class="col-sm-4 text-left">
            <p>Vaikai nenori, kad tevams butu pateikta papildoma informacija</p>
        </div>
        <div id="divmid3" class="col-sm-4 text-left">
            <p>Vaiko pamoku tvarkarastis</p>
        </div>
    </div>
    <div id="divmidrow2" class="row content">
        <div id="divmid4" class="col-sm-4 text-left">
            <p>Papildoma tevams info</p>
        </div>
        <div>
            <p>Mokiniu ir Mokytoju sarasas</p>
            <table>
                <tr>
                    <th>Mokinio vardas</th>
                    <th>Mokinio pavarde</th>
                    <th>Mokinio mokytojas</th>
                </tr>
                <c:forEach var="stlist" items="${childteacherlist}">
                    <tr>
                        <td>${stlist.getName()}</td>
                        <td>${stlist.getSurname()}</td>
                        <td>${stlist.getTeacher().getName()} ${stlist.getTeacher().getSurname()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>