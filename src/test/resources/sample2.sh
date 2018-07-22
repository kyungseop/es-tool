#!/bin/bash


if [ -z $1 ] || [ -z $2 ] || [ -z $3 ]; then
    echo "Usage: sample2.sh seq id name"
    echo "       sample2.sh 1 super tony"
    exit 1;
fi

echo "update application keyword:$1 , $2 , $3"
echo ""

data="{
  \"script\": {
    \"inline\": \"ctx._source.user.id = \u0027$2\u0027 ; ctx._source.user.name = \u0027$3\u0027 \",
    \"lang\": \"painless\"
  },
  \"query\": {
    \"term\": {
      \"keyword\": \"$1\"
    }
  }
}"

out="curl -X POST \"localhost:9200/index/_update_by_query\" -H \"Content-Type: application/json\" -d '$data'"

echo "out=$out"
echo $out > run.sh
chmod 755 run.sh
./run.sh

echo ""
