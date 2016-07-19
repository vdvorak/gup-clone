Build folder:

/public/src/main/webapp/WEB-INF/



Easy web-server:

being in 'frontend-dev' folder
```
npm i
```

```
npm start
```


Compile:
dev:
```
. dev_compile_webpack.sh
```

prod:
```
. prod_compile_webpack.sh
```


Install dependencies:
```
. bootstrap.sh
```

Angular Controllers Plan:
https://trello.com/b/WRjaA6ir/novaera


Implemented directives example:

<nice-button class="btn-blue" ng-click="">Hi man</nice-button>

<text ng-model="main.hello" label="name" color="blue"></text>
<text ng-model="main.hello" label="name" color="blue"></text>
<text class="required" ng-model="main.hello" label="name" color="blue" error="main.someError"></text>
<text ng-model="main.hello" type="password" label="name" color="blue"></text>

<checkbox ng-model="main.boolean"></checkbox>



URLS of the app:
see /server.js

Images dimensions:

Avatar:
```
40x40
175x200
```

Image uploaded:
```
*x600
165x120 - fit ratio as much as possible
90x90
```
