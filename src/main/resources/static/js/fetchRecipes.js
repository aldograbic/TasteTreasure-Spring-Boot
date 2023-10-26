const APP_ID = "f995a27c";
const APP_KEY = "e1979cdadca32155ca0b295a7449529d";

const API_URL = "https://api.edamam.com/search";

async function fetchRecipes(query) {
  const response = await fetch(
    API_URL + "?q=" + query + "&app_id=" + APP_ID + "&app_key=" + APP_KEY
  );
  const data = await response.json();
  console.log(data);
}

async function searchRecipes() {
  const query = document.getElementById("search").value;
  await fetchRecipes(query);
}
