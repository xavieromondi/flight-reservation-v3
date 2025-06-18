<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register User</title>
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
        input[type="text"],
        input[type="password"] {
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
    <h2>User Registration</h2>
    <form action="registerUser" method="post">
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="text" class="form-control" id="email" name="email" required>
        </div>
        <div class="mb-3">
            <label for="firstName" class="form-label">First Name:</label>
            <input type="text" class="form-control" id="firstName" name="firstName" required>
        </div>
        <div class="mb-3">
            <label for="lastName" class="form-label">Last Name:</label>
            <input type="text" class="form-control" id="lastName" name="lastName" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>
<!-- Bootstrap JS (Optional if you need JS functionality) -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
