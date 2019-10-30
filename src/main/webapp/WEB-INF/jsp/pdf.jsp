<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8"></meta>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"></meta>
    <title>座次表</title>
    <style>
        @page {
            size: A4;
            margin: 0;
        }

        html, body {
            margin: 0;
            padding: 0;
        }

        h2, h4 {
            text-align: center;
        }

        .wrap {
            width: 794px;
            /* height: 1123px; */
            height: auto;
            margin: 0 auto;
        }

        ul {
            width: 100%;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        li {
            list-style: none;
            width: 20%;
            height: auto;
            float: left;
            margin-bottom: 3px;
            text-align: center;
        }

        li img {
            width: 120px;
        }

        li p {
            margin: 0;
            text-align: center;
        }
    </style>
</head>
<body bgcolor='white' style='font-family:SimSun; height:100%;' screen_capture_injected='true' ryt11773='1'>
kkkkk
<c:set var="length" scope="session" value="${fn:length(stuExamRooms)}"/>
<c:forEach items="${stuExamRooms}" var="room" varStatus="i">
    <c:set var="roompage" scope="session" value="${fn:length(room.stuSubjectRooms)}"/>

    <h4> 第${room.roomCode}考场 （个数:${roompage}）</h4>
    <div class="wrap">
        <ul>
            <c:forEach items="${room.stuSubjectRooms}" var="var" varStatus="q">
                <li>
                    <img src="${path}/resources/photo/${var.ksh}.jpg"></img>
                    <p>${var.seatNum} ${var.name}</p>
                </li>

                <c:set var="count" scope="session" value="${q.count%35}"/>

                <c:if test="${count==0}">
                    <p style="page-break-after:always;"></p>
                </c:if>
            </c:forEach>
        </ul>
    </div>


    <c:if test="${length!=i.count}">
        <p style="page-break-after:always;"></p>
    </c:if>


</c:forEach>


<%--<h4> 第${rooCode}考场 （个数:${fn:length(list)}）</h4>
<div class="wrap">
    <ul>

        <c:forEach items="${list}" var="var" varStatus="q">
            <li>
                <img src="${path}/resource/img/dz/mg_photo.jpg"></img>
                <p>${var.sear} ${var.name}</p>
            </li>

            <c:set var="count" scope="session" value="${q.count%35}"/>
            <c:if test="${count==0}">
                <p style="page-break-after:always;"></p>
            </c:if>


        </c:forEach>


    </ul>
</div>--%>
</body>
</html>