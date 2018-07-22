<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>hello</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style>
        body {
            font: normal normal 13px/16px "Open Sans", sans-serif;
            background: #dfdfdf;
        } {color: red;}
        form {
            display: inline-block;
        }
        input{
            padding: 4px 15px 4px 5px;
        }
        ol {padding-left: 20px;}

        ol li {padding: 5px;color:#000;}

        ol li:nth-child(even){background: #dfdfdf;}

        #btnParam{
            display: inline-block;
            background-color:#fc999b;
            color:#ffffff;
            border-radius: 5px;
            text-align:center;
            margin-top:2px;
            padding: 5px 15px;
        }

        li:hover{
            cursor: pointer;
        }
    </style>
    <script>
        $(document).ready(function () {
            $('#btnConvert').click(function () {
                var query = $('#esQuery').val();
                var ip = $('#esIp').val();
                var filename = $('#filename').val();

                var params = [];
                $('.params').each(function( index ) {
                    params.push($( this ).text());
                });

                // for params array serialize
                jQuery.ajaxSettings.traditional = true;

                $.get("/convert", {query: query, ip: ip, param: params , filename: filename})
                        .done(function (data) {
                            $('#script').val(data.after);
                        }).fail(function(jqXHR, textStatus, errorThrown) {
                            alert(jqXHR.responseJSON.description);
                        });
            });

            $('#btnParam').click(
                    function(){
                        var toAdd = $('input[name=ListItem]').val();
                        $('ol').append('<li class=params>' + toAdd + '</li>');
                    });

            $("input[name=ListItem]").keyup(function(event){
                if(event.keyCode == 13){
                    $("#button").click();
                }
            });

            $(document).on('dblclick','li', function(){
                $(this).toggleClass('strike').fadeOut('slow');

            });

            $('input').focus(function() {
                $(this).val('');
            });

            $('#btnClean').click(
                    function(){
                        console.log('clean');
                    });
        });
    </script>
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
            <button id="btnClean">clean</button>
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