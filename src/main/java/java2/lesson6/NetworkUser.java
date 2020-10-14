package java2.lesson6;

public interface NetworkUser {

    int SERVER_PORT = 8189;

    boolean getConnected();

    void setConnected(boolean connected);

    void start();
}
