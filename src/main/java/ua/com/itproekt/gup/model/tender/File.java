package ua.com.itproekt.gup.model.tender;


import ua.com.itproekt.gup.util.FileType;

public class File {
    String id;
    String name;
    long size;
    FileType fileType;

    public File(){
    }

    public File(String id, String name, long size, FileType fileType) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.fileType = fileType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "File{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", fileType=" + fileType +
                '}';
    }
}
