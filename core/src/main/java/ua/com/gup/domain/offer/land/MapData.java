package ua.com.gup.domain.offer.land;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MapData {
    @ApiModelProperty(position = 0)
    private String unit_area;

    @ApiModelProperty(position = 10)
    private String area;

    @ApiModelProperty(position = 20)
    private String id_office;

    @ApiModelProperty(position = 30)
    private String cadnum;

    @ApiModelProperty(position = 40)
    private String use;

    @ApiModelProperty(position = 50)
    private String purpose;

    @ApiModelProperty(position = 60)
    private String ownershipcode;

    public String getUnit_area() {
        return unit_area;
    }

    public void setUnit_area(String unit_area) {
        this.unit_area = unit_area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getId_office() {
        return id_office;
    }

    public void setId_office(String id_office) {
        this.id_office = id_office;
    }

    public String getCadnum() {
        return cadnum;
    }

    public void setCadnum(String cadnum) {
        this.cadnum = cadnum;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getOwnershipcode() {
        return ownershipcode;
    }

    public void setOwnershipcode(String ownershipcode) {
        this.ownershipcode = ownershipcode;
    }

    @Override
    public String toString() {
        return "MapData [unit_area = " + unit_area + ", area = " + area + ", id_office = " + id_office + ", cadnum = " + cadnum + ", use = " + use + ", purpose = " + purpose + ", ownershipcode = " + ownershipcode + "]";
    }
}
