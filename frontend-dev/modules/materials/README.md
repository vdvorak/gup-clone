# Предустановленные события
## text
'form-submit' - каждый элемент производит валидацию по событию


# Интегрированные директивы с примерами
<nice-button class="btn-blue" ng-click="">Hi man</nice-button>

<text ng-model="main.hello" label="name" color="blue"></text>
<text ng-model="main.hello" label="name" color="blue"></text>
<text class="required" ng-model="main.hello" label="name" color="blue" validate="login.emailIsValid" isValid="login.emailValid"></text>

<text ng-model="main.hello" type="password" label="name" color="blue"></text>

<checkbox ng-model="main.boolean"></checkbox>

<select-box ng-model="main.hello" items="{{ main.list }}"></select-box>


<text-area ng-model="main.hello" label="name"></text-area>
