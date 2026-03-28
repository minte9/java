curl http://localhost:8080/hello
    # Access denied

URL='http://localhost:8080/token?user=myuser&password=mypass'
RESPONSE=$(curl -s -X POST -H "Content-Type: application/json" $URL)
TOKEN=$(echo $RESPONSE | jq -r '.token')
echo $TOKEN
    # Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dG...

curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/hello
    # Hello World