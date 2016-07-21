'use strict'

const TEMPLATE_RENDER_DELAY = 500,
      ANIMATION_DELAY = 900

module.exports = function($scope, $timeout) {
  let cover = document.getElementsByClassName('cover')[0]
  let content = document.getElementsByClassName('general-content')[0]

  let display = true

  $scope.$on('$routeChangeSuccess', function() {
    if( display ) {
      $timeout(function() {
        content.style.display = ""
        cover.classList.add('hide')
        $timeout(function() {
          cover.style.display="none"
        }.bind(this), ANIMATION_DELAY)
      }.bind(this), TEMPLATE_RENDER_DELAY)

      display = false
    }
  }.bind(this))
}
