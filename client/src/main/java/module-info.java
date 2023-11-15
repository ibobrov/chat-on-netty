module client {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires io.netty.all;
    requires io.netty.transport;
    requires io.netty.codec;

    opens i.bobrov.client;
}