<%@tag pageEncoding="UTF-8"%>
<%@attribute name="css" fragment="true" required="false" %>
<%@attribute name="js" fragment="true" required="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ProjectFinal_Newspaper</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body{
            scroll-behavior: auto;

        }
        div {
            border-radius: 10px;
        }

        img {
            border-radius: 10px;
        }

        a{
            text-decoration: none;
            color: black;
        }

        a:hover{
            text-decoration: none;
            color: cornflowerblue;
        }

        .form-control:focus {
            border-color: transparent;
            -webkit-box-shadow: none;
            box-shadow: none;
            background-color: #EEEEEE;
        }

        .crop {
            width: 1060px;
            height: 400px;
        }

        .crop img {
            width: 100%;
            height: 100%;
        }

        .grad {
            background-image: linear-gradient(to top, black 5%, transparent, transparent);
        }

        .bgColorGray{
            background-color: #EEEEEE;
        }

        .imageIcon{
            width: 40px;
            height: 40px;
            border-radius: 40px
        }


        .dropbtn {
            border: none;
        }

        dropbtn:hover {
            background-color: darkgrey;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 4;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        .dropdown-content a:hover {background-color: darkgrey;}
        .dropdown:hover .dropdown-content {display: block;}

    </style>
    <jsp:invoke fragment="css" />
</head>
<body>
<jsp:include page="../../views/vwPartial/Navigation.jsp"/>
<jsp:doBody/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
<jsp:invoke fragment="js" />
</body>
</html>