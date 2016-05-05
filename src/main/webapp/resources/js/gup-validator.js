/**
 * Created by Юля on 05.05.2016.
 */
(function (namespace) {

    'use strict';

    // {maxLength, minLength, min, max, regExp}

    var arrStrategies = [
        {
            name: 'tender',
            rules: {
                files: {maxLength: 15},
                title: {minLength: 5, maxLength: 70},
                body: {minLength: 50, maxLength: 4000},
                naceIds: {minLength: 1},
                expectedPrice: {min: 0, max: 2147483648}
            },
            msg: {
                files: 'Количество изображений и документов не должно превышать 15 штук.',
                title: 'Проверьте заполнение поля Заголовок. Длина заголовка должна быть не менее 5 и не более 70 символов.',
                body: 'Проверьте заполнение поля Описание. Длина описания должна быть не менее 50 и не более 4000 символов.',
                naceIds: 'Проверьте заполнение поля Отрасли. Необходимо выбрать хотя бы 1 отрасль.',
                expectedPrice: 'Проверьте заполнение поля Ожидаемая стоимость. Стоимость не должна превышать значение 2 147 483 648 грн.'
            }
        },
        {
            name: 'offer',
            rules: {
                imagesIds: {maxLength: 15},
                title: {minLength: 5, maxLength: 70},
                description: {minLength: 50, maxLength: 4000},
                naceIds: {minLength: 1},
                userInfo: {
                    email: {regExp: /\S+@\S+\.\S+/},
                    contactName: {minLength: 1}
                }
            },
            msg: {
                imagesIds: 'Количество изображений не должно превышать 15 штук.',
                title: 'Проверьте заполнение поля Заголовок. Длина заголовка должна быть не менее 5 и не более 70 символов.',
                description: 'Проверьте заполнение поля Описание. Длина описания должна быть не менее 50 и не более 4000 символов.',
                naceIds: 'Проверьте заполнение поля Отрасли. Необходимо выбрать хотя бы 1 отрасль.',
                userInfo: {
                    email: 'Проверьте заполнение поля E-mail.',
                    contactName: 'Заполнените поле Контактное лицо.'
                }
            }
        },
        {
            name: 'blog',
            rules: {
                title: {minLength: 2, maxLength: 70},
                description: {minLength: 50, maxLength: 5000}
            },
            msg: {
                title: 'Проверьте заполнение поля Заголовок. Длина заголовка должна быть не менее 2 и не более 70 символов.',
                description: 'Проверьте заполнение поля Описание. Длина описания должна быть не менее 50 и не более 5000 символов.'
            }
        },
        {
            name: 'blog-post',
            rules: {
                imagesIds: {maxLength: 15},
                title: {minLength: 5, maxLength: 70},
                text: {minLength: 500, maxLength: 10000}
            },
            msg: {
                imagesIds: 'Количество изображений не должно превышать 15 штук.',
                title: 'Проверьте заполнение поля Заголовок. Длина заголовка должна быть не менее 5 и не более 70 символов.',
                text: 'Проверьте заполнение поля Описание. Длина описания должна быть не менее 500 и не более 10000 символов.'
            }

        },
        {
            name: 'project',
            rules: {
                imagesIds: {maxLength: 15},
                title: {minLength: 4, maxLength: 70},
                description: {minLength: 50, maxLength: 5000},
                categoriesOfIndustry: {minLength: 1},
                amountRequested: {min: 1, max: 2147483648}
            },
            msg: {
                imagesIds: 'Количество изображений не должно превышать 15 штук.',
                title: 'Проверьте заполнение поля Заголовок. Длина заголовка должна быть не менее 5 и не более 70 символов.',
                description: 'Проверьте заполнение поля Описание. Длина описания должна быть не менее 50 и не более 5000 символов.',
                categoriesOfIndustry: 'Проверьте заполнение поля Категории индустрии. Необходимо выбрать хотя бы 1 значение.',
                amountRequested: 'Проверьте заполнение поля Нужная сумма. Сумма не должна превышать значение 2 147 483 648 грн.'
            }
        }
    ];

    function GupValidator(strategy) {

        this.modal = document.getElementById('gup-validator-modal');
        this.messages = [];
        this.strategy = {};
        this.isValid = true;

        for (var i = 0; i < arrStrategies.length; i++) {
            if (arrStrategies[i].name === strategy) this.strategy = arrStrategies[i];
        }

    }

    GupValidator.prototype.checkMatchRules = function (prop, rules, msg) {
        for(var key in rules) {
            if((key === 'maxLength' && prop.length > rules[key])
            || (key === 'minLength' && prop.length < rules[key])
            || (key === 'min' && prop < rules[key])
            || (key === 'max' && prop > rules[key])
            || (key === 'regExp' && !rules[key].test(prop)))  {
                this.messages.push(msg);
                break;
            }
        }
        return this;
    }


    GupValidator.prototype.validate = function (obj, rules, msg) {

        this.reconstruct();

        var rules = rules || this.strategy.rules,
            msg = msg || this.strategy.msg;

        for(var key in rules) {
            if(rules[key] !== null && typeof rules[key] === 'object') {
                this.validate(obj[key], rules[key], msg[key]);
            } else {
                this.checkMatchRules(obj[key], rules[key], msg[key]);
            }
        }

        if(this.messages.length) {
            this.isValid = false;
            this.drawMessages();
        }

        return this;
    }

    GupValidator.prototype.reconstruct = function () {
        this.isValid = true;
        this.messages = [];

        return this;
    }

    GupValidator.prototype.drawMessages = function () {
        var modalBody = this.modal.querySelector('.cropper-modal-body');

        for(var i = 0; i < this.messages.length; i++) {
          modalBody.appendChild('<p>'+ this.messages[i] +'</p>') ;
        }

        return this;
    }

    GupValidator.prototype.init = function () {
        var modal = this.modal;

        window.addEventListener('click', function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }, false);

        document.getElementById('gup-validator-btn-ok').addEventListener('click', function(event) {
            event.stopPropagation();

            var modalBody = modal.querySelector('.cropper-modal-body');

            modal.style.display = "none";
            modalBody.innerHTML = '';

        }, false);

        return this;

    }

    namespace.Constructor = GupValidator;

})(window.GupValidator = GupValidator || {});

