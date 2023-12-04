package bot.application.config.boot;

import java.io.IOException;

public interface MessageInterface {
    public void sendMessage() throws IOException;
    public void sendWhatsMessageMe();
}
