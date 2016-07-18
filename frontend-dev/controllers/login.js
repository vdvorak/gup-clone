'use strict'

module.exports = function($scope) {

  this.init = () => {
    this.username = ""
    this.password = ""

    this.emailError = ""
    this.passwordError = ""

    this.loginError = ""
  }

  this.send = () => {
    /*
      - Get data
      - Validate
      - Show errors
      - or
      - Goto bd and send data
    */

    if( this.isValid() ) {
      $scope.bd.checkEmail(email, (err, data) => {
          if(err) {
            this.emailError = "Почта уже используется"
            console.error("bad email")
          } else $scope.bd.login({
            "email" : email,
            "password": password
          }, (err, data) => {
            if(err) {
              this.loginError = "Ошибка авторизации, проверьте ваши данные"
              console.error("Bad login/password")
            } else console.log(data)
          })
      })

    }
  }

  this.isValid = () => {
    if(!mail.length) {
      this.emailError = "Обязательное поле"
      this.email = ""
    }else if(!/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email)) {
      this.emailError = "Неверный формат почты"
      this.email = ""
    }

    if (!password.length) {
      this.passwordError = "Обязательное поле"
      this.password = ""
    } else if(password.length < 6) {
      this.passwordError = "Пароль должен содержать не менее 6 символов"
      this.password = ""
    }

     return !this.mailError.length && !this.passwordError.length
  }
}
