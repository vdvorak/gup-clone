<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="question questionFormToggle">
    <img class="question-img questionFormToggle" src="/resources/images/question.png" alt="question">
    <div class="questionForm questionFormToggle">
        <p class="quest-p questionFormToggle">Оставьте свой вопрос</p>
        <form id="form-quest" action="#" role="form" class="questionFormToggle">
            <%--<input type="text" class="form-control inText" name="name" placeholder="Ваше имя">--%>
            <textarea class="form-control questionFormToggle" rows="7" id="comment-quest" placeholder="Ваш вопрос"></textarea>
            <button type="submit" class="btn-quest">Отправить</button>
        </form>
    </div>
</div>