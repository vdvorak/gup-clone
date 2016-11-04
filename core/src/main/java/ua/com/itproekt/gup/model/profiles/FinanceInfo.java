package ua.com.itproekt.gup.model.profiles;

import java.util.Set;

public class FinanceInfo {
    private String usreou; //ЄДРПОУ
    private String bankCode; //МФО
    private String vatNumber; //ІПН
    private String beneficiaryBank; //Назва банку
    private String beneficiaryAccount; //Рахунок отримувача
    private String legalEntityLocation; //Місцезнаходження юридичної особи
    private Set<String> idAddFile;

    public FinanceInfo setLegalEntityLocation(String legalEntityLocation) {
        this.legalEntityLocation = legalEntityLocation;
        return this;
    }

    public FinanceInfo setUsreou(String usreou) {
        this.usreou = usreou;
        return this;
    }

    public FinanceInfo setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
        return this;
    }

    public FinanceInfo setBankCode(String bankCode) {
        this.bankCode = bankCode;
        return this;
    }

    public FinanceInfo setBeneficiaryBank(String beneficiaryBank) {
        this.beneficiaryBank = beneficiaryBank;
        return this;
    }

    public FinanceInfo setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
        return this;
    }

    public FinanceInfo setIdAddFile(Set<String> idAddFile) {
        this.idAddFile = idAddFile;
        return this;
    }

    public String getLegalEntityLocation() {
        return legalEntityLocation;
    }

    public String getUsreou() {
        return usreou;
    }

    public String getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBeneficiaryBank() {
        return beneficiaryBank;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public Set<String> getIdAddFile() {
        return idAddFile;
    }

    @Override
    public String toString() {
        return "FinanceInfo{" +
                ", legalEntityLocation='" + legalEntityLocation + '\'' +
                ", usreou='" + usreou + '\'' +
                ", beneficiaryAccount='" + beneficiaryAccount + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", beneficiaryBank='" + beneficiaryBank + '\'' +
                ", vatNumber='" + vatNumber + '\'' +
                ", idAddFile=" + idAddFile +
                '}';
    }
}
