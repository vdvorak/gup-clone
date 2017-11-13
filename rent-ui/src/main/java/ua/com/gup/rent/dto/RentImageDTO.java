package ua.com.gup.rent.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentImageDTO {

    @ApiModelProperty(position = 10, example = "58ff0d6c821847a4bc8c5bfc")
    private String imageId;

    @ApiModelProperty(position = 10, example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHwAAAB8CAMAAACcwCSMAAAAY1BMVEUAAAD////7+/vOzs7t7e3x8fH39/eRkZHb29uoqKienp7o6OgYGBhISEiOjo7X19c/Pz8rKyshISGzs7O+vr6AgIB4eHjHx8dTU1NsbGwLCwsxMTHh4eFcXFw2NjYmJiZkZGRG26wjAAAEQklEQVRogeWbWXuqMBCGI6tsLqC4W///rzxq26cksyTCDDfnu4a8Jc2sGc1CWumhPF36oEeNLDnaPc7mqdv88KzYmG8dZ4d3d/Orub+8upk/zfw/78xQq1nhe4ttujnhJ5tt1vPBo95hB64qAt+77DBLE4EXLjvwXy4BjwG7TmeDbwA8zMol4DlgB551AXgG2afQdyfDVxC+nAuOfHge/PJUeAnY92gueNSM3/TJ8Ctglx+8PREOdn3/ydsT4Q+HHepeJOCp493Ccggh+PVssQPjiRB8adlYqFcVgg8DWpl9/LoUfFWNeF1k2x/FuNenwZM8z7vl5/stAscUpVVVJVmIhw+CZ9eu7F8q45RdNIvz0/Fen+ttczutdonnL/DDq/IxtOZjTllUVrjZu2lyNsx44FEHw5b5KuGSUed62t+HGc/DwrOyxlc0TZcMH1yDzH2gtqBOJAff3bkl+/gnQb4WyO5Yuh8+haegBnK16cvDIT/6HntphR49Eh63IYsG64jVERS886/3meprMBxJiCcLmigOR6qQ6aoTF4PCYdkpoksI/KDDhhkeAq8ozzJZTeSFBxnuGIGoD+GwAJJRGwMUgFdn/zpjtAmxcy5ETNAFy/FceKrDNmh+6cKVPhzPbR14osPeoWwXrnPUqdLVhiO1voA2VFvOhq812NSmu3CNSMo0Ym24yq5Dz4bCr7Kp07c2JNuGq8RSpoi04CoJDNOBtuAawRSkLxR8qwDnekRDeKpx3kgjd+BLjVDOlalDOLyvmC72xmMI17C0lmFbcPES6amvULhGrfD/woP/5xrwmmvSaR+4MyhNCfhOAW6IdgyAzx1RLbhKBsddfAzhV5UyLRCuEtW4yKIez7mArp7JmDPderbgKmk7c+dkwTW8zDN3Jp2cBdeplmhTt+DJRQVeUzdPthkqNaKoGTEb7u1yjxQxTGDDVULLS3gCbcO1+mBEqep0JtS6jyjd8fs6buYtxNc48KV/kdGCbSEHHulY+rc2boCbqQn4ozxl4Rr12kDbMmLgCzVj+9GOg6u0Rv7Usl8OZ35EZR15mN/p+ZmX1jxczb+/1Cx4uKqpFx641pXeSxfezp9KVDLot5w0GisotC63jEn8cJ3KxcDQgpZSWp/utsVQeKTDBjEVLyJVqgfYISEqWDi/PF2wdiDgCpEVqZqo2l0+qUAySAqeSXsa7GKPnpORZW+xKp1umchm0egoGQ2PJE88XqwxzSLBnKbBCVynSi62IiMLPjj4Xc5YUV0ZfhhP5m6VnPzmxxCrLwH2LawhBCVg7Vu66e2b/pyeyzINd+/oqY/uK6+Igx4GXxyI5e/7Yv3tM7O47ImHsLmgT+CLJeLqLvu17axTdO71xP+CKmTcGMyBNh226HXvRMLWN/wdNmgdDwy+7skhiLRo/ra/XXl/OBY65b0sHq2pm76kxy/eSg7l/nS8eZ976x+5jjaUwnDDVwAAAABJRU5ErkJggg==")
    private String base64Data;

    public RentImageDTO() {
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }
}
