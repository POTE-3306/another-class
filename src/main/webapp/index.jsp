<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap CSS Example</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        /* Navigation bar */
        .navbar {
            background-color: orange; /* Set the background color to blue */
            padding: 10px;
        }

        .navbar .nav-item {
            margin-right: 10px;
        }

        /* Center the search bar */
        .search-form {
            display: flex;
            justify-content: center;
            margin-top: 10px;
        }

        .search-form input[type="text"] {
            width: 300px;
            padding: 5px;
        }

        /* My page section */
        .my-page {
            margin-top: 30px;
            padding: 20px;
            background-color: #f8f9fa;
            display: none;
        }

        .my-page h2 {
            margin-bottom: 10px;
        }

        .my-page p {
            margin-bottom: 10px;
        }

        /* Profile picture */
        .profile-picture {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            cursor: pointer;
        }
    </style>
</head>
<body>
<!-- Navigation bar -->
<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand" href="#">Logo</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <a class="nav-link" href="#">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Contact</a>
        </li>
        <li class="nav-item">
            <img src="profile_picture.jpg" class="profile-picture" alt="Profile Picture">
            <span id="myPageLink">My Page</span>
        </li>
    </ul>
</nav>

<!-- Search bar -->
<form class="search-form">
    <input type="text" placeholder="Search">
    <button type="submit">Search</button>
</form>

<!-- My page section -->
<section class="my-page" id="myPageSection">
    <h2>My Page</h2>
    <p>Welcome to my page! Here you can find my latest updates.</p>
    <button onclick="location.href='post_creation.html'">Create Post</button>
</section>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script>
    // Show/hide the My Page section when profile picture is clicked
    const myPageLink = document.getElementById('myPageLink');
    const myPageSection = document.getElementById('myPageSection');

    myPageLink.addEventListener('click', () => {
        if (myPageSection.style.display === 'none') {
            myPageSection.style.display = 'block';
        } else {
            myPageSection.style.display = 'none';
        }
    });
</script>
</body>
</html>
