var investorPostFO = {skip: 0, limit: 10, searchField: getUrlParam('name')};

appendInvestorPosts(investorPostFO);

function appendInvestorPosts(investorPostFO) {
    loadInvestorPostsWithFO(investorPostFO).statusCode({
        200: function (responseEntity) {
            responseEntity.entities.forEach(function (investorPost) {

                var score = checkInvestorBalance(investorPost.uId);
                var balance;
                $.when(score).done(function (response) {
                    balance = response;
                    appendInvestorPostBlock(investorPost, balance);
                }).fail(function (response) {
                    balance = 0;
                    appendInvestorPostBlock(investorPost, balance);
                });
            });
        }
    });
}

function appendInvestorPostBlock(investorPost, balance) {

    loadProfileById(investorPost.uId).statusCode({

        200: function (profile) {
            var categoriesOfIndustryUlList = '';

            if(investorPost.categoriesOfIndustry) {
                investorPost.categoriesOfIndustry.forEach(function (categorieOfIndustry) {
                    categoriesOfIndustryUlList += '<li><a>' + categorieOfIndustry + '</a></li>';
                });
            }

            $('#investorPostsBlock').append(
                '<div class="feedItem">' +
                    <!--Add class "vip" to vip-tialize investment-->
                '<div class="publishDate">Опубликовано: ' + getReadableDate(investorPost.createdDate) + '</div>' +
                '<div class="content">' +
                '<div class="desc">Описание инвестиции</div>' +
                '<p class="text">' + investorPost.description + '</p>' +
                '</div>' +
                '<div class="cats">' +
                    <!--Recommended max 14 elements-->
                '<ul>' +
                    <!--Emmet shortcut-->
                    <!--li*14>a[href="#"]>{IT Ресурсы}-->
                categoriesOfIndustryUlList +
                '</ul>' +
                '</div>' +

                '<div class="details">' +
                '<a href="/profile?id=' + profile.id + '">' +
                '<div class="title"> Инвестор: ' + getProfileName(profile) + '</div>' +
                '</a>' +
                '<div class="alreadyInvested">' +
                '<div class="desc"> Баланс инвестора:  <div class="total">' + balance + ' ₴ </div></div>' +
                '<div class="desc">Сумма инвестирования:<br>от  <div class="total">' + investorPost.minInvestAmount + ' ₴ </div> до  ' +
                '<div class="total">' + investorPost.maxInvestAmount + ' ₴ </div> </div>' +
                '</div>' +
                '<button type="button" class="abutton blue investApp">Нужна инвестиция</button>' +
                '</div>' +
                '</div>');
        }
    });
}

$(".investApp").click(function () {
    alert("!!! Нужно окно для выбора проекта !!!");
});

function getProfileName(profile) {
    if (profile.contact.type === 'LEGAL_ENTITY' ) {
        return profile.contact.companyName;
    } else if (profile.username) {
        return  profile.username;
    } else {
        return 'Имя не указано';
    }
}