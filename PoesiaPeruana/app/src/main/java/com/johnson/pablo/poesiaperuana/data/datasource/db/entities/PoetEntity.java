package com.johnson.pablo.poesiaperuana.data.datasource.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.johnson.pablo.poesiaperuana.domain.model.Poet;

/**
 * Created by pjohnson on 5/04/17.
 */
@Entity(tableName = "poets")
public class PoetEntity {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String name;
    private String birthDate;
    private String deadDate;
    private String pseudonym;
    private String wikiUrl;
    //    private List<Poem> poems;
    private String imageUrl;
    private String city;

    public PoetEntity() {
    }

    public PoetEntity(Poet poet) {
        name = poet.getName();
        birthDate = poet.getBirthDate();
        deadDate = poet.getDeadDate();
        pseudonym = poet.getPseudonym();
        wikiUrl = poet.getWikiUrl();
        imageUrl = poet.getImageUrl();
        city = poet.getCity();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(String deadDate) {
        this.deadDate = deadDate;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

//    public List<Poem> getPoems() {
//        return poems;
//    }
//
//    public void setPoems(List<Poem> poems) {
//        this.poems = poems;
//    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Poet toPoet() {
        Poet poet = new Poet();
        poet.setName(name);
        poet.setCity(city);
        poet.setBirthDate(birthDate);
        poet.setDeadDate(deadDate);
        poet.setImageUrl(imageUrl);
        poet.setWikiUrl(wikiUrl);
        return poet;
    }
}
