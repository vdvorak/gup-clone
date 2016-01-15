<%--
  Created by IntelliJ IDEA.
  User: Sasha
  Date: 14.01.2016
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Создание публикации инвестора</title>
</head>
    <body>
        <div>
            <h2 align="center">Создание публикации инвестора</h2>
        </div>

        <div>
            <label for="investorPostDescription"><b>Описание: </b></label>
            <textarea id="investorPostDescription"
                      placeholder="Минимум 50 символов, максимум 5000." required></textarea>
        </div>

        <div>
            <label for="amountOfMoney"><b>Нужная сумма: </b></label>
            <input id="amountOfMoney" type="number" min="1"
                   placeholder="Минимум 1" required>
        </div>

        <div>
            <label for="categoriesOfIndustry"><b>Категории индустрии: </b></label>
            <select multiple="multiple" size="4" name="categoriesOfIndustry" id="categoriesOfIndustry" required>
                <option value="cat1">Категория 1</option>
                <option value="cat2">Категория 2</option>
                <option value="cat3">Категория 3</option>
                <option value="cat4">Категория 4</option>
            </select>
        </div>

        <button id="createInvestorPost">Создать</button>

        <script src="/resources/libs/jquery-1.11.3.min.js"></script>
        <script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
        <script>

            var investorPost = {};

            $(document).on('click', '#createInvestorPost', function (event) {

                investorPost.investorPostDescription = $('#investorPostDescription').val();
                investorPost.amountOfMoney = $('#amountOfMoney').val();
                investorPost.categoriesOfIndustry = $('#categoriesOfIndustry').val();

                $.ajax({
                    type: "POST",
                    url: "/api/rest/projectsAndInvestmentsService/investorPost/create",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    data: JSON.stringify(investorPost),
                    success: function (createdInvestorPostId) {
                        window.location.href = '/investorPost/id/' + createdInvestorPostId.id;
                    },
                    error: function (response) {
                        alert("Внутренняя ошибка сервера");
                    }
                });
            });
        </script>
    </body>
</html>

