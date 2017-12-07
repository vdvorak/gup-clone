package ua.com.gup.rent.model.file;


import java.io.InputStream;

public class RentOfferFileUploadWrapper {

    private String serviceName;
    private InputStream inputStream;
    private String contentType;
    private String filename;


    public String getServiceName() {
        return serviceName;
    }

    public RentOfferFileUploadWrapper setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public RentOfferFileUploadWrapper setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public RentOfferFileUploadWrapper setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public RentOfferFileUploadWrapper setFilename(String filename) {
        this.filename = filename;
        return this;
    }


    @Override
    public String toString() {
        return "RentOfferFileUploadWrapper{" +
                "serviceName='" + serviceName + '\'' +
                ", inputStream=" + inputStream +
                ", contentType='" + contentType + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
