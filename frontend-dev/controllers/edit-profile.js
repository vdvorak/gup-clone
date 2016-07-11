"use strict";

class ProfileContact{
    constructor() {
        this.contactEmails = [];
        this.contactPhones = [];
    }
}

class profileCtrl {
    constructor(){
        this.contact = new ProfileContact();
    }
    updateProfile(){

    }
    addContact(array){
        if(array.length < 5) array.push('');
    }
    deleteContact(array, index){
        array.splice(index,1);
    }
}

module.exports = profileCtrl;
