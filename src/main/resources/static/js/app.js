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
            throw new Error(data.message);
        }

        displayResults(data);

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


function displayResults(data) {
    let html = `<h3>${data.name}</h3>`;

    for (const [field, value] of Object.entries(data.fields)) {
        html += `<p>${field}: ${value}</p>`;
    }

    document.getElementById("result").innerHTML = html;
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