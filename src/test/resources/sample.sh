#!/bin/bash

## keyword 로 삭제

if [ -z $1 ]; then
    echo "Usage: es_index_delete_by_seq.sh keyword"
    exit 1;
fi

echo "delete index keyword:$1"
echo ""

data="{
  \"query\": {
    \"term\": {
      \"keyword\": \"$1\"
    }
  }
}"

out="curl -X POST \"localhost:9200/index/_delete_by_query\" -H \"Content-Type: application/json\" -d '$data'"

echo "out=$out"
echo $out > run.sh
chmod 755 run.sh
./run.sh

echo ""
