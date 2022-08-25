package uk.co.tmdavies.genshinplugin.utils;

import org.yaml.snakeyaml.Yaml;
import uk.co.tmdavies.genshinplugin.GenshinPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class ShadowConfig {

    private final File file;
    private final Yaml configuration;
    private Map<String, Object> writable;

    public ShadowConfig(String fileName) {

        if (!fileName.endsWith(".yml")) fileName = fileName + ".yml";

        this.file = new File(GenshinPlugin.getInstance().getDataFolder() + "/" + fileName);
        this.configuration = new Yaml();

        try {

            this.writable = this.configuration.load(new FileInputStream(this.file));

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }

    }

    public void reload() {

        this.configuration.dump(this.writable);

        try {

            this.writable = this.configuration.load(new FileInputStream(this.file));

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }

    }

    public void add(String path, Object value) {

        this.writable.put(path, value);

        reload();

    }

    public void remove(String path) {

        this.writable.remove(path);

        reload();

    }

    public Object get(String path) {

        return this.writable.get(path);

    }

    public String getString(String path) {

        Object object = this.writable.get(path);

        if (!(object instanceof String)) return null;

        return (String) object;

    }

    public int getInt(String path) {

        Object object = this.writable.get(path);

        if (!(object instanceof Integer)) return -1;

        return (int) object;

    }

}
