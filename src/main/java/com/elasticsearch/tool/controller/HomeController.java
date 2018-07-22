package com.elasticsearch.tool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.elasticsearch.tool.util.EsQueryUtils.makeEsQueryToShell;

@Slf4j
@Controller
public class HomeController {


    @GetMapping("/")
    public ModelAndView hello(ModelAndView mv) {
        mv.setViewName("home");
        return mv;
    }

    /**
     * elasticsearch query to shell script
     *
     * @param query query dsl from kibana
     * @param ip    elasticsearch ip address
     * @return
     * @throws IOException
     */
    @GetMapping("/convert")
    public ResponseEntity convert(@RequestParam String query, @RequestParam(required = false) String ip,
                                  @RequestParam(required = false) List<String> param, @RequestParam(required = false) String filename) throws IOException {
        log.info("request to convert => query : {}, ip : {}, param : {}, filename : {}", query, ip, CollectionUtils.isEmpty(param) ? "" : param, filename);
        String shell = makeEsQueryToShell(query, ip, param, filename);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("before", query);
        hm.put("after", shell);
        return ResponseEntity.ok(hm);
    }
}
