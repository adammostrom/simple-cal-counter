curl -X POST http://localhost:8080/nutrition \
-H "Content-Type: application/json" \
-d '{
  "resource": "rice",
  "amount": 200,
  "unit": "GRAM"
}'
