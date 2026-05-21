curl -X POST http://localhost:8080/nutrition \
-H "Content-Type: application/json" \
-d '{
	"ingredient": "apple",
	"amount": 200,
	"unit": "GRAM"
}'
