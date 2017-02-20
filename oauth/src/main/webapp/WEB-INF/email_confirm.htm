<!--| https://www.sitepoint.com/community/t/on-form-submit-set-cookie/2062 |-->

<html>
    <head>
        <title>E-mail confirm</title>
        <script type="text/javascript">
            <!--
            cookie_name = "landasp"
            expdays     = 365

            // An adaptation of Dorcht's cookie functions:
            function set_cookie(name,value,expires,path,domain,secure){
                if( !expires ) expires = new Date()

                document.cookie = name + "=" + escape(value) +
                ((expires==null) ? "" : "; expires=" + expires.toGMTString()) +
                ((path==null) ? "" : "; path=" + path) +
                ((domain==null) ? "" : "; domain=" + domain) +
                ((secure==null) ? "" : "; secure");
            }

            function get_cookie(name){
                var  arg = name + "=";
                var alen = arg.length;
                var clen = document.cookie.length;
                var    i = 0;
                while( i < clen ){
                    var j = i + alen;
                    if( document.cookie.substring(i,j)==arg ) return get_cookie_val(j);

                    i = document.cookie.indexOf(" ",i) + 1;
                    if( i==0 ) break;
                }
                return null;
            }

            function get_cookie_val(offset){
                var endstr = document.cookie.indexOf (";",offset);
                if( endstr==-1 ) endstr = document.cookie.length;
                return unescape(document.cookie.substring(offset,endstr));
            }

            function delete_cookie(name,path,domain){
                document.cookie = name + "=" +
                ((path==null) ? "" : "; path=" + path) +
                ((domain==null) ? "" : "; domain=" + domain) +
                "; expires=Thu, 01-Jan-00 00:00:01 GMT";
            }

            function saving_cookie(){
                var expdate = new Date ();
                expdate.setTime( expdate.getTime() + (expdays*24*3600*1000) ); //  plus 1-day

                Data = "cooked"

                set_cookie(cookie_name,Data,expdate)
            }

            function get_cookie_data(){
                info = get_cookie(cookie_name)

                if( !info ) document.getElementById("display1").style.display = "block"
                else document.getElementById("display2").style.display = "block"
            }
            // -->
        </script>
    </head>

    <body onload="get_cookie_data()">

        <div id="display1" style="display:none">
            <div class="register">
                <strong>REGISTER NOW</strong>
                <form action="#" name="reserve" id="reserve" onsubmit="saving_cookie()">
                    <span class="side">
                        <br>Name<br>
                        <input name="name" type="text" id="name" size=16" class="inputf">

                        <br><br>Tel<br>
                        <input name="tel" type="text" id="tel" size="16" class="inputf">

                        <br><br>Email<br>
                        <input name="email" type="text" id="email" size="16" class="inputf">

                        <br><br>Address<br>
                        <textarea name="address" cols="14" rows="2" class="inputf" id="address"></textarea>
                        <br><br>

                        <input type="submit" name="Submit" value="Submit" class="button1">
                    </span>
                </form>
            </div>
        </div>

        <div id="display2" style="display:none">
            <div class="register">
                <strong>REGISTERED</strong>
                <br><br>For testing <a href="#null" onclick="delete_cookie(cookie_name)">Delete Cookie</a>
            </div>
        </div>
    </body>
</html>