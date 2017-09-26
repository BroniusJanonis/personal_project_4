<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="divmid" class="container col-sm-8 text-left">
    <div id="divmidrow1" class="row content">
        <div id="divmid1" class="col-sm-4 text-left">
            <p>Padaryta per JQuerry kreipiantis per controleri ir jame issikvieciant kito jsp visa info</p>
        </div>
        <div id="divmid2" class="col-sm-4 text-left">
            <p>Mokiniai turi atvykti laiku</p>
        </div>
        <div id="divmid3" class="col-sm-4 text-left">
            <p>Vaiku pamoku tvarkarastis</p>
        </div>
    </div>
    <div id="divmidrow2" class="row content">
        <div id="divmid4" class="col-sm-4 text-left">
            <p>Papildoma vaikams info</p>
        </div>
        <div>
            <p>Mokytoju sarasas</p>
            <table>
                <tr>
                    <th>Mokytojo vardas</th>
                    <th>Mokytojo pavarde</th>
                </tr>
                <c:forEach var="tlist" items="${teacherlist}">
                    <tr>
                        <td>${tlist.getName()}</td>
                        <td>${tlist.getSurname()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>