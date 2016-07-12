Build folder:

  /public/src/main/webapp/WEB-INF/

Easy web-server(launch in the build folder):

  install: npm install -g local-web-server

  docs: https://www.npmjs.com/package/local-web-server

  usage: ws



Compile:

  dev: . dev_compile_webpack.sh

  prod: . prod_compile_webpack.sh


Install dependencies:
  . bootstrap.sh



Angular Controllers Plan:

General

BulletinAdd

MessageBox

Dialog

Contacts

Feedback

Profile

ProfileEdit

Register

Login

Favourites

BulletinDetails

BulletinsPreffered

MessageBoxMini

ContactsMini

DialogMini

FavouritesList

FavouritesDetails

Filter (Map, colors, subscribe, category)



Implemented directives example:

<nice-button class="btn-blue | btn-grey">Hi man</nice-button>

<text class="inputSearch" ng-model="main.hello" label="name"></text>



URLS of the app:
/
/index
/error/403
/error/404
/error/500
