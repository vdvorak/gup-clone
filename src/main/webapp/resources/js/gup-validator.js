/**
 * Created by Юля on 05.05.2016.
 */
(function (namespace) {

    'use strict';

    // {maxLength, minLength, min, max, regExp, }

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
                text: {minLength: 500, maxLength: 10000},
                categories: {minLength: 1}
            },
            msg: {
                imagesIds: 'Количество изображений не должно превышать 15 штук.',
                title: 'Проверьте заполнение поля Заголовок. Длина заголовка должна быть не менее 5 и не более 70 символов.',
                text: 'Проверьте заполнение поля Описание. Длина описания должна быть не менее 500 и не более 10000 символов.',
                categories: 'Выберите хотя бы 1 категорию для новости.'
            }

        },
        {
            name: 'project',
            rules: {
                imagesIds: {maxLength: 15},
                title: {minLength: 4, maxLength: 70},
                description: {minLength: 50, maxLength: 5000},
                categoriesOfIndustry: {minLength: 1},
                amountRequested: {min: 1, max: 2147483648},
                type: {minLength: 1}
            },
            msg: {
                imagesIds: 'Количество изображений не должно превышать 15 штук.',
                title: 'Проверьте заполнение поля Заголовок. Длина заголовка должна быть не менее 5 и не более 70 символов.',
                description: 'Проверьте заполнение поля Описание. Длина описания должна быть не менее 50 и не более 5000 символов.',
                categoriesOfIndustry: 'Проверьте заполнение поля Категории индустрии. Необходимо выбрать хотя бы 1 значение.',
                amountRequested: 'Проверьте заполнение поля Нужная сумма. Сумма не должна превышать значение 2 147 483 648 грн.',
                type: 'Выберите тип проекта.'
            }
        },
        {
            name: 'investorPost',
            rules: {
                description: {minLength: 50, maxLength: 5000},
                categoriesOfIndustry: {minLength: 1},
                minInvestAmount: {min: 1, max: 2147483648},
                maxInvestAmount: {min: 1, max: 2147483648}
            },
            msg: {
                description: 'Проверьте заполнение поля Описание. Длина описания должна быть не менее 50 и не более 5000 символов.',
                categoriesOfIndustry: 'Проверьте заполнение поля Отрасли. Необходимо выбрать хотя бы 1 отрасль.',
                minInvestAmount: 'Заполните поле Минимальная сумма. Сумма не должна превышать значение 2 147 483 648 грн.',
                maxInvestAmount: 'Заполните поле  Максимальная сумма. Сумма не должна превышать значение 2 147 483 648 грн.'
            }
        },
        {
            name: 'doer',
            rules: {
                title: {minLength: 5, maxLength: 70},
                body: {minLength: 50, maxLength: 4000},
                naceIds: {minLength: 1}
            },
            msg: {
                title: 'Проверьте заполнение поля Заголовок. Длина заголовка должна быть не менее 2 и не более 70 символов.',
                body: 'Проверьте заполнение поля Описание. Длина описания должна быть не менее 50 и не более 5000 символов.',
                naceIds: 'Проверьте заполнение поля Отрасли. Необходимо выбрать хотя бы 1 отрасль.'
            }
        }
    ];

    function GupValidator(strategy) {

        this.modal = document.getElementById('gup-validator-popup');
        this.messages = [];
        this.strategy = {};
        this.isValid = true;

        for (var i = 0; i < arrStrategies.length; i++) {
            if (arrStrategies[i].name === strategy) this.strategy = arrStrategies[i];
        }

    }

    GupValidator.prototype.checkMatchRules = function (prop, rules, msg) {
        for(var key in rules) {
            if((key === 'maxLength' && (prop === undefined || prop === null || prop.length > rules[key]))
            || (key === 'minLength' && (prop === undefined || prop === null || prop.length < rules[key]))
            || (key === 'min' && (prop === undefined || prop === null || prop < rules[key]))
            || (key === 'max' && (prop === undefined || prop === null || prop > rules[key]))
            || (key === 'regExp' && (prop === undefined || prop === null || !rules[key].test(prop))))  {
                this.messages.push(msg);
                break;
            }
        }
        return this;
    }

    GupValidator.prototype.validate = function (obj) {

        this.reconstruct();

        var rules = this.strategy.rules,
            msg = this.strategy.msg;

        this.validateRecursively(obj, rules, msg)
            .validateAdditionalRules(obj);

        if(this.messages.length) {
            this.isValid = false;
            this.drawMessages();
        }

        return this;
    }

    GupValidator.prototype.validateAdditionalRules = function(obj) {
        var strategy = this.strategy.name;
        if(strategy === 'offer') {
            this.validateOfferAdditionalFields();
        } else if(strategy === 'investorPost') {
            this.validateInvestorPostAdditionalFields(obj);
        } else if(strategy === 'tender') {
            this.validateTenderAdditionalFields(obj);
        }

        return this;
    }

    GupValidator.prototype.validateTenderAdditionalFields = function(obj) {
        if (obj.begin && obj.end && obj.begin > obj.end) this.messages.push('Дата начала не должна превышать дату окончания тендера.');
        return this;
    }

    GupValidator.prototype.validateInvestorPostAdditionalFields = function(obj) {
        if (obj.minInvestAmount > obj.maxInvestAmount) this.messages.push('Максимальная сумма должна быть больше минимальной.');
        return this;
    }

    GupValidator.prototype.validateOfferAdditionalFields = function() {

        // validate price
        var inp = document.getElementById('offer-inpPrice'),
            price = +inp.value;

        if ((document.getElementById('offer-price-row').style.display !== 'none' && document.getElementById('selection-price').value === 'price')
            && (price <= 0 || price > 2147483648)) {
            this.messages.push('Проверьте заполнение поля Цена. Сумма не должна превышать значение 2 147 483 648 грн.');
        }

        // validate region
        var region = document.getElementById('text-region').textContent;
        var city = document.getElementById('text-city').textContent;
        if (region === 'Выберите область' || (region !== 'Вся Украина' && city === 'Выберите город')) {
            this.messages.push('Проверьте заполнение поля Регион.');
        }

        // validate categories
        var blocks = document.querySelectorAll('#categories-row div');
        for(var i = 0; i < blocks.length; i++) {
            if(blocks[i].style.display !== 'none') {
                var content = blocks[i].querySelector('[id^="text-category"]').textContent;
                if(content === 'Выберите категорию' || content === 'Выберите подкатегорию') {
                    this.messages.push('Проверьте заполнение поля Категории.');
                    break;
                }
            }
        }
    }

    GupValidator.prototype.validateRecursively = function (obj, rules, msg) {
        if(Object.prototype.toString.call(obj) === '[object Object]') {
            for (var key in rules) {
                if (key in obj) {
                    this.checkMatchRules(obj[key], rules[key], msg[key])
                        .validateRecursively(obj[key], rules[key], msg[key]);
                }
            }
        }
        return this;
    }

    GupValidator.prototype.reconstruct = function () {
        this.isValid = true;
        this.messages = [];

        return this;
    }

    GupValidator.prototype.drawMessages = function () {
        var modalBody = this.modal.querySelector('.popup-content');
        modalBody.innerHTML = '';

        for(var i = 0; i < this.messages.length; i++) {
          modalBody.innerHTML += '<p>'+ this.messages[i] +'</p>';
        }

        this.modal.classList.add("gup-popup-active");

        return this;
    }

    GupValidator.prototype.init = function () {
        var modal = this.modal;

        window.addEventListener('click', function (event) {
            if (event.target == modal) {
                modal.classList.remove("gup-popup-active");
            }
        }, false);

        modal.querySelector('.popup-close').addEventListener('click', function(event) {
            event.preventDefault();
            modal.classList.remove("gup-popup-active");
        }, false);

        return this;

    }

    namespace.Constructor = GupValidator;

})(window.GupValidator = window.GupValidator || {});


