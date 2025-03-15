package mc.com.serverSite.service.notification;

public interface NotificationGateway<A extends Alert> {

    void send(A alert);

}
