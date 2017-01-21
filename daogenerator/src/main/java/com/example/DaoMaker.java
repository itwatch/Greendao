package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoMaker {

    public static void main(String[] agrs) {


        Schema schema = new Schema(1, "com.student.entity");
        addstudentSchema(schema);
        addorkSchema(schema);
        schema.setDefaultJavaPackageDao("com.student.dao");
        try {
            new DaoGenerator().generateAll(schema, "C:\\Users\\Administrator\\Desktop\\MyApplication\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addorkSchema(Schema schema) {
        Entity entity = schema.addEntity("WorkMan");
        entity.addIdProperty().autoincrement().primaryKey();
        entity.addStringProperty("name");
        entity.addStringProperty("address");
        entity.addIntProperty("age");
    }


    private static void addstudentSchema(Schema schema) {
        Entity entity = schema.addEntity("Student");
        entity.addIdProperty().autoincrement().primaryKey();
        entity.addStringProperty("name");
        entity.addStringProperty("address");
        entity.addIntProperty("age");

    }
}
