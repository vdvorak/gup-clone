package ua.com.itproekt.gup.service.transportApi.novaPoshta.requestModels;


public class NovaPoshtaRequestObject {

    private String apiKey;
    private String modelName;
    private String calledMethod;
    private MethodProperties methodProperties;

    public NovaPoshtaRequestObject() {
        this.methodProperties = new MethodProperties();
    }

    public String getApiKey() {
        return apiKey;
    }

    public NovaPoshtaRequestObject setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public NovaPoshtaRequestObject setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getCalledMethod() {
        return calledMethod;
    }

    public NovaPoshtaRequestObject setCalledMethod(String calledMethod) {
        this.calledMethod = calledMethod;
        return this;
    }

    public MethodProperties getMethodProperties() {
        return methodProperties;
    }

    public NovaPoshtaRequestObject setMethodProperties(MethodProperties methodProperties) {
        this.methodProperties = methodProperties;
        return this;
    }

    @Override
    public String toString() {
        return "NovaPoshtaRequestObject{" +
                "apiKey='" + apiKey + '\'' +
                ", modelName='" + modelName + '\'' +
                ", calledMethod='" + calledMethod + '\'' +
                ", methodProperties=" + methodProperties +
                '}';
    }
}
