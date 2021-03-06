package model.persistence;

import org.json.JSONObject;

public interface Writable {

    JSONObject toJson();
}
