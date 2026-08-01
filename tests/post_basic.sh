curl -X POST http://localhost:8080/nutrition \
-H "Content-Type: application/json" \
-d '{
	"product_name": "Potato",
	"amount": 200,
	"unit": "GRAM",
	"fields": ["CALORIES", "FAT", "SATURATED_FAT", "TRANS_FAT", "CHOLESTEROL", "CARBOHYDRATES", "SUGARS"
	,"ADDED_SUGARS", "SUCROSE", "GLUCOSE", "FRUCTOSE", "LACTOSE", "STARCH", "FIBER", "PROTEINS", "SALT"
	,"ADDED_SALT","SODIUM", "VITAMIN_C", "VITAMIN_B1","VITAMIN_B2","VITAMIN_PP","VITAMIN_B6","VITAMIN_B9"
	,"VITAMIN_B12", "POTASSIUM", "CALCIUM", "IRON", "MAGNESIUM", "ZINC" ]
}'
