#!/bin/bash


if [ -z $1 ] || [ -z $2 ] || [ -z $3 ] || [ -z $4 ]; then
echo "Usage: ./es.sh seq id name name"
exit 1;
f1


data="{
  \"query\": {
    \"match_all\": {}
  }
}
"
out="curl -X GET \"localhost:9200/_search?pretty\" -H \"Content-Type: application/json\" -d '$data'"
echo "out=$out"
echo $out > run.sh
chmod 755 run.sh
./run.sh
 echo ""