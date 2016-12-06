package fr.osb.smartf.agent.worker.couchbase.model;

/**
 * Created by szagoret on 12.10.2016.
 */
public class KeyValue {
    private String key;
    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
