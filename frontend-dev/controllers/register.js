'use strict'

module.exports = function($scope, $q) {

  this.init = () => {
    this.db = $scope.$parent.db

    this.email = ""
    this.password = ""
    this.password2 = ""

    this.emailValid = ""
    this.passwordValid = ""
    this.password2Valid = ""

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
    if( this.emailValid && this.passwordValid && this.password2Valid ) {
      this.db.login({
        "email" : this.email,
        "password": this.password
      }, (err, data) => {
        this.deleteListners()
        if(err)
          $scope.$parent.redirectToUrl('/500')
        else {
          /* Save data to db */
          $scope.redirectToUrl('/profile')
          console.log(data)
        }
      })
    }
  }

  this.emailIsValid = function(email) {
    return $q( function(resolve, reject) {
      let error = ""
      if(!email.length)  error += "Обязательное поле. "
      if(!/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email))
        error += "Неверный формат почты. "

      window.db.checkEmail(email, function(err, data) {
        if(err) reject(err)
        else {
          console.log(data)
          if(data !== "false")
            error += "Такая почта уже используется. "
          resolve(error)
        }
      }.bind(this))
    }.bind(this))
  }

  this.passwordIsValid = pwd => {
    let error = ""
    if(!pwd.length) error += "Обязательное поле. "
    if(pwd.length < 6) error += "Пароль должен содержать не менее 6 символов. "
    return error
  }

  this.password2IsValid = pwd => {
    let error = this.passwordIsValid(pwd)
    if(this.password !== this.password2 ) error += "Пароли не совпадают"
    return error
  }
}