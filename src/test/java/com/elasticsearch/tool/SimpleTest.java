package com.elasticsearch.tool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.elasticsearch.tool.common.Constants.NEW_LINE;
import static com.elasticsearch.tool.util.EsQueryUtils.*;

@Slf4j
public class SimpleTest {

    @Test
    public void testCli() throws IOException {
        //TODO 입력값 : es 주소, 파라미터 N 개 (N개 모두 필수)
        //TODO 사용방법을 표시해야한다
        //TODO replace 할 파라미터 표시
        String inputQuery = "GET _search\n" +
                "{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}";

        String ip = "localhost:9200";

        List<String> queryParams = new ArrayList<>();
        queryParams.add("seq");
        queryParams.add("id");
        queryParams.add("name");
        queryParams.add("name");

        String shell = makeEsQueryToShell(inputQuery, ip, queryParams, "es.sh");

        log.info("testCli => shell : {}", shell);
        createShellAsFile(shell, "es.sh");

    }

    @Test
    public void testCreateParameterHeader() throws IOException {

        List<String> queryParams = new ArrayList<>();
        queryParams.add("seq");
        queryParams.add("id");
        queryParams.add("name");
        queryParams.add("name");

        String filename = "temp.sh";

        String header = createHeader(queryParams, filename);

        log.info(NEW_LINE + "{}", header);


    }

}
