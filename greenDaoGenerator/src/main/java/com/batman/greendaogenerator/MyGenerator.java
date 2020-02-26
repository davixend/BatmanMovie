package com.batman.greendaogenerator;


public class MyGenerator {
    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(46, "com.fa.klipix.db");

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addTables(final Schema schema) {
        addUserEntities(schema);
    }

    private static Entity addUserEntities(final Schema schema) {
        Entity user = schema.addEntity("Users");
        user.addIdProperty().primaryKey().autoincrement();
        user.addStringProperty("user_id").unique();
        user.addIntProperty("gender");
        user.addIntProperty("visits");
        user.addIntProperty("level");
        user.addIntProperty("followers");
        user.addIntProperty("following");
        user.addStringProperty("name");
        user.addStringProperty("website");
        user.addStringProperty("username").notNull();
        user.addStringProperty("userPhoto");
        user.addStringProperty("location");
        user.addStringProperty("about");
        user.addIntProperty("verified");
        return user;
    }
}