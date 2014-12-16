package UsedByAll;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.activation.DataHandler;
import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by User on 12/8/2014.
 */
public class GmailMessager {


    public Store initializePOP3 (TestUser testUser) throws MessagingException {
        String POP_AUTH_USER = testUser.getEmail();
        String POP_AUTH_PWD = testUser.getEPassword();


        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";



        System.out.println("Начинаю соединение с Gmail через POP3");
        Properties pop3Props = new Properties();

        pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
        pop3Props.setProperty("mail.pop3.port", "995");
        pop3Props.setProperty("mail.pop3.socketFactory.port", "995");


        URLName url = new URLName("pop3", "pop.gmail.com", 955, "", POP_AUTH_USER, POP_AUTH_PWD);
        //Session session = Session.getDefaultInstance(pop3Props);
        Session session = Session.getInstance(pop3Props, null);
        Store store = session.getStore(url);


        return store;

    }
    public String getLastMessageTime(TestUser testUser) throws MessagingException {

        String FOLDER_INBOX = "INBOX"; //name of INBOX folder
        Store store = initializePOP3(testUser);

        store.connect();

        Folder folder = store.getFolder(FOLDER_INBOX);
        try{
            folder.open(Folder.READ_WRITE);
        } catch(MessagingException ex) {
            folder.open(Folder.READ_ONLY);
        }
        Message[] messages = folder.getMessages();
        System.out.println("Messages.length: " + (messages.length-1));

            Message message = messages[messages.length-1];
            System.out.println("Email Number " + (messages.length-1));
            System.out.println("Subjet: " + message.getSubject());
            System.out.println("Received Date " + message.getSentDate());
            // System.out.println("Text: "+ message.getContent().toString());
            String detectMessageTime = message.getSentDate().toString();
        folder.close(false);
        store.close();
        return detectMessageTime;
    }
    public String openAndReturnLink(TestUser testUser, String requiredSubject, String requiredLink, String Separator) throws MessagingException, IOException {
        String FOLDER_INBOX = "INBOX"; //name of INBOX folder
        String activationLink = null;

        Store store = initializePOP3(testUser);
        store.connect();

        Folder folder = store.getFolder(FOLDER_INBOX);
        try{
            folder.open(Folder.READ_WRITE);
        } catch(MessagingException ex) {
            folder.open(Folder.READ_ONLY);
        }
        Message[] messages = folder.getMessages();
        System.out.println("Messages.length: " + (messages.length-1));

        Message message = messages[messages.length-1];

        String messageSubject = message.getSubject();
        System.out.println("Subject: " + messageSubject);// Subject повідомлення

        if(!messageSubject.contains(requiredSubject))
            System.err.println("Неверная тема сообщения");

        String contentType = message.getContentType(); // тип контенту повідомлення
        String textMessage = "";

        if (contentType.contains("text/plain") || contentType.contains("text/html")) {
            textMessage = message.getContent() != null ? textMessage = message.getContent().toString() : ""; //if then else
        }else if (contentType.contains("multipart")) {
            Multipart multipart = (Multipart) message.getContent();

            for(int j=0; j<multipart.getCount(); j++){
                BodyPart bodyPart = multipart.getBodyPart(j);

                String disposition = bodyPart.getDisposition(); // disposition -- расположение
                if (disposition != null && (disposition.equals(BodyPart.ATTACHMENT))) {
                    System.out.println("Mail have some attachment");
                    DataHandler handler = bodyPart.getDataHandler();
                    System.out.println("file name: " + handler.getName()); //зберегти attachment в файл
                }else {
                    textMessage = bodyPart.getContent() != null ? textMessage = bodyPart.getContent().toString() : ""; // мультіпарт в текст
                }

            }
        }
        //CharSequence searchLink = requiredLink; // CharSequence -- послідовність символів, requiredLink -- подаємо з тесту




        System.out.println("Message text: "+ textMessage);
        System.out.println("Required Link: "+ requiredLink);
        if (textMessage.contains(requiredLink)) {
            System.out.println("Письмо содержит активационную ссылку");
        }else {
            System.err.println("Активационная ссылка не найдена");

        }
        Document letter = Jsoup.parse(textMessage);
        Elements links = letter.getElementsByTag("p");
        for(Element link : links ){
            System.out.println("link: "+link.text());
        }
        Elements test1 = letter.getElementsByAttributeValueEnding("p", requiredLink);
        for(Element test : test1 ){
            System.out.println("test: "+test.text());
        }
        //System.out.println("activation jsoup: "+ letterByTag.toString());


    //   int indexOf = textMessage.lastIndexOf(requiredLink); // перший індекс -- початок лінку

      //  int lastIndexOf = textMessage.lastIndexOf(Separator, indexOf); // Separator - разделитель, для рековери - "Regards", для регистрейшн - " "

      //  activationLink = textMessage.substring(indexOf, lastIndexOf).trim(); // trim - обрізає пробєли

        System.out.println("Received Date: " + message.getSentDate());
       // System.out.println("Activation link: " + activationLink);

        folder.close(false);
        store.close();
        return links.toString();


    }

}
