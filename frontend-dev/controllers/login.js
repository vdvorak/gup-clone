'use strict'

module.exports = function($scope) {

  this.init = () => {
    this.db = $scope.$parent.db

    this.email = ""
    this.password = ""

    this.emailValid = true
    this.passwordValid = true

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
    ee.emit({ name : "form-submit" })

    if( this.emailValid && this.passwordValid ) {
      this.db.login({
        "email" : this.email,
        "password": this.password
      }, (err, data) => {
        if(err)
          $scope.$parent.displayError("Ошибка авторизации, проверьте ваши данные")
        else {
          this.deleteListners()
          this.db.saveUserData(data)
          $scope.redirectToUrl('/profile')
        }
      })
    }
  }

  this.emailIsValid = email => {
    let error = ""
    if(!email.length)  error += "Обязательное поле. "
    if(!/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email))
      error += "Неверный формат почты. "
    return error
  }

  this.passwordIsValid = pwd => {
    let error = ""
    if(!pwd.length) error += "Обязательное поле. "
    if( pwd.length < 6) error += "Пароль должен содержать не менее 6 символов. "
    return error
  }
}
