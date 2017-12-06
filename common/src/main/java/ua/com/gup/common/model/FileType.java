package ua.com.gup.common.model;

public enum FileType {
    IMAGE("/images/", "image", ImageFileInfo.class);

    
    private final String path;
    private final String requestParamName;
    private final Class clazz;

    private FileType(String path, String requestParamName, Class<? extends FileInfo> clazz) {
        this.path = path;
        this.requestParamName = requestParamName;
        this.clazz = clazz;
    }

    public String getPath() {
        return path;
    }

    public FileInfo createInstance() throws InstantiationException, IllegalAccessException {
        return (FileInfo) clazz.newInstance();
    }

    public String getRequestParamName() {
        return requestParamName;
    }

    public static FileType fromContentType(String contentType) {
        String[] split = contentType.split("/");
        switch (split[0]) {
            case "image":
                return IMAGE;
            default:
                throw new UnsupportedOperationException("Unsupported content-type");
        }

    }

}
