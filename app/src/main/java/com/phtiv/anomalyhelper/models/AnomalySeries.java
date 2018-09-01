package com.phtiv.anomalyhelper.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AnomalySeries {
    @Id(autoincrement = false)
    int ID;
    String NAME;
    String ENL_SCORE;
    String RES_SCORE;
    String WINNER;

    @Generated(hash = 1576878776)
    public AnomalySeries(int ID, String NAME, String ENL_SCORE, String RES_SCORE,
            String WINNER) {
        this.ID = ID;
        this.NAME = NAME;
        this.ENL_SCORE = ENL_SCORE;
        this.RES_SCORE = RES_SCORE;
        this.WINNER = WINNER;
    }

    @Generated(hash = 938391229)
    public AnomalySeries() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getENL_SCORE() {
        return ENL_SCORE;
    }

    public void setENL_SCORE(String ENL_SCORE) {
        this.ENL_SCORE = ENL_SCORE;
    }

    public String getRES_SCORE() {
        return RES_SCORE;
    }

    public void setRES_SCORE(String RES_SCORE) {
        this.RES_SCORE = RES_SCORE;
    }

    public String getWINNER() {
        return WINNER;
    }

    public void setWINNER(String WINNER) {
        this.WINNER = WINNER;
    }
}
