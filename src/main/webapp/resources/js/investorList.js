var investorPostFO = {skip: 0, limit: 10};

appendInvestorPosts(investorPostFO);


function appendInvestorPosts(investorPostFO) {
    loadInvestorPostsWithFO(investorPostFO).statusCode({
        200: function (responseEntity) {
            responseEntity.entities.forEach(function (investorPost) {
                appendInvestorPostBlock(investorPost);
            });
        }
    });
}

function appendInvestorPostBlock(investorPost) {
    loadProfileById(investorPost.uId).statusCode({
        200: function (profile) {
            var categoriesOfIndustryUlList = '';
            investorPost.categoriesOfIndustry.forEach(function (categorieOfIndustry) {
                categoriesOfIndustryUlList += '<li><a>' + categorieOfIndustry + '</a></li>';
            });
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
                        '<a href="/profile/id/' + profile.id + '">' +
                            '<div class="title"> Инвестор: ' + profile.username + '</div>' +
                        '</a>' +
                        '<div class="alreadyInvested">' +
                            '<div class="desc"> Баланс инвестора:  <div class="total">' + profile.contact.balance + ' ₴ </div></div>' +
                            '<div class="desc">Сумма инвестирования:  от <div class="total">' + investorPost.minInvestAmount + ' ₴ </div> до  ' +
                                                                        '<div class="total">' + investorPost.maxInvestAmount + ' ₴ </div> </div>' +
                        '</div>' +
                        '<button type="button" class="abutton blue investApp">Нужна инвестиция</button>' +
                    '</div>' +
                '</div>');
        }
    });
}

$(".investApp").click(function(){
    alert("!!! Нужно окно для выбора проекта !!!");
});