<%-- 
    Document   : simulator_security
    Created on : Oct 18, 2014, 12:04:13 PM
    Author     : Zheng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simulators</title>
        <script src="js/jquery-1.11.1.min.js"></script>
    </head>
    <body>



        <form id="SecuritySimulatorForm">
            <label>Simulate Security Alarms</label>

            <table name='security'>
                <tr>
                    <td>

                        <select id="detectors">
                            <c:forEach items="${detectors}" var="detector">
                                <option value="${detector.detectorid}"><c:out value="${detector.detectorid}\: ${detector.description}" /></option>
                            </c:forEach>

                        </select>
                    </td>
                    <td>
                        <input id="sendBtn" type="submit" value="Send Alarm" />
                    </td>
                </tr>
            </table>
        </form>
        <div id="result"></div>



        <script>



            $("#SecuritySimulatorForm").submit(function (e) {
                //Stops the submit request
                e.preventDefault();

                $("#result").empty();
                var detectorid = $("#detectors option:selected").val();
                $.post("SecuritySimulator", {detectorid: detectorid},
                function (data, status) {
                    $("#result").empty().append("Response: " + data + "\nStatus: " + status);
                });
               
            });
        </script>

    </body>
</html>
