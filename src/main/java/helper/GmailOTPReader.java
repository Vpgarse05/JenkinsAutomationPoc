package helper;

import base.BaseTest;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailOTPReader extends BaseTest {

    public static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    public static final String TOKENS_DIRECTORY_PATH = "tokens";
    public static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
    public static final String CREDENTIALS_FILE_PATH = "C:\\Users\\Vaibhav Garse\\Downloads\\client_secret_769862752683-4f03nj9p3rggs8in0ttfshpaa84m27oo.apps.googleusercontent.com.json";

    public static Credential getCredentials(NetHttpTransport HTTP_TRANSPORT) throws IOException {

        FileInputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("sssautomation222@gmail.com");
    }

    public String getOTP() throws IOException, GeneralSecurityException {

        String OTP = null;
        String msgStr;
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        List<Message> messages = service.users().messages().list("me").setQ("is:unread").setMaxResults(10L).execute().getMessages();
        if (messages == null || messages.isEmpty()) {
            System.out.println("No messages found.");
        } else {
            for (Message message : messages) {
                Message msg = service.users().messages().get("me", message.getId()).execute();
                if (msg.getPayload().getParts() != null) {
                    msgStr = new String(msg.getPayload().getParts().get(0).getBody().decodeData(), StandardCharsets.UTF_8);
                } else {
                    msgStr = new String(msg.getPayload().getBody().decodeData(), StandardCharsets.UTF_8);
                }

                Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
                Matcher matcher = pattern.matcher(msgStr);
                if (matcher.find()) {
                    OTP = matcher.group();
                    break;
                }
            }
        }
        return OTP;
    }
}
