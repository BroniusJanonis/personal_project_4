<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="divmid" class="container col-sm-8 text-left">
    <div id="divmidrow1" class="row content">
        <div id="divmid1" class="col-sm-4 text-left">
            <p>Padaryta per JQuerry kreipiantis per controleri ir jame issikvieciant kito jsp visa info</p>
        </div>
        <div id="divmid2" class="col-sm-4 text-left">
            <p>Mokytojai turi atvykti laiku</p>
        </div>
        <div id="divmid3" class="col-sm-4 text-left">
            <p>Mokytoju pamoku tvarkarastis</p>
        </div>
    </div>
    <div id="divmidrow2" class="row content">
        <div id="divmid4" class="col-sm-4 text-left">
            <p>Papildoma mokytojams info</p>
        </div>
        <div>
            <p>Mokiniu sarasas</p>
            <table>
                <tr>
                    <th>Mokinio vardas</th>
                    <th>Mokinio pavarde</th>
                    <th>Tevu info</th>
                </tr>
                <c:forEach var="slist" items="${schoolchildlist}">
                    <tr>
                        <td>${slist.getName()}</td>
                        <td>${slist.getSurname()}</td>
                        <td>${slist.getParentinfo()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>