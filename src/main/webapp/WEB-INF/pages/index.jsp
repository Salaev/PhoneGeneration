<%--
  Created by IntelliJ IDEA.
  User: amsalaye
  Date: 26.09.2017
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <style>
        <%@include file="/WEB-INF/css/styles.css"%>
    </style>

    <title>Генератор телефонов</title>

 </head>
<body>

    <form class="form-container" method="get" action="generation">
        <div class="form-title"> <h2>Генератор телефонных номером </h2> </div>

        <div class="form-title"> <h4> Последние сгенерированные данные </h4> </div>
<%--Последние сгенирированные данные--%>
        <div class="form-title"> IMSI: &nbsp &nbsp &nbsp ${IMSI} </div>
        <div class="form-title"> ICCID: &nbsp &nbsp ${ICCID} </div>
        <div class="form-title"> MSISDN: ${MSISDN} </div>
<br>
        <%-- поля для ввода новых данные для начала генерации--%>
        <div class="form-title">IMSI:&nbsp &nbsp &nbsp
            <input class="form-field" type="text" name="IMSI1" value=${IMSI1} /> </div>

        <div class="form-title">ICCID:&nbsp &nbsp
        <input class="form-field" type="text" name="ICCID1" value=${ICCID1} /> </div>

        <div class="form-title">MSISDN:
            <input class="form-field" type="text" name="MSISDN1" value=${MSISDN1} /> </div>

        <%--блок с полями количесвтво генераций--%>
        <div class="form-title">Student
            <input class="form-fieldBox" type="Student" name="Student" value="20"/>
            &nbsp &nbsp &nbsp &nbsp  Test <input class="form-fieldBox" type="Test" name="Test" value="2" /> </div>

        <div class="form-title">Teacher
            <input class="form-fieldBox" type="Teacher" name="Teacher" value="5"/>
        Rezerve <input class="form-fieldBox" type="Rezerve" name="Rezerve" value="5" /> </div>

        <%--чек боксы --%>

        <input type="checkbox" class="checkbox" id="kontrakt"  name="id" value="kontrakt" checked = "checked" />
        <label for="kontrakt">Контрактные sim &nbsp </label>

        <input type="checkbox" class="checkbox" id="komplect" name="id" value="komplect" checked = "checked"/>
        <label for="komplect"> Комлект </label>

        <%--Кнопка генерации--%>
        <div class="submit-container">
            <input class="submit-button" type="submit" value="Generation" />
        </div>

        <div class="form-title"> <h4> ${description} </h4> </div>

    </form>
<form class="form-containerClean" method="get" action="clean">
    <%--Кнопка очистки--%>
    <div class="submit-container">
        <input class="submit-button" type="submit" value="Clean" />
    </div>
</form>



</body>
</html>
