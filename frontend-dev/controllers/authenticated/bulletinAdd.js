'use strict'

module.exports = function($scope, $timeout) {

  this.init = function() {
    // if(!db.user)
    //   return $scope.redirectToUrl('/403')

    this.types = [
      "Аренда",
      "Продажа",
      "Отдам даром",
      "Обмен"
    ]
    this.files = []
  }

  this.showFileUpload = function() {
    $timeout( function() {
      document.getElementById('uploadFile').click()
    }, 100)
  }
}
