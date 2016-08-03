"use strict";

let _$timeout;
    
class ProfileContact {
    constructor(user) {
        this.contactEmails = [].push(user.emailContact);
        this.emailGeneral = user.emailGeneral;
        this.contactPhones = [].push(user.phoneContact);
        this.type ="INDIVIDUAL";

        this.position = user.position;
        this.companyName = user.workplace;
        this.skypeUserName = user.skypeName;
        this.socNetLink = {};
        this.linkToWebSite = user.website;
        this.aboutUs = "";
    }
}

class Profile {
    constructor(user){
        
        this.mainPhoneNumber = user.phoneGeneral;
        this.imgId = user.avatar;
        this.contact = new ProfileContact(user);
    }
}

class profileCtrl {
    constructor($scope, $timeout){
//        if(!$scope.$parent.db.user)
//          $scope.$parent.redirectToUrl('/403', true)
//        else
        
        _$timeout = $timeout;
        
        this.user = $scope.$parent.db.user;
        this.contact = new ProfileContact(this.user);
        
        // удалить потом
        this.user.type = this.user.type || "INDIVIDUAL";
        this.username = "";
        this.user.aboutUs = this.user.aboutUs || "";
        //
        
        this.resultImageURI = 'url(../images/avatar.jpg)';
        this.originalImage = '';
        this.croppedImage = '';
        
        this.contactTypes = [
            'Физическое лицо',
            'Частный предприниматель',
            'Юридическое лицо'
        ];
            
        this.type = "Физическое лицо";
        
        $scope.$watch(
            () => this.type,
            ( newValue, oldValue ) => {
                if(this.user) this.user.type = (newValue === 'Юридическое лицо') ? "LEGAL_ENTITY" 
                : (newValue === 'Частный предприниматель') ? "ENTREPRENEUR"
                : "INDIVIDUAL";
            }
        );
        
    }
    
    fileUpload() {
        _$timeout(function(){
            document.getElementById('uploadInput').click()
        })
    }
    
    changeAvatar(files){
        var reader = new FileReader();
        
        reader.onload = (evt) => {
            
            _$timeout(() => {
              this.originalImage = evt.target.result;
            });
            
            angular.element(document.querySelector('.edit-profile-form-foto>p')).text(files[0].name)
          
        };
        
        reader.readAsDataURL(files[0]);
    }
    
    cropImage(){
        this.resultImageURI = this.croppedImage;
    }
    
    cancelCrop(){
        this.image = '';
        this.croppedImage = '';
    }
    
    updateProfile($event){

    }
}

profileCtrl.$inject = ['$scope', '$timeout'];  

module.exports = profileCtrl;

