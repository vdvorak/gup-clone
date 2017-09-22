package ua.com.gup.model.offers.price;

public enum OrderStatus {

    NONE(0){
        @Override
        public String description() {
            return "Отсутствует";
        }
    },
    SUBMIT_REQUEST(1){
        @Override
        public String description() {
            return "Подача заявки от клиента";
        }
    },
    ACCEPT_REQUEST(2){
        @Override
        public String description() {
            return "Прийом заявки владельцем на обработку";
        }
    },
    IN_PROGRESS_REQUEST(3){
        @Override
        public String description() {
            return "Заявка еще в состоянии обработки (выполняются проверки...либо формируется новый заказ, либо отклоняется заявка, либо оповещаются причины задержки выполнения заявки)";
        }
    },
    NEW_ORDER(4){
        @Override
        public String description() {
            return "Создается новый не подтвержденый заказ";
        }
    },
    NEW_CONFIRMED_ORDER(5){
        @Override
        public String description() {
            return "Заказ подтвержден";
        }
    },
    NEW_RESERVATION_ORDER(6){
        @Override
        public String description() {
            return "Бронирования (заказ будет предварительно подтвержден)";
        }
    },
    IN_PROGRESS_ORDER(7){
        @Override
        public String description() {
            return "Заказ еще в состоянии обработки";
        }
    },
    NOT_EXISTENCE_PRODUCT(8){
            @Override
            public String description() {
                return "Товара нет в наличии";
            }
        },
    OF_STOCK_PRODUCT(9){
        @Override
        public String description() {
            return "Товара уже есть в наличии но еще на складе";
        }
    },
    BEEN_ISSUED_PRODUCT(10){
        @Override
        public String description() {
            return "Товар уже был выдан на руки";
        }
    },
    SUCCESSFULLY_ORDER(11){
        @Override
        public String description() {
            return "Заказ успешно выполнен";
        }
    },
    CANCELED_ORDER(12){
        @Override
        public String description() {
            return "Заказ отклонен";
        }
    };

    private int status;

    public abstract String description();

    private OrderStatus(int status){
        this.status = status;
    }

}
