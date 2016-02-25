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
            $('#investorPostsBlock').append(
                '<div class="feedItem vip">' +
                    <!--Add class "vip" to vip-tialize investment-->
                    '<div class="publishDate">Опубликовано: ' + getReadableDate(investorPost.createdDate) + '</div>' +
                    '<div class="photo border-color">' +
                        '<img src="' + getProfileImgUrl(profile.contact.pic) + '" alt="user avatar"/>' +
                    '</div>' +
                    '<a href="#" class="content">' +
                        '<div class="title">' + profile.username + '</div>' +
                        '<div class="desc">Описание</div>' +
                        '<p class="text">' + investorPost.description + '</p>' +
                    '</a>' +
                    '<div class="cats">' +
                        <!--Recommended max 14 elements-->
                        '<ul>' +
                            <!--Emmet shortcut-->
                            <!--li*14>a[href="#"]>{IT Ресурсы}-->
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                            '<li><a href="#">IT Ресурсы</a></li>' +
                        '</ul>' +
                    '</div>' +

                    '<div class="details">' +
                        '<div class="canInvest">' + profile.contact.balance + ' ₴ </div>' +
                        '<div class="alreadyInvested">' +
                            '<div class="desc">Проинвестировано:</div>' +
                            '<div class="total">15468900$</div>' +
                            '<div class="totalProjects">25 проектов</div>' +
                        '</div>' +
                    '</div>' +
            '</div>');
        }
    });
}