<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Passenger Details</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom Styles */
        body {
            background-color: #f8f9fa; /* Light Gray */
            color: #343a40; /* Dark Gray */
            padding: 20px;
        }
        h2 {
            color: #007bff; /* Primary Blue */
        }
        form {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff; /* White */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ced4da; /* Grayish */
            border-radius: 5px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff; /* Primary Blue */
            color: #fff; /* White */
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3; /* Dark Blue */
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Complete Reservation</h2>
    <p>Airline: ${flight.operatingAirlines}</p>
    <p>Departure City: ${flight.departureCity}</p>
    <p>Arrival City: ${flight.arrivalCity}</p>
    <form action="completeReservation" method="post">
        <h2>Passenger Details</h2>
        <input type="text" name="passengerFirstName" placeholder="First Name" required>
        <input type="text" name="passengerLastName" placeholder="Last Name" required>
        <input type="text" name="passengerEmail" placeholder="Email" required>
        <input type="text" name="passengerPhone" placeholder="Phone" required>

        <h2>Card Details</h2>
        <input type="text" name="nameOnTheCard" placeholder="Name on the Card" required>
        <input type="text" name="cardNumber" placeholder="Card Number" required>
        <input type="text" name="expirationDate" placeholder="Expiry Date (MM/YY)" required>
        <input type="text" name="securityCode" placeholder="Security Code" required>

        <input type="hidden" name="flightId" value="${flight.id}">
        <input type="submit" value="Confirm">
    </form>
</div>
<!-- Bootstrap JS (Optional if you need JS functionality) -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
