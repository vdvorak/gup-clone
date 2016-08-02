"use strict";

let _$timeout;
    
class ProfileContact {
    constructor() {
        this.contactEmails = [''];
        this.contactPhones = [''];
        this.type ="ENTREPRENEUR"

        this.position = ""
        this.companyName = ""
        this.skypeUserName = ""
        this.socNetLink = {}
        this.linkToWebSite = ""
        this.aboutUs = ""
    }
}

class profileCtrl {
    constructor($scope, $timeout){
//        if(!$scope.$parent.db.user)
//          $scope.$parent.redirectToUrl('/403', true)
//        else
        
        _$timeout = $timeout;
        
        this.contactTypes = [
        "LEGAL_ENTITY",
        "ENTREPRENEUR",
        "INDIVIDUAL"
      ]
        this.contact = new ProfileContact();
        this.user = $scope.$parent.db.user;
        
        this.user.type = this.user.type || "INDIVIDUAL";
        this.user.aboutUs = this.user.aboutUs || "";
        
    }
    
    fileUpload() {
        _$timeout(function(){
            document.getElementById('profile-upload-photo-inp').click()
        })
    }
    
    changeAvatar(files){
        angular.element(document.querySelector('.edit-profile-form-foto>p')).text(files[0].name);
    }
    
    updateProfile($event){

    }
}

profileCtrl.$inject = ['$scope', '$timeout'];  

module.exports = profileCtrl;

