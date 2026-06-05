package com.example.demo.models;

public enum DBTables {

    OPENFOOD_RAW("openfood_raw"),
    OPENFOOD_MEDIAN("openfood_median");

    private final String dbTableName;

    DBTables(String dbTableName) {
        this.dbTableName = dbTableName;
    }

    public String getDbTable() {
        return dbTableName;
    }
}
