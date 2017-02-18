package ua.com.itproekt.gup.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Validator2Util {

    NUMBER_CREDIT_CARD() {
        @Override
        public String description() {
            return "Номер кредитки";
        }

        @Override
        public boolean check(String val) {
            Pattern pattern = Pattern.compile( this.number_credit_card );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.number_credit_card;
        }
    },
    ICQ() {
        @Override
        public String description() {
            return "ICQ";
        }

        @Override
        public boolean check(String val) {
            Pattern pattern = Pattern.compile( this.isq );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.isq;
        }
    },
    LATIN() {
        @Override
        public String description() {
            return "Набор из букв и цифр (латиница)";
        }

        @Override
        public boolean check(String val) {
            Pattern pattern = Pattern.compile( this.latin );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.latin;
        }
    },
    LATIN_CYRILLIC() {
        @Override
        public String description() {
            return "Набор из букв и цифр (латиница + кириллица)";
        }

        @Override
        public boolean check(String val) {
            Pattern pattern = Pattern.compile(this.latin_cyrillic);
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.latin_cyrillic;
        }
    },
    DOMAIN() {
        @Override
        public String description() {
            return "Домен (например abcd.com)";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.domain );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.domain;
        }
    },
    IPv4() {
        @Override
        public String description () {
            return "IPv4: (подкорректировано@runcore)";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.ipv4 );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.ipv4;
        }
    },
    IPv6() {
        @Override
        public String description () {
            return "IPv6";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.ipv6 );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.ipv6;
        }
    },
    USERNAME() {
        @Override
        public String description () {
            return "Имя пользователя (с ограничением 2-20 символов, которыми могут быть буквы и цифры, первый символ обязательно буква)";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.username );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.username;
        }
    },
    PASSWORD() {
        @Override
        public String description () {
            return "Пароль (Строчные и прописные латинские буквы, цифры)";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.password );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.password;
        }
    },
    SUPER_PASSWORD() {
        @Override
        public String description () {
            return "Пароль (Строчные и прописные латинские буквы, цифры, спецсимволы. Минимум 8 символов)";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.super_password );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.super_password;
        }
    },
    YYYY_MM_DD() {
        @Override
        public String description () {
            return "Дата в формате YYYY-MM-DD";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.yyyy_mm_dd );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.yyyy_mm_dd;
        }
    },
    UPD() {
        @Override
        public String description () {
            return "UPD. Более строгая проверка, предложенная@runcore";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.upd );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.upd;
        }
    },
    DD__MM__YYYY() {
        @Override
        public String description() {
            return "Дата в формате DD/MM/YYYY";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.dd__mm__yyyy );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.dd__mm__yyyy;
        }
    },
    NUMBER() {
        @Override
        public String description() {
            return "Целые числа и числа с плавающей точкой (разделитель точка)";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.number );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.number;
        }
    },
    UUID() {
        @Override
        public String description() {
            return "UUID";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.uuid );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.uuid;
        }
    },
    LATITUDE_LONGITUDE() {
        @Override
        public String description() {
            return "Широта или долгота";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.latitude_longitude );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.latitude_longitude;
        }
    },
    UPD_EMAIL() {
        @Override
        public String description () {
            return "UPD. E-mail (от@kvf77)";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.upd_email );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.upd_email;
        }
    },
    UPD_URL() {
        @Override
        public String description () {
            return "UPD. URL на латинице. Если нужно распознавать и кириллические домены, необходимо изменить все «a-z0-9» на «а-яёa-z0-9» и добавить в список доменных зон «рф» (от@kvf77)";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.upd_url );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.upd_url;
        }
    },
    UPD_HH__MM__SS() {
        @Override
        public String description () {
            return "UPD. Время в формате HH:MM:SS (от@runcore)";
        }

        @Override
        public boolean check (String val){
            Pattern pattern = Pattern.compile( this.upd_hh__mm__ss );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.upd_hh__mm__ss;
        }
    },
    UPD_MAC() {
        @Override
        public String description() {
            return "UPD. Mac-адрес (от@tiahin)";
        }

        @Override
        public boolean check(String val){
            Pattern pattern = Pattern.compile( this.upd_mac );
            Matcher matcher = pattern.matcher(val);
            return matcher.matches();
        }

        @Override
        public String value() {
            return this.upd_mac;
        }
    };

    public abstract boolean check(String val);
    public abstract String description();
    public abstract String value();


    /**
     *  Номер кредитки:
     */
    public final String number_credit_card = "[0-9]{13,16}";

    /**
     * ICQ:
     */
    public final String isq = "([1-9])+(?:-?\\d){4,}";

    /**
     *  Набор из букв и цифр (латиница):
     */
    public final String latin = "^[a-zA-Z0-9]+$";

    /**
     *  Набор из букв и цифр (латиница + кириллица):
     */
    public final String latin_cyrillic = "^[а-яА-ЯёЁa-zA-Z0-9]+$";

    /**
     * Домен (например abcd.com):
     */
    public final String domain = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$";

    /**
     * IPv4: (подкорректировано@runcore)
     */
    public final String ipv4 = "((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)";

    /**
     * IPv6:
     */
    public final String ipv6 = "((^|:)([0-9a-fA-F]{0,4})){1,8}$";

    /**
     * Имя пользователя (с ограничением 2-20 символов, которыми могут быть буквы и цифры, первый символ обязательно буква):
     */
    public final String username = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";

    /**
     * Пароль (Строчные и прописные латинские буквы, цифры):
     */
    public final String password = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";

    /**
     * Пароль (Строчные и прописные латинские буквы, цифры, спецсимволы. Минимум 8 символов):
     */
    public final String super_password = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$";

    /**
     * Дата в формате YYYY-MM-DD:
     */
    public final String yyyy_mm_dd = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";

    /**
     * UPD. Более строгая проверка, предложенная@runcore:
     */
    public final String upd = "(19|20)\\d\\d-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)";

    /**
     * Дата в формате DD/MM/YYYY:
     */
    public final String dd__mm__yyyy = "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d";

    /**
     * Целые числа и числа с плавающей точкой (разделитель точка):
     */
    public final String number = "\\-?\\d+(\\.\\d{0,})?";

    /**
     * UUID:
     */
    public final String uuid = "^[0-9A-Fa-f]{8}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{4}\\-[0-9A-Fa-f]{12}$";

    /**
     * Широта или долгота:
     */
    public final String latitude_longitude = "-?\\d{1,3}\\.\\d+";

    /**
     * UPD. E-mail (от@kvf77):
     */
    public final String upd_email = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

    /**
     * UPD. URL на латинице. Если нужно распознавать и кириллические домены, необходимо изменить все «a-z0-9» на «а-яёa-z0-9» и добавить в список доменных зон «рф» (от@kvf77):
     */
    public final String upd_url = "~^(?:(?:https?|ftp|telnet)://(?:[a-z0-9_-]{1,32}(?::[a-z0-9_-]{1,32})?@)?)?(?:(?:[a-z0-9-]"
            + "{1,128}\\.)+(?:ru|su|com|net|org|mil|edu|arpa|gov|biz|info|aero|inc|name|[a-z]{2})|(?!0)(?:"
            + "(?!0[^.]|255)[0-9]{1,3}\\.){3}(?!0|255)[0-9]{1,3})(?:/[a-z0-9.,_@%&?+=\\~/-]*)?(?:#[^ '\"&]*)?$~i";

    /**
     * UPD. Время в формате HH:MM:SS (от@runcore):
     */
    public final String upd_hh__mm__ss = "^([0-1]\\d|2[0-3])(:[0-5]\\d){2}$";

    /**
     * UPD. Mac-адрес (от@tiahin):
     */
    public final String upd_mac = "([0-9a-fA-F]{2}([:-]|$)){6}$|([0-9a-fA-F]{4}([.]|$)){3}";

}
