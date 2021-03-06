package ua.com.itproekt.gup.model.profiles;

import java.util.Set;

public class UserProfile {
    private String usreou; //ЄДРПОУ
    private String bankCode; //МФО
    private String vatNumber; //ІПН
    private String beneficiaryBank; //Назва банку
    private String beneficiaryAccount; //Рахунок отримувача
    private String legalEntityLocation; //Місцезнаходження юридичної особи
    private Set<String> idAddFile;

    public UserProfile setLegalEntityLocation(String legalEntityLocation) {
        this.legalEntityLocation = legalEntityLocation;
        return this;
    }

    public UserProfile setUsreou(String usreou) {
        this.usreou = usreou;
        return this;
    }

    public UserProfile setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
        return this;
    }

    public UserProfile setBankCode(String bankCode) {
        this.bankCode = bankCode;
        return this;
    }

    public UserProfile setBeneficiaryBank(String beneficiaryBank) {
        this.beneficiaryBank = beneficiaryBank;
        return this;
    }

    public UserProfile setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
        return this;
    }

    public UserProfile setIdAddFile(Set<String> idAddFile) {
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
        return "UserProfile{" +
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
