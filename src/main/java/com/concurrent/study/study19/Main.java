package com.concurrent.study.study19;

import java.io.IOException;

/**
 * @author 王文涛
 * @date 2025/4/3
 * @description
 **/
public class Main {

    public static void main(String[] args) throws IOException {

        String url = "https://repo1.maven.org/maven2/org/apache/httpcomponents/client5/httpclient5/5.4.2/httpclient5-5.4.2.jar";

        String fileName = "src/main/resources/download/httpclient5-5.4.2.jar";

        Download.download(url, fileName);

    }

}
