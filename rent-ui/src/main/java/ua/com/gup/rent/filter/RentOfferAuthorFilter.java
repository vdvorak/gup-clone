package ua.com.gup.rent.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Victor Dvorak
 **/
@ApiModel(description = "RentOfferAuthorFilter By Offer model")
public class RentOfferAuthorFilter implements Serializable {

    @ApiModelProperty("Author User Id  who is register in GUP from table users and references field _id ")
    private String authorId;

    @ApiModelProperty("Public User Id  who is register in GUP from table users and references fileld publicId")
    private String publicId;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    @Override
    public String toString() {
        return "RentOfferAuthorFilter{" +
                "authorId='" + authorId + '\'' +
                ", publicId='" + publicId + '\'' +
                '}';
    }
}
