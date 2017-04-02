//package ua.com.itproekt.gup.service.blockchain;
//
//import ua.com.itproekt.gup.model.order.blockchain.Chain;
//import ua.com.itproekt.gup.model.order.blockchain.Transaction;
//
////                    ////////////////////////////////////////////////////////////////////////////////////////////////////
////                    ---------------------------- информация об обявлении
////                    String buyerId  = userId;
////                    String sellerId = offer.getAuthorId();
////                    long price = offer.getPrice();
////                    String offerId = offer.getId();   String offerTitle = offer.getTitle();   String offerDescription = offer.getDescription();
////                    ////////////////////////////////////////////////////////////////////////////////////////////////////
////                    ---------------------------- информация об покупателе
////                    ProfileInfo buyerInfo = profilesService.findPrivateProfileByIdAndUpdateLastLoginDate(userId);
////                    buyerInfo.getUserBalance();
////
////                    ProfileInfo sellerInfo = profilesService.findPrivateProfileByIdAndUpdateLastLoginDate(offer.getAuthorId());
////                    sellerInfo.getProfile().getFinanceInfo().getBankCode();
////                    sellerInfo.getUserBalance();
//////////////////////////////////////////////////////////////////////////////////////////////////////
//// - MoneyTransferTransactio
////
//// outputs
//// [
//// - проверяем доступный баланс на счету полкупателя (buyer)
//// + указываем сумму покупки которая должна уйти продавцу (seller)
//// + указываем хеш256 публичного ключа продавца (seller)
//// ]
////
//// data
//// - в случае если оплата через карту банка
//// + указываем код карты покупателя (buyer)
//// + указываем сумму покупки (которая должна сняться из этой карты покупаталя (buyer))
//// + указываем хеш256 публичного ключа банка
//// + просто сигнатура банка
//// + ID-транзакции банка
////
//// type = MONEY_TRANSFER
//// _hash: Хеш256 транзакции
//// timestamp
//// сигнатура транзакции (+++)хеш256
////
//// inputs []
//////////////////////////////////////////////////////////////////////////////////////////////////////
//
//public class BlockChainAlgorithm {
//
//    private Chain chain;
//
//    public BlockChainAlgorithm(Chain chain){
//        this.chain = chain;
//    }
//
//    public Transaction reject(){
//        Transaction transaction = chain.getTransaction();
//        // TODO
//
//        return transaction;
//    }
//
//    public Transaction confirm(){
//        Transaction transaction = chain.getTransaction();
//        // TODO
//
//        return transaction;
//    }
//
//}
