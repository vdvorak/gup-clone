package ua.com.gup.common.model.uapay.base;

public abstract class Token<T> {


    public abstract T getPayload();


    public abstract void setPayload(T token);
}
