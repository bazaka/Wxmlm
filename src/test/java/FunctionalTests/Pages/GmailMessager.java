//package FunctionalTests.Pages;
//
//import org.junit.Test;
//
//import javax.mail.*;
//import java.util.Properties;
//
///**
// * Created by User on 12/8/2014.
// */
//public class GmailMessager {
//    @Test
//    public void getMessages (/*TestUser testUser*/){
//        String POP_AUTH_USER = "yb@t4web.com.ua";/*testUser.getEmail();*/
//        String POP_AUTH_PWD = "ghbdtnbr";/* testUser.getEPassword();*/
//
//        String FOLDER_INBOX = "INBOX"; //name of INBOX folder
//        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//
//        try{
//            System.out.println("Начинаю соединение с Gmail через POP3");
//            Properties pop3Props = new Properties();
//
//            pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
//            pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
//            pop3Props.setProperty("mail.pop3.port", "995");
//            pop3Props.setProperty("mail.pop3.socketFactory.port", "995");
//
//
//            URLName url = new URLName("pop3", "pop.gmail.com", 955, "", POP_AUTH_USER, POP_AUTH_PWD);
//            //Session session = Session.getDefaultInstance(pop3Props);
//            Session session = Session.getInstance(pop3Props, null);
//            Store store = session.getStore(url);
//            store.connect();
//
//            Folder folder = store.getFolder(FOLDER_INBOX);
//            try{
//                folder.open(Folder.READ_WRITE);
//            } catch(MessagingException ex){
//                folder.open(Folder.READ_ONLY);
//            }
//            //Message[] messages = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false)); // тут список новий листів убде
//            // обробка повідомлень:
//            Message[] messages = folder.getMessages();
//            System.out.println("Messages.length: "+messages.length);
//            //for (int i=messages.length; i>(messages.length - 3; i--){ // останні 3 листи
//            for(int i=(messages.length-1); i>10; i--){
//            //for (int i=0; i<messages.length; i++){
//
//
//                Message message = messages[i];
//                System.out.println("Email Number "+ (i));
//                System.out.println("Subjet: "+ message.getSubject());
//                System.out.println("From: "+message.getFrom());
//                System.out.println("Received Date "+message.getSentDate());
//                System.out.println("Text: "+ message.getContent().toString());
//            }
//
//            folder.close(false);
//            store.close();
//        } catch (NoSuchProviderException e) {
//            e.printStackTrace();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//    }
//
//}
