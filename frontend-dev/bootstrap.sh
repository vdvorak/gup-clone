#!/bin/bash

# function installCurl {
#   sudo apt-get install curl -y
# }
#
# function installNode {
#   curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.31.2/install.sh | bash
#   nvm install 4.4.7
#   nvm use 4.4.7
#   npm install
#   npm install -g webpack
# }
#
# which curl | grep "." >/dev/null 2>&1  || installCurl
# which node | grep "." >/dev/null 2>&1 || installNode
npm i
./prod_compile_webpack.sh
