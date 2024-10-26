<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Software</title>
    <style>
        /* Container styling */
        .form-container {
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f9f9f9;
            font-family: Arial, sans-serif;
        }

        /* Title styling */
        .form-container h2 {
            text-align: center;
            color: #333;
        }

        /* Input styling */
        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        /* Checkbox label styling */
        .checkbox-group {
            margin-top: 15px;
            color: #555;
        }

        .checkbox-group label {
            margin-right: 15px;
            font-weight: bold;
        }

        /* Button styling */
        button {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Create Software</h2>
    <form action="createSoftware" method="post">
        <input type="text" name="name" placeholder="Software Name" required/>
        <textarea name="description" rows="4" placeholder="Description"></textarea>
        
        <div class="checkbox-group">
            <label><input type="checkbox" name="accessLevels" value="Read"> Read</label>
            <label><input type="checkbox" name="accessLevels" value="Write"> Write</label>
        </div>
        
        <button type="submit">Create Software</button>
    </form>
</div>

</body>
</html>
