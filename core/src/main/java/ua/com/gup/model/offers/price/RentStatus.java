package ua.com.gup.model.offers.price;

public enum RentStatus {

    AVAILABLE(1){
        @Override
        public String description() {
            return "Свободные для аренды";
        }
    },
    IN_PROGRESS(2){
        @Override
        public String description() {
            return "Находится в обработке";
        }
    },
    RENTED(3){
        @Override
        public String description() {
            return "Уже зайнятые";
        }
    },
    EXPIRED(4){
        @Override
        public String description() {
            return "Просроченные";
        }
    };

    private int status;

    public abstract String description();

    private RentStatus(int status){
        this.status = status;
    }

}
