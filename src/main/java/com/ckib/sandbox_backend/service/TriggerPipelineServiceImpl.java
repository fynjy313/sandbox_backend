package com.ckib.sandbox_backend.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class TriggerPipelineServiceImpl implements TriggerPipelineService {
    private static final String TRIGGER_TOKEN = "glptt-74cddf342f00b35b735f8371b6c586fc7c23de9e";//ias token to "Container_scan_pipeline"

    @Override
    public void triggerPipeline(String imageName) {
        HttpURLConnection con = null;
        //String imageName = "harbor.stage.local/ssdlc/yandex-cloud-devsecops@sha256:2e78f63ae7b0be9e5d0dbe409bc6e647978d68fad5d5e601b5764167f96db47d";
        var url = "https://gitlab.ssdlc.online/api/v4/projects/8/ref/send-report-to-nexus/trigger/pipeline";
        var urlParameters = "token=" + TRIGGER_TOKEN + "&variables[TEST_IMAGE]=" + imageName;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {
            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (var wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }

            StringBuilder content;

            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            assert con != null;
            con.disconnect();
        }
    }
}
