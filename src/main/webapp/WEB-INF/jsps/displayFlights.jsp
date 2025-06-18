<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flight Results</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom Styles */
        body {
            background-color: #f8f9fa; /* Light Gray */
            color: #343a40; /* Dark Gray */
            padding: 20px;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            border-spacing: 0;
            background-color: #fff; /* White */
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #dee2e6; /* Grayish */
        }
        th {
            background-color: #007bff; /* Primary Blue */
            color: #fff; /* White */
        }
        tr:nth-child(even) {
            background-color: #f8f9fa; /* Light Gray */
        }
        tr:hover {
            background-color: #e9ecef; /* Grayish Blue */
        }
        a {
            color: #007bff; /* Link Blue */
            text-decoration: none;
        }
        a:hover {
            color: #0056b3; /* Hover Blue */
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Flight Results</h2>
    <table>
        <thead>
        <tr>
            <th>Airlines</th>
            <th>Departure City</th>
            <th>Arrival City</th>
            <th>Departure Time</th>
            <th>Estimated Departure Time</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${flights}" var="flight">
            <tr>
                <td>${flight.operatingAirlines}</td>
                <td>${flight.departureCity}</td>
                <td>${flight.arrivalCity}</td>
                <td>${flight.dateOfDeparture}</td>
                <td>${flight.estimatedDepartureTime}</td>
                <td><a href="showCompleteReservation?id=${flight.id}">select</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- Bootstrap JS (Optional if you need JS functionality) -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
