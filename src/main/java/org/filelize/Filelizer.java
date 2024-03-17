package org.filelize;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.filelize.FilelizeUtil.getFilelizeId;

public class Filelizer {

    private final Logger log = LoggerFactory.getLogger(Filelizer.class);
    private final String path;
    private final JsonMapper jsonMapper;
    private final FilelizeType defaultFilelizeType;

    public Filelizer(String path) {
        this.path = path;
        this.jsonMapper = new JsonMapper(new ObjectMapper());
        this.defaultFilelizeType = FilelizeType.SINGLE_FILE;
    }

    public Filelizer(String path, ObjectMapper objectMapper, FilelizeType defaultFilelizeType) {
        this.path = path;
        this.jsonMapper = new JsonMapper(objectMapper);
        this.defaultFilelizeType = defaultFilelizeType;
    }

    public <T> T find(String filename, Class<T> valueType) {
        var fullPath = getFullPath(filename);
        try {
            return jsonMapper.readFile(fullPath, valueType);
        } catch (IOException e) {
            log.error("Error occurred when trying to get " + fullPath, e);
            return null;
        }
    }

    public String save(Object object) {
        var filename = getFilename(object);
        save(filename, object);
        return filename;
    }

    public String save(String filename, Object object) {
        try {
            var fullPath = getFullPath(filename);
            jsonMapper.writeFile(fullPath, object);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Error occurred when trying to opens or creates a file for writing",e);
        }
    }

    public List<String> saveAll(List<?> objects) {
        var filenames = new ArrayList<String>();
        for(var object : objects) {
            String filename = save(object);
            filenames.add(filename);
        }
        return filenames;
    }

    public String saveAll(String filename, List<?> objects) {
        return save(filename, objects);
    }

    private String getFullPath(String filename) {
        return path + "/" + filename;
    }

    private String getFilename(Object objectToWrite) {
        var filename = getFilelizeId(objectToWrite);
        if(filename == null) {
            filename = objectToWrite.getClass().getSimpleName();
        }
        filename += ".json";
        return filename;
    }
}
