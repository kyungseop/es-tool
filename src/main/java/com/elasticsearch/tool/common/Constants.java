package com.elasticsearch.tool.common;

public class Constants {

    public static final String WHITE_SPACE = " ";
    public static final String OR = " || ";
    public static final String NEW_LINE = "\n";
    public static final String PRETTY = "?pretty";
    public static final String URL_TEMPLATE = "curl -X %s \"%s/%s\" -H \"Content-Type: application/json\" -d '$data'";
    public static final String PREFIX = "$";
    public static final String HEADER_FORMAT = "if %s; then";
    public static final String PARAM_FORMAT = "[ -z %s ]";

}
