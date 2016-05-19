<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="ad">
    <p class="proj-p" onClick='location.href="/project/list"'>ТОП ПРОЕКТОВ</p>

    <div id="topProjectsBlock">
        <a href="#" class="project-item-wrapper" style="display: none;">
            <div class="proj-top1">
                <span class="ad-a1"></span>
            </div>
        </a>
        <%--заполняется из бызы--%>
    </div>
    <img class="caretDown" id="proj-caret" src="/resources/images/caret.png" alt="caret">
</div>