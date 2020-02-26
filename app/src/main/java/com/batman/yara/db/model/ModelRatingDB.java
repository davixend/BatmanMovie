package com.batman.yara.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "rating", indices = {@Index(value = {"imdbID", "source"}, unique = true)})
public class ModelRatingDB {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "source")
    private String source;
    private String value;
    @ColumnInfo(name = "imdbID")
    private String imdbID;

    public ModelRatingDB(String source, String value, String imdbID) {
        this.source = source;
        this.value = value;
        this.imdbID = imdbID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getValue() {
        return value;
    }

    public String getImdbID() {
        return imdbID;
    }
}
