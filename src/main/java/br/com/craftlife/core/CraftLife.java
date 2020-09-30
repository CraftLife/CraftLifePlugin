package br.com.craftlife.core;

import br.com.craftlife.core.database.Database;
import br.com.craftlife.core.database.PostgreSQL;
import br.com.craftlife.core.resource.CustomFile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public final class CraftLife extends JavaPlugin {

    @Getter @Setter(AccessLevel.PRIVATE)
    private static CraftLife instance;

    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
    private CustomFile databaseConfig;

    @Getter @Setter(AccessLevel.PRIVATE)
    private Database database;

    @SneakyThrows
    @Override
    public void onLoad() {
        CraftLife.setInstance(this);
        this.setDatabaseConfig(new CustomFile(this, "database.yml"));
        this.getDatabaseConfig().saveFile();

        if (!this.setupDatabase()) {
            this.getServer().shutdown();
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private boolean setupDatabase() throws ClassNotFoundException {
        ConfigurationSection configSection =
                this.getDatabaseConfig().getConfig().getConfigurationSection("database");
        if (configSection == null) return false;

        this.setDatabase(new PostgreSQL(configSection.getString("url"), configSection.getString("username"),
                configSection.getString("password")));

        String sqlScript = "";

        return true;


    }
}
