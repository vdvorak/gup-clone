/**
 * Created by Юля on 17.05.2016.
 */

function localDateTime(long) {
    var offset = new Date().getTimezoneOffset();
    long = (new Date(parseInt(long) + offset * 60000));
    long = moment(long).locale("ru").format('LLL');
    return long;
}