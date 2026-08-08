const staticFields = [
    "calories",
    "fat",
    "saturated_fat",
    "trans_fat",
    "cholesterol",
    "carbohydrates",
    "sugars",
    "added_sugars",
    "sucrose",
    "glucose",
    "fructose",
    "lactose",
    "starch",
    "fiber",
    "proteins",
    "salt",
    "added_salt",
    "sodium",
    "vitamin_c",
    "vitamin_b1",
    "vitamin_b2",
    "vitamin_pp",
    "vitamin_b6",
    "vitamin_b9",
    "vitamin_b12",
    "potassium",
    "calcium",
    "iron",
    "magnesium",
    "zinc"
];


async function searchNutrition() {

    clearError();
    const productName = document.getElementById("product").value;
    const productAmount = document.getElementById("amount").value;

    const nutritionFields = getSelectedFields();

    try {
        const response = await fetch("/nutrition", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                product_name: productName,
                amount: productAmount,
                unit: "GRAM",
                fields: nutritionFields
            })
        });

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.error || data.message || "Something went wrong.");
        }

        displayNutrition(data, productAmount);

    } catch (error) {
        showError(error.message);
    }
}


function getSelectedFields() {
    return staticFields
        .map(field => document.getElementById(field))
        .filter(element => element && element.checked)
        .map(element => element.value);
}


function displayNutrition(data, productAmount) {

    const box = document.getElementById("nutrition-result");

    // clear previous result
    box.innerHTML = "";

    let rows = "";

    for (const [field, value] of Object.entries(data.fields)) {
            rows += `
                <tr>
                    <td>${field}</td>
                    <td>${data.fields[field]}</td>
                </tr>
            `;
    }
    


    box.innerHTML = `
        <div class="nutrition-title">
            Nutrition Facts
        </div>

        <div class="nutrition-serving">
            Serving Size ${productAmount} g
        </div>

        <table class="nutrition-table">
            <thead>
                <tr>
                    <th>Nutrient</th>
                    <th>Amount (in grams)</th>
                </tr>
            </thead>

            <tbody>
                ${rows}
            </tbody>
        </table>

        <div class="nutrition-footer">
            Values are based on available nutritional data.
        </div>
        <div class="nutrition-footer">
        Source of all nutritional data: https://in.openfoodfacts.org/
        </div>
    `;
}

function showError(message) {
    const errorBox = document.getElementById("error-message");

    errorBox.textContent = message;
    errorBox.style.display = "block";
}

function clearError() {
    const errorBox = document.getElementById("error-message");

    errorBox.textContent = "";
    errorBox.style.display = "none";
}

function resetForm() {

    document.getElementById("product").value = "";

    document.getElementById("amount").value = 100;

    staticFields.forEach(field => {
        const checkbox = document.getElementById(field);

        if (checkbox) {
            checkbox.checked = [
                "calories"
            ].includes(field);
        }
    });

    document.getElementById("nutrition-result").innerHTML = "";
    clearError();
}

