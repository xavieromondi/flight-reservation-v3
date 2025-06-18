<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Find Flights</title>
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
            text-align: center;
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
    <h2>Find Flights</h2>
    <form action="findFlights" method="post">
        <div class="mb-3">
            <label for="from" class="form-label">From:</label>
            <input type="text" class="form-control" id="from" name="from" required>
        </div>
        <div class="mb-3">
            <label for="to" class="form-label">To:</label>
            <input type="text" class="form-control" id="to" name="to" required>
        </div>
        <div class="mb-3">
            <label for="departureDate" class="form-label">Departure Date:</label>
            <input type="text" class="form-control" id="departureDate" name="departureDate" placeholder="yyyy-dd-MM" required>
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
</div>
<!-- Bootstrap JS (Optional if you need JS functionality) -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
