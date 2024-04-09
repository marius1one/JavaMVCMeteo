<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        input[type="submit"] {
            border: 1px solid whitesmoke;
            background-color: transparent;
            color: whitesmoke;
            font-size: 18px;
            font-weight: 500;
            background: transparent;
            cursor: pointer;
            display: inline-block;
            margin-right: 15px;
            margin-bottom: 30px;
            border-radius: 3px;
            text-transform: capitalize;
        }

        input:focus {
            outline: none;
        }

        input.hover-center-1 {
            background-image: linear-gradient(0, whitesmoke, whitesmoke);
            background-size: 0;
            transition: .8s;
            background-repeat: no-repeat;
            background-position: bottom;
            transition-timing-function: cubic-bezier(0.52, 1.64, 0.37, 0.66);
        }

        input.hover-center-1:hover {
            background-size: 100%;
            color: dimgray;
        }

        body {

            background-image: url("https://img.freepik.com/free-photo/3d-background-with-white-cubes_23-2150473010.jpg");
            background-repeat: no-repeat;
            background-size: cover;
        }

        div {
            height: 200px;
            margin-left: 35%;
            width: 30%;
            margin-top: 200px;
            border: 1px solid grey;
            border-radius: 20px;
            padding: 30px;
            background: rgba(73, 73, 73, 0.27);

            box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
            backdrop-filter: blur(10.1px);
            -webkit-backdrop-filter: blur(10.1px);
        }

        table {
            border-collapse: separate;
            border-spacing: 0 15px;

            color: whitesmoke;
        }

        table, input {
            width: 100%;
        }


        .klaida {
            background-color: red;
            color: whitesmoke;
            padding: 15px;
        }
    </style>
    <title>JSP - Hello World</title>
</head>
<body>
<div>
    <form action="prisijungimas" method="get">
        <table>

            <%
                String ar_klaida = request.getParameter("klaida");
                if (ar_klaida != null) {
                    if (ar_klaida.equals("taip")) {
            %>
            <tr>
                <td colspan="2" class="klaida">
                    Neteisigni prisijungimo duomehys
                </td>
            </tr>
            <%
                    }
                }
            %>

            <tr>
                <td>Vartotojo vardas</td>
                <td><input type="text" name="user_name"/></td>
            </tr>
            <tr>
                <td>Slaptazodis</td>
                <td><input type="password" name="user_psw"/></td>
            </tr>
            <tr>
                <td><input class="hover-center-1" style="margin-top:15px; margin-left: 85%" type="submit"
                           name="veiksmas" value="Prisijungti"/></td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>