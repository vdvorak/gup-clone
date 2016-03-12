<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="question">
    <img class="question-img" src="/resources/images/question.png" alt="question">
    <div class="questionForm">
        <p class="quest-p">Оставьте свой вопрос</p>
        <form id="form-quest" action="#" role="form">
            <input type="text" class="form-control inText" name="name" placeholder="Ваше имя">
            <textarea class="form-control" rows="7" id="comment-quest"></textarea>
            <button type="submit" class="btn-quest">Отправить</button>
        </form>
    </div>
</div>