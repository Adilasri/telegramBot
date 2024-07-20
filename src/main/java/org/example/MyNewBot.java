package org.example;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.ws.rs.core.Link;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MyNewBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        try {
            System.out.println("send ");
            String message = update.getMessage().getText();
            CloseableHttpClient client = HttpClients.createDefault();
            URI uri = new URIBuilder("https://app.seker.live/fm1/send-message")
                    .setParameter("id", "329368963")
                    .setParameter("text", message )
                    .build();
            HttpGet request = new HttpGet(uri);
            CloseableHttpResponse response =  client.execute(request);
            SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), EntityUtils.toString(response.getEntity()));
            execute(sendMessage);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }





    @Override
    public String getBotUsername() {
        return "Adi3";
    }

    @Override
    public String getBotToken() {
        return "7313547207:AAGsDxBdcqLEVjbFzl900OkzDFcOhSuxphs";
    }


}
