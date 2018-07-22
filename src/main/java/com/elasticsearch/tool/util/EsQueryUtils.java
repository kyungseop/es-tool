package com.elasticsearch.tool.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.elasticsearch.tool.common.Constants.*;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.text.StringEscapeUtils.escapeJava;

@Slf4j
public class EsQueryUtils {

    /**
     * convert qdsl from kibana to shell script
     *
     * @param qdsl     elasticsearch query from kibana
     * @param ip       elasticsearch ip address
     * @param params   script parameters
     * @param filename filename
     * @return qdsl shell script
     * @throws IOException
     */
    public static String makeEsQueryToShell(final String qdsl, String ip, List<String> params, String filename) throws IOException {
        requireNonNull(qdsl, "elasticsearch query was required.");

        if (StringUtils.isEmpty(ip)) {
            ip = "localhost:9200";
        }

        String[] split = qdsl.split(NEW_LINE);

        StringBuilder urlBuilder = new StringBuilder();
        StringBuilder dataBuilder = new StringBuilder();
        boolean firstLine = true;
        for (String line : split) {
            if (firstLine) {
                String[] header = line.split(WHITE_SPACE);
                try {
                    String format = String.format(URL_TEMPLATE, header[0], ip, header[1] + PRETTY);
                    urlBuilder.append(escapeJava(format));
                } catch (ArrayIndexOutOfBoundsException e) {
                    log.error("makeEsQueryToShell => error : {}", e);
                    throw new IllegalArgumentException("error.qdsl.malformed.url");
                }
                firstLine = false;
                continue;
            }
            dataBuilder.append(escapeJava(line));
            dataBuilder.append(NEW_LINE);
        }

        if (!CollectionUtils.isEmpty(params)) {
            String header = createHeader(params, filename);
            return createScript(dataBuilder.toString(), urlBuilder.toString(), header);

        } else {
            return createScript(dataBuilder.toString(), urlBuilder.toString());
        }

    }

    private static String createScript(String escapeData, String escapeUrl) {
        return createScript(escapeData, escapeUrl, null);
    }

    /**
     * combine each script parts
     *
     * @param escapeData qdsl
     * @param escapeUrl  qdsl url & index
     * @param header     script parameters and usage
     * @return shell script
     */
    private static String createScript(String escapeData, String escapeUrl, String header) {
        log.debug("createScript => escapeUrl : {}", escapeUrl);
        log.debug("createScript => escapeData : {}", escapeData);

        StringBuilder scriptBuilder = new StringBuilder();
        scriptBuilder.append("#!/bin/bash");
        scriptBuilder.append(NEW_LINE);
        scriptBuilder.append(NEW_LINE);
        scriptBuilder.append(NEW_LINE);
        if (!StringUtils.isEmpty(header)) {
            scriptBuilder.append(header);
            scriptBuilder.append(NEW_LINE);
            scriptBuilder.append(NEW_LINE);
            scriptBuilder.append(NEW_LINE);
        }
        scriptBuilder.append("data=\"" + escapeData + "\"");
        scriptBuilder.append(NEW_LINE);
        scriptBuilder.append("out=\"" + escapeUrl + "\"");
        scriptBuilder.append(NEW_LINE);
        scriptBuilder.append("echo \"out=$out\"");
        scriptBuilder.append(NEW_LINE);
        scriptBuilder.append("echo $out > run.sh");
        scriptBuilder.append(NEW_LINE);
        scriptBuilder.append("chmod 755 run.sh");
        scriptBuilder.append(NEW_LINE);
        scriptBuilder.append("./run.sh");
        scriptBuilder.append(NEW_LINE);
        scriptBuilder.append(" echo \"\"");
        return scriptBuilder.toString();
    }

    /**
     * create shell script parameter and usage
     *
     * @param scriptParameters script parameters
     * @param filename         script filename
     * @return parameter and usage string
     */
    public static String createHeader(List<String> scriptParameters, String filename) {
        if (StringUtils.isEmpty(filename)) {
            filename = "es.sh";
        }

        List<String> params = new ArrayList<>();
        for (int i = 1; i <= scriptParameters.size(); i++) {
            params.add(String.format(PARAM_FORMAT, PREFIX + i));
        }

        String delimitedString = StringUtils.collectionToDelimitedString(params, OR);
        String headerParams = String.format(HEADER_FORMAT, delimitedString);

        String usageSequence = StringUtils.collectionToDelimitedString(scriptParameters, WHITE_SPACE);

        StringBuilder headerBulder = new StringBuilder();
        headerBulder.append(headerParams);
        headerBulder.append(NEW_LINE);
        headerBulder.append("echo \"Usage: ./" + filename + " " + usageSequence + "\"");
        headerBulder.append(NEW_LINE);
        headerBulder.append("exit 1;");
        headerBulder.append(NEW_LINE);
        headerBulder.append("f1");
        return headerBulder.toString();
    }

    /**
     * @param shellScript shell script
     * @param filename    script filename
     * @throws IOException
     */
    public static void createShellAsFile(String shellScript, String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(shellScript);
        printWriter.close();
    }


}
