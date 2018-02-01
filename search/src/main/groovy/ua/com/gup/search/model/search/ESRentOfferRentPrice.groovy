package ua.com.gup.search.model.search

class ESRentOfferRentPrice {
    Integer totalCost
    Integer prePayment

    ESRentOfferRentPrice() {
        totalCost = 0
        prePayment = 0
    }

    def addToTotalCost(Integer cost) {
        totalCost += cost
    }

    def multiplyTotalCost(int x) {
        totalCost = totalCost * x
    }

    def calculatePrePayment(int prepaymentPercent) {
        if (prepaymentPercent > -1)
            prePayment = totalCost / 100 * prepaymentPercent
    }
}
