package de.rubicon.core;

import de.rubicon.util.Configuration;
import de.rubicon.util.Info;
import de.rubicon.util.MySQL;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    private static DiscordCore discordCore;
    private static MySQL mySQL;

    public static Configuration getConfiguration() {
        return configuration;
    }

    private static Configuration configuration;

    public static void main(String[] args) {
        configuration = new Configuration(new File(Info.CONFIG_FILE));
        mySQL = new MySQL(Info.MYSQL_HOST, Info.MYSQL_PORT, Info.MYSQL_USER, Info.MYSQL_PASSWORD, Info.MYSQL_DATABASE);
        mySQL.connect();
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE test(TEXT test)");
            mySQL.executePreparedStatement(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        discordCore = new DiscordCore();
        discordCore.start();
    }

    public static DiscordCore getDiscordCore() {
        return discordCore;
    }

    public static MySQL getMySQL() {
        return mySQL;
    }
}
