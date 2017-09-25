package ua.com.gup.model.file;


import java.io.InputStream;

public class FileUploadWrapper {

    private String serviceName;
    private InputStream inputStream;
    private String contentType;
    private String filename;


    public String getServiceName() {
        return serviceName;
    }

    public FileUploadWrapper setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public FileUploadWrapper setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public FileUploadWrapper setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public FileUploadWrapper setFilename(String filename) {
        this.filename = filename;
        return this;
    }


    @Override
    public String toString() {
        return "FileUploadWrapper{" +
                "serviceName='" + serviceName + '\'' +
                ", inputStream=" + inputStream +
                ", contentType='" + contentType + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
