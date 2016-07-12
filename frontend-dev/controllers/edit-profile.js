"use strict";

class ProfileContact{
    constructor() {
        this.contactEmails = [''];
        this.contactPhones = [''];
    }
}

class profileCtrl {
    constructor(){
        this.contact = new ProfileContact();
    }
    updateProfile(){

    }
    addContacts($event, $index){
        var arr;
        if(type === 'email') arr = this.contactEmails;
        else if(type === 'phone') arr = this.contactPhones;
        else return;

        if(arr.length < 5 && arr[$index].trim()) arr.push('');
    }

    deleteContacts($event, $index){
        var type = angular.element($event.target).parent().data('contact');

        if(type === 'email') this.contactEmails.splice($index, 1);
        else if(type === 'phone') this.contactPhones.splice($index, 1);
        else return;
    }
}

module.exports = profileCtrl;
