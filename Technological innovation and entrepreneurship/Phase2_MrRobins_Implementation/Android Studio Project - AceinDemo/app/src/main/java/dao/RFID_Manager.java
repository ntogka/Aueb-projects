package com.example.georgiosmoschovis.aceindemo.dao;

import java.util.Date;
import java.util.Vector;

/**
 * This class illustrates an RFID Data Manager; the RFID reader software.
 */
public class RFID_Manager {
    public static Vector<Vec2<Date, Date>> records = new Vector<Vec2<Date, Date>>();

    public void addTimestamp(Date start, Date end) {
        Vec2<Date, Date> data = new Vec2<Date, Date>(start, end);
        records.add(data);
    }

    public Vector<Vec2<Date, Date>> getAllData() {
        Vector<Vec2<Date, Date>> records_copy = new Vector<>(records);
        return records_copy;
    }
}
