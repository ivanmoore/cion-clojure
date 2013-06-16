package com.oocode.cion;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.URI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(URI.create("http://ivan:password@localhost:8080/httpAuth/app/rest/projects/"));
        request.setEntity(new StringEntity("myproj", ContentType.TEXT_PLAIN));
        HttpResponse httpResponse = client.execute(request);
        System.out.println("httpResponse = " + httpResponse);
        InputStream content = httpResponse.getEntity().getContent();
        String next = new Scanner(content).useDelimiter("\\A").next();
        System.out.println("next = " + next);
    }
}
