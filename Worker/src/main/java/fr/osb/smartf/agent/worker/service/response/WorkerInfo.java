package fr.osb.smartf.agent.worker.service.response;

import fr.osb.smartf.agent.worker.service.util.WorkerStatus;

/**
 * Represents a object response of availability of worker who contains scheme, host, port and availability
 * <p>
 * Created by szagoret on 15.06.2016.
 */
public class WorkerInfo {

    private String id;
    private String scheme;
    private String host;
    private String path;
    private int port;
    private WorkerStatus status;

    public WorkerInfo() {
    }

    public WorkerInfo(String id, String scheme, String host, String path,
                      int port, WorkerStatus status) {
        this.id = id;
        this.scheme = scheme;
        this.host = host;
        this.path = path;
        this.port = port;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String workerId) {
        this.id = id;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public WorkerStatus getStatus() {
        return status;
    }

    public void setStatus(WorkerStatus status) {
        this.status = status;
    }
}
