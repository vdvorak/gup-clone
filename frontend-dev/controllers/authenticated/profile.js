"use strict";

class ProfileContact{
    constructor() {
        this.contactEmails = ['']
        this.contactPhones = ['']
    }
}

class profileCtrl {
    constructor($scope){
        // if(!$scope.$parent.db.user)
        //   $scope.$parent.redirectToUrl('/403', true)
        // else
        this.contact = new ProfileContact();

        this.socialCategories = require('../../data/social')
		
		this.profileOrHistory = true;
		
		this.deeper = false;
		
		this.showProfile = ["История покупок", "История отзывов", "Запросы на отзывов"];
		
		this.selectedOption = "Показать";
    }
	
	showTitle() {
		if (this.selectedOption == "Показать") {
			return "Профиль";
		} else if (this.selectedOption == "История покупок") {
			return "История покупок";
		} else if (this.selectedOption == "История отзывов") {
			return "История отзывов"
		} else {
			return "Запросы на отзывов";
		}
	}
	
    updateProfile(){

    }
    addContacts($event, type){
        var arr;
        if(type === 'email') arr = this.contact.contactEmails;
        else if(type === 'phone') arr = this.contact.contactPhones;
        else return;

        if(arr.length < 5 && arr[arr.length - 1].trim()) arr.push('');
    }

    deleteContacts($event, $index, type){
        if(type === 'email') this.contact.contactEmails.splice($index, 1);
        else if(type === 'phone') this.contact.contactPhones.splice($index, 1);
        else return;
    }
}

module.exports = profileCtrl;
