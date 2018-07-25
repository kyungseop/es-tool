<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>hello</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/js/home.js"></script>
    <link rel="stylesheet" href="/css/home.css">
</head>
<body>
<div>
    <div>
        <div style="float: left; width: 50%">
            <p>Elasticsearch IP</p>
            <input id="esIp" value="localhost:9200"><br>
        </div>
        <div style="float: right; width: 50%">
            <p>File Name</p>
            <input id="filename" value="es.sh"><br>
        </div>
    </div>
    <div>
        <div>
            <p>Parameter</p>
            <form name="paramList">
                <input name="ListItem"/>
            </form>
            <button id="btnParam">Add Parameter</button><br>
            <ol name="param"></ol>
        </div>
        <div>
            <button id="btnConvert">convert to shell</button>
            <button id="btnClear">clear</button>
        </div>
    </div>

    <div style="float: left; width: 50%" class="wrap_kibana_query">
        <h3>Kibana query</h3>
        <textarea id="esQuery" rows="50" placeholder="kibana query를 입력해주세요." cols="100"></textarea>
    </div>
    <div style="float: right; width: 50%" class="wrap_shell_script">
        <h3>Shell script</h3>
        <textarea id="script" rows="50" cols="100"></textarea>
    </div>
</div>
</body>
</html>