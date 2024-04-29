package main;

import com.google.gson.JsonObject;

public interface Serializable {
    JsonObject Serialize();
//    static void Deserialize(JsonObject json);
}
