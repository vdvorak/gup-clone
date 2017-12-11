package ua.com.gup.common.model;

public abstract class FileInfo {

    private String fileName;
    private String s3id;
    private String url;
    private String contentType;
    private Long size;
    private final FileType type;

    public FileInfo(FileType type) {
        this.type = type;
    }

    public FileType getType() {
        return type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getS3id() {
        return s3id;
    }

    public void setS3id(String s3id) {
        this.s3id = s3id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FileInfo{" + "fileName=" + fileName + ", s3id=" + s3id + ", contentType=" + contentType + ", size=" + size + ", type=" + type + '}';
    }
}
