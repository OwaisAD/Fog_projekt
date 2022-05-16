package dat.startcode.model.entities;

import java.time.LocalDate;

public class Request {
    private int carportId;
    private int coverageId;
    private int userId;
    private int dimensionId;
    private int shedId;
    private boolean hasShed;
    private boolean isConfirmed;
    private LocalDate created;


    public Request(int coverageId, int userId, int dimensionId, int shedId, boolean hasShed, boolean isConfirmed) {
        this.coverageId = coverageId;
        this.userId = userId;
        this.dimensionId = dimensionId;
        this.shedId = shedId;
        this.hasShed = hasShed;
        this.isConfirmed = isConfirmed;
    }

    public Request(int carportId, int coverageId, int userId, int dimensionId, int shedId, boolean hasShed, boolean isConfirmed) {
        this.carportId = carportId;
        this.coverageId = coverageId;
        this.userId = userId;
        this.dimensionId = dimensionId;
        this.shedId = shedId;
        this.hasShed = hasShed;
        this.isConfirmed = isConfirmed;
    }

    public int getCoverageId() {
        return coverageId;
    }

    public void setCoverageId(int coverageId) {
        this.coverageId = coverageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
    }

    public int getShedId() {
        return shedId;
    }

    public void setShedId(int shedId) {
        this.shedId = shedId;
    }

    public boolean isHasShed() {
        return hasShed;
    }

    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Request{" +
                "carportId=" + carportId +
                ", coverageId=" + coverageId +
                ", userId=" + userId +
                ", dimensionId=" + dimensionId +
                ", shedId=" + shedId +
                ", hasShed=" + hasShed +
                ", isConfirmed=" + isConfirmed +
                ", created=" + created +
                '}';
    }
}
