'use strict'

module.exports = function($scope) {

  this.init = () => {
    this.db = $scope.$parent.db

    this.email = ""
    this.password = ""

    this.emailError = ""
    this.passwordError = ""

    this.loginError = ""

    this.handler = function(e) {
      if(e.which == 13) this.send.call(this)
    }.bind(this)

    document.addEventListener('keyup', this.handler)
  }

  this.deleteListners = () => {
    document.removeEventListener('keyup', this.handler)
  }

  this.send = () => {
    /*
      - Get data
      - Validate
      - Show errors
      - or
      - Goto bd and send data
    */
    // this.deleteListners()

    if( this.isValid() ) {
      this.db.login({
        "email" : this.email,
        "password": this.password
      }, (err, data) => {
        if(err) {
          this.loginError = "Ошибка авторизации, проверьте ваши данные"
          console.error("Bad login/password")
        } else {
          this.deleteListners()
          /* Save data to db */
          $scope.redirectToUrl('/profile')
          console.log(data)
        }
      })
    }
  }

  this.isValid = () => {
    if(!this.email.length) {
      this.emailError = "Обязательное поле"
      this.email = ""
    }else if(!/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.email)) {
      this.emailError = "Неверный формат почты"
      this.email = ""
    } else {
      this.emailError = ""
    }

    if (!this.password.length) {
      this.passwordError = "Обязательное поле"
      this.password = ""
    } else if(this.password.length < 6) {
      this.passwordError = "Пароль должен содержать не менее 6 символов"
      this.password = ""
    } else {
      this.passwordError = ""
    }

    $scope.$apply()
    return !this.emailError.length && !this.passwordError.length
  }
}
